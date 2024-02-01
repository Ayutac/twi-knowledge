package org.abos.twi.knowledge.db;

import org.abos.common.LogUtil;
import org.abos.twi.knowledge.core.publication.Book;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.core.location.Landmark;
import org.abos.twi.knowledge.core.location.LandmassOcean;
import org.abos.twi.knowledge.core.location.LandmassOceanType;
import org.abos.twi.knowledge.core.location.Nation;
import org.abos.twi.knowledge.core.location.NationType;
import org.abos.twi.knowledge.core.Rsk;
import org.abos.twi.knowledge.core.location.Settlement;
import org.abos.twi.knowledge.core.location.SettlementType;
import org.abos.twi.knowledge.core.Skill;
import org.abos.twi.knowledge.core.publication.Volume;
import org.abos.twi.knowledge.core.location.World;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.PGProperty;
import org.postgresql.jdbc.PgConnection;
import org.postgresql.util.HostSpec;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public final class DbHelper {

    public static final String TABLE_SETUP_FILE_NAME = "tableSetup.sql";

    public static final String PRE_FETCH_DATA_FILL_FILE_NAME = "preFetchDataFill.sql";

    public static final String TABLE_TEAR_DOWN_FILE_NAME = "tableTearDown.sql";

    public static final String PROPERTY_URL = "postgresql_url";

    public static final String PROPERTY_SU_NAME = "postgresql_su_name";

    public static final String PROPERTY_SU_PW = "postgresql_su_pw";

    private static final String SELECT_VOLUME = "SELECT name, wiki_link FROM volume";

    private static final String SELECT_BOOK = "SELECT name, volume_ord, wiki_link, publication_link, publication_date, audible_link, audible_date, volume_id FROM book_with_volume";

    private static final String SELECT_CHAPTER = "SELECT name, volume_ord, book_ord, release, words, book_id, volume_id, link, wiki_link FROM chapter";

    private static final String SELECT_CLASS = "SELECT name, since, wiki_link FROM class";

    private static final String SELECT_SKILL = "SELECT name, since, wiki_link FROM skill";

    private static final String SELECT_WORLD = "SELECT name, since, wiki_link FROM world";

    private static final String SELECT_LANDMASS_OCEAN = "SELECT name, type, since, world_id, wiki_link FROM landmass_ocean";

    private static final String SELECT_LANDMARK = "SELECT name, is_natural, since, landmass_ocean_id, wiki_link FROM landmark";

    private static final String SELECT_NATION = "SELECT name, type, since, landmass_ocean_id, wiki_link FROM nation";

    private static final String SELECT_SETTLEMENT = "SELECT name, type, since, nation_id, wiki_link FROM settlement";

    private static final String SELECT_CHARACTER = "SELECT wiki_link FROM character";

    private static final String SELECT_RSK = "SELECT name FROM rsk";

    private static final Logger LOGGER = LogManager.getLogger(DbHelper.class);

    private static final String LOG_SQL_MSG = "SQL about to be executed: {}";

    private final HostSpec[] specs = new HostSpec[1];

    private final Properties suInfo = new Properties();

    private final BidiMap<Volume, Integer> volumeIdMap = new DualHashBidiMap<>();

    private final BidiMap<Book, Integer> bookIdMap = new DualHashBidiMap<>();

    private final BidiMap<Chapter, Integer> chapterIdMap = new DualHashBidiMap<>();

    private final BidiMap<World, Integer> worldIdMap = new DualHashBidiMap<>();

    private final BidiMap<LandmassOcean, Integer> landmassOceanIdMap = new DualHashBidiMap<>();

    private final BidiMap<Nation, Integer> nationIdMap = new DualHashBidiMap<>();

    public DbHelper() throws IllegalStateException {
        final String url = System.getProperty(PROPERTY_URL);
        if (url == null) {
            throw new IllegalStateException("No url property given! Use: " + PROPERTY_URL);
        }
        final int portIndex = url.indexOf(':');
        final int port;
        final String host;
        final String dbName;
        if (portIndex != -1) {
            final String urlSnippet = url.substring(portIndex + 1);
            final int slashIndex = urlSnippet.indexOf('/');
            host = url.substring(0, portIndex);
            port = slashIndex == -1 ? Integer.parseInt(urlSnippet) : Integer.parseInt(urlSnippet.substring(0, slashIndex));
            dbName = urlSnippet.substring(slashIndex+1);
        }
        else {
            host = url;
            port = 5432;
            dbName = "knowledge";
        }
        specs[0] = new HostSpec(host, port);
        suInfo.put(PGProperty.PG_DBNAME.getName(), dbName);
        suInfo.put(PGProperty.USER.getName(), System.getProperty(PROPERTY_SU_NAME));
        suInfo.put(PGProperty.PASSWORD.getName(), System.getProperty(PROPERTY_SU_PW));
    }

    private Connection getConnection() throws SQLException {
        return new PgConnection(specs, suInfo, specs[0].getLocalSocketAddress());
    }

    private ConnectionStatement prepareStatement(final String sql) throws SQLException {
        final Connection connection = getConnection();
        return new ConnectionStatement(connection, connection.prepareStatement(sql));
    }

    /**
     * Replaces {@code "} and {@code '} in the specified string to prevent SQL injection.
     * @param input the string to be sanitized
     * @return The string in a sanitized version. {@code null} returns {@code null}.
     */
    public static String sanitizeString(final String input) {
        if (input == null) {
            return null;
        }
        return input.replace("\"", "\\\"")
                .replace("'", "\\'");
    }

    /**
     * Use this instead of {@link PreparedStatement#setString(int, String)} to avoid SQL injection.
     * @param stmt the prepared statement to set the string into
     * @param index the parameter index
     * @param s the string to set
     * @throws SQLException If an SQL error occurs.
     */
    private static void setString(final PreparedStatement stmt, final int index, final String s) throws SQLException {
        stmt.setString(index, sanitizeString(s));
    }

    private void innerExecuteScript(final Connection connection, final String sql) throws SQLException {
        LOGGER.debug(LOG_SQL_MSG, sql);
        try (final PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();
        }
    }

    private void executeScript(final String resourceLocation) throws SQLException, IOException {
        final URL url = DbHelper.class.getClassLoader().getResource(resourceLocation);
        final String sql = Files.readString(new File(url.getFile()).toPath());
        try (final Connection connection = getConnection()) {
            innerExecuteScript(connection, sql);
        }
    }

    public void setupTables() throws IOException, SQLException {
        LOGGER.info("Setting up tables...");
        final Instant start = Instant.now();
        executeScript(TABLE_SETUP_FILE_NAME);
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Setting up tables", time.toMinutes(), time.toSecondsPart());
    }

    public void preFetchDataFill() throws IOException, SQLException {
        LOGGER.info("Pre-fetch data filling...");
        final Instant start = Instant.now();
        executeScript(PRE_FETCH_DATA_FILL_FILE_NAME);
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Pre-fetch data filling", time.toMinutes(), time.toSecondsPart());
    }

    public void tearDownTables() throws IOException, SQLException {
        LOGGER.info("Tearing down tables...");
        final Instant start = Instant.now();
        executeScript(TABLE_TEAR_DOWN_FILE_NAME);
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Tearing down tables", time.toMinutes(), time.toSecondsPart());
    }

    private <T> T internalFetchReference(BidiMap<T, Integer> cache, SQLFunction<Integer, T> fetcher, final Integer id) throws SQLException {
        if (id != 0 && !cache.containsValue(id)) {
            final T fetched = fetcher.apply(id);
            if (fetched != null) {
                cache.put(fetched, id);
            }
        }
        return cache.getKey(id);
    }

    private <T> T internalFetchById(final SQLFunction<ResultSet, T> fetcher, final String selectSql, final int id) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement(selectSql + " WHERE id=?")) {
            cs.preparedStatement().setInt(1, id);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    return fetcher.apply(rs);
                }
                return null;
            }
        }
    }

    private <T> List<T> internalFetchAll(final SQLFunction<ResultSet, T> fetcher, final String selectSql) throws SQLException {
        List<T> result = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement(selectSql + " ORDER BY id");
             final ResultSet rs = cs.preparedStatement().executeQuery()) {
            while (rs.next()) {
                result.add(fetcher.apply(rs));
            }
        }
        return result;
    }

    public void addVolume(final Volume volume, final PreparedStatement pStmt) throws SQLException {
        setString(pStmt, 1, volume.name());
        setString(pStmt, 2, volume.wikiLink());
        pStmt.execute();
    }

    public void addVolumes(final List<Volume> volumes) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO volume (name, wiki_link) VALUES (?,?);")) {
            for (Volume volume : volumes) {
                addVolume(volume, cs.preparedStatement());
            }
        }
    }

    private Integer fetchVolumeId(final String volumeName) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("SELECT id FROM volume WHERE name=?")) {
            setString(cs.preparedStatement(), 1, Objects.requireNonNull(volumeName));
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return null;
            }
        }
    }

    private Volume internalFetchVolume(final ResultSet rs) throws SQLException {
        return new Volume(rs.getString(1), rs.getString(2));
    }

    private Volume fetchVolume(final int volumeId) throws SQLException {
        return internalFetchById(this::internalFetchVolume, SELECT_VOLUME, volumeId);
    }

    public List<Volume> fetchVolumes() throws SQLException {
        return internalFetchAll(this::internalFetchVolume, SELECT_VOLUME);
    }

    public void addBook(final Book book, final PreparedStatement pStmt) throws SQLException {
        pStmt.setString(1, book.name());
        if (book.volumeOrd() == null) {
            pStmt.setNull(2, JDBCType.INTEGER.getVendorTypeNumber());
        }
        else {
            pStmt.setInt(2, book.volumeOrd());
        }
        pStmt.setString(3, book.wikiLink());
        if (book.publicationLink() == null) {
            pStmt.setNull(4, JDBCType.LONGVARCHAR.getVendorTypeNumber());
        }
        else {
            setString(pStmt, 4, book.publicationLink());
        }
        if (book.publicationDate() == null) {
            pStmt.setNull(5, JDBCType.BIGINT.getVendorTypeNumber());
        }
        else {
            pStmt.setLong(5, book.publicationDate().toEpochDay());
        }
        if (book.audibleLink() == null) {
            pStmt.setNull(6, JDBCType.LONGVARCHAR.getVendorTypeNumber());
        }
        else {
            setString(pStmt, 6, book.audibleLink());
        }
        if (book.audibleDate() == null) {
            pStmt.setNull(7, JDBCType.BIGINT.getVendorTypeNumber());
        }
        else {
            pStmt.setLong(7, book.audibleDate().toEpochDay());
        }
        pStmt.execute();
    }

    public void addBooks(final List<Book> books) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO book (name, volume_ord, wiki_link, publication_link, publication_date, audible_link, audible_date) VALUES (?,?,?,?,?,?,?);")) {
            for (Book book : books) {
                addBook(book, cs.preparedStatement());
            }
        }
    }

    private Integer fetchBookId(final String bookName) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("SELECT id FROM book WHERE name=?")) {
            setString(cs.preparedStatement(), 1, Objects.requireNonNull(bookName));
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return null;
            }
        }
    }

    private Book internalFetchBook(final ResultSet rs) throws SQLException {
        final Volume volume = internalFetchReference(volumeIdMap, this::fetchVolume, rs.getInt(8));
        final int volumeOrd = rs.getInt(2);
        return new Book(rs.getString(1), volumeOrd == 0 ? null : volumeOrd, volume, rs.getString(3), rs.getString(4), LocalDate.ofEpochDay(rs.getLong(5)), rs.getString(6), LocalDate.ofEpochDay(rs.getLong(7)));
    }

    private Book fetchBook(final int bookId) throws SQLException {
        return internalFetchById(this::internalFetchBook, SELECT_BOOK, bookId);
    }

    public List<Book> fetchBooks() throws SQLException {
        return internalFetchAll(this::internalFetchBook, SELECT_BOOK);
    }

    public void addChapter(final Chapter chapter, PreparedStatement pStmt) throws SQLException {
        setString(pStmt, 1, chapter.name());
        if (chapter.volumeOrd() == null) {
            pStmt.setNull(2, JDBCType.INTEGER.getVendorTypeNumber());
        }
        else {
            pStmt.setInt(2, chapter.volumeOrd());
        }
        if (chapter.bookOrd() == null) {
            pStmt.setNull(3, JDBCType.INTEGER.getVendorTypeNumber());
        }
        else {
            pStmt.setInt(3, chapter.bookOrd());
        }
        pStmt.setLong(4, chapter.release().toEpochDay());
        pStmt.setInt(5, chapter.words());
        pStmt.setBoolean(6, chapter.lettered());
        pStmt.setBoolean(7, chapter.interlude());
        pStmt.setBoolean(8, chapter.inParts());
        if (chapter.book() != null && !bookIdMap.containsKey(chapter.book())) {
            bookIdMap.put(chapter.book(), fetchBookId(chapter.book().name()));
        }
        final Integer bookId = bookIdMap.get(chapter.book());
        if (bookId == null) {
            pStmt.setNull(9, JDBCType.INTEGER.getVendorTypeNumber());
        }
        else {
            pStmt.setInt(9, bookId);
        }
        if (chapter.volume() != null && !volumeIdMap.containsKey(chapter.volume())) {
            volumeIdMap.put(chapter.volume(), fetchVolumeId(chapter.volume().name()));
        }
        final Integer volumeId = volumeIdMap.get(chapter.volume());
        if (volumeId == null) {
            pStmt.setNull(10, JDBCType.INTEGER.getVendorTypeNumber());
        }
        else {
            pStmt.setInt(10, volumeId);
        }
        pStmt.setString(11, chapter.link());
        pStmt.setString(12, chapter.wikiLink());
        pStmt.execute();
    }

    public void addChapters(final List<Chapter> chapters) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO chapter (name, volume_ord, book_ord, release, words, lettered, interlude, in_parts, book_id, volume_id, link, wiki_link) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);")) {
            for (Chapter chapter : chapters) {
                addChapter(chapter, cs.preparedStatement());
            }
        }
    }

    private Chapter internalFetchChapter(final ResultSet rs) throws SQLException {
        final int volumeOrd = rs.getInt(2);
        final int bookOrd = rs.getInt(3);
        final Book book = internalFetchReference(bookIdMap, this::fetchBook, rs.getInt(6));
        final Volume volume = internalFetchReference(volumeIdMap, this::fetchVolume, rs.getInt(7));
        return new Chapter(rs.getString(1), volumeOrd == 0 ? null : volumeOrd, bookOrd == 0 ? null : bookOrd, LocalDate.ofEpochDay(rs.getLong(4)), rs.getInt(5), book, volume, rs.getString(8), rs.getString(9));
    }

    private Chapter fetchChapter(final int chapterId) throws SQLException {
        return internalFetchById(this::internalFetchChapter, SELECT_CHAPTER, chapterId);
    }
    
    public List<Chapter> fetchChapters() throws SQLException {
        return internalFetchAll(this::internalFetchChapter, SELECT_CHAPTER);
    }

    private Class internalFetchClass(final ResultSet rs) throws SQLException {
        final Chapter chapter = internalFetchReference(chapterIdMap, this::fetchChapter, rs.getInt(2));
        return new Class(rs.getString(1), chapter, rs.getString(3));
    }

    private Class fetchClass(final int classId) throws SQLException {
        return internalFetchById(this::internalFetchClass, SELECT_CLASS, classId);
    }

    public List<Class> fetchClasses() throws SQLException {
        return internalFetchAll(this::internalFetchClass, SELECT_CLASS);
    }

    private Skill internalFetchSkill(final ResultSet rs) throws SQLException {
        final Chapter chapter = internalFetchReference(chapterIdMap, this::fetchChapter, rs.getInt(2));
        return new Skill(rs.getString(1), chapter, rs.getString(3));
    }

    private Skill fetchSkill(final int skillId) throws SQLException {
        return internalFetchById(this::internalFetchSkill, SELECT_SKILL, skillId);
    }

    public List<Skill> fetchSkills() throws SQLException {
        return internalFetchAll(this::internalFetchSkill, SELECT_SKILL);
    }

    private World internalFetchWorld(final ResultSet rs) throws SQLException {
        final Chapter chapter = internalFetchReference(chapterIdMap, this::fetchChapter, rs.getInt(2));
        return new World(rs.getString(1), chapter, rs.getString(3));
    }

    private World fetchWorld(final int worldId) throws SQLException {
        return internalFetchById(this::internalFetchWorld, SELECT_WORLD, worldId);
    }

    public List<World> fetchWorlds() throws SQLException {
        return internalFetchAll(this::internalFetchWorld, SELECT_WORLD);
    }

    private LandmassOcean internalFetchLandmassOcean(final ResultSet rs) throws SQLException {
        final Chapter chapter = internalFetchReference(chapterIdMap, this::fetchChapter, rs.getInt(3));
        final World world = internalFetchReference(worldIdMap, this::fetchWorld, rs.getInt(4));
        return new LandmassOcean(rs.getString(1), Enum.valueOf(LandmassOceanType.class, rs.getString(2).toUpperCase()), chapter, world, rs.getString(5));
    }

    private LandmassOcean fetchLandmassOcean(final int landmassOceanId) throws SQLException {
        return internalFetchById(this::internalFetchLandmassOcean, SELECT_LANDMASS_OCEAN, landmassOceanId);
    }

    public List<LandmassOcean> fetchLandmassesOceans() throws SQLException {
        return internalFetchAll(this::internalFetchLandmassOcean, SELECT_LANDMASS_OCEAN);
    }

    private Landmark internalFetchLandmark(final ResultSet rs) throws SQLException {
        final Chapter chapter = internalFetchReference(chapterIdMap, this::fetchChapter, rs.getInt(3));
        final LandmassOcean landmassOcean = internalFetchReference(landmassOceanIdMap, this::fetchLandmassOcean, rs.getInt(4));
        return new Landmark(rs.getString(1), rs.getBoolean(2), chapter, landmassOcean, rs.getString(5));
    }

    private Landmark fetchLandmark(final int landmarkId) throws SQLException {
        return internalFetchById(this::internalFetchLandmark, SELECT_LANDMARK, landmarkId);
    }

    public List<Landmark> fetchLandmarks() throws SQLException {
        return internalFetchAll(this::internalFetchLandmark, SELECT_LANDMARK);
    }

    private Nation internalFetchNation(final ResultSet rs) throws SQLException {
        final Chapter chapter = internalFetchReference(chapterIdMap, this::fetchChapter, rs.getInt(3));
        final LandmassOcean landmassOcean = internalFetchReference(landmassOceanIdMap, this::fetchLandmassOcean, rs.getInt(4));
        return new Nation(rs.getString(1), Enum.valueOf(NationType.class, rs.getString(2).toUpperCase().replace(' ', '_')), chapter, landmassOcean, rs.getString(5));
    }

    private Nation fetchNation(final int nationId) throws SQLException {
        return internalFetchById(this::internalFetchNation, SELECT_NATION, nationId);
    }

    public List<Nation> fetchNations() throws SQLException {
        return internalFetchAll(this::internalFetchNation, SELECT_NATION);
    }

    private Settlement internalFetchSettlement(final ResultSet rs) throws SQLException {
        final Chapter chapter = internalFetchReference(chapterIdMap, this::fetchChapter, rs.getInt(3));
        final Nation nation = internalFetchReference(nationIdMap, this::fetchNation, rs.getInt(4));
        return new Settlement(rs.getString(1), Enum.valueOf(SettlementType.class, rs.getString(2).toUpperCase()), chapter, nation, rs.getString(5));
    }

    private Settlement fetchSettlement(final int settlementId) throws SQLException {
        return internalFetchById(this::internalFetchSettlement, SELECT_SETTLEMENT, settlementId);
    }

    public List<Settlement> fetchSettlements() throws SQLException {
        return internalFetchAll(this::internalFetchSettlement, SELECT_SETTLEMENT);
    }

    public void addCharacter(final Character character, final PreparedStatement pStmt) throws SQLException {
        setString(pStmt, 1, character.wikiLink());
        pStmt.execute();
    }

    public void addCharacters(List<Character> characters) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO character (wiki_link) VALUES (?);")) {
            for (Character character : characters) {
                addCharacter(character, cs.preparedStatement());
            }
        }
    }

    private Character internalFetchCharacter(final ResultSet rs) throws SQLException {
        return new Character(rs.getString(1));
    }

    private Character fetchCharacter(final int characterId) throws SQLException {
        return internalFetchById(this::internalFetchCharacter, SELECT_CHARACTER, characterId);
    }

    public List<Character> fetchCharacters() throws SQLException {
        return internalFetchAll(this::internalFetchCharacter, SELECT_CHARACTER);
    }

    private Rsk internalFetchRsk(final ResultSet rs) throws SQLException {
        return new Rsk(rs.getString(1));
    }

    private Rsk fetchRsk(final int rskId) throws SQLException {
        return internalFetchById(this::internalFetchRsk, SELECT_RSK, rskId);
    }

    public List<Rsk> fetchRsks() throws SQLException {
        return internalFetchAll(this::internalFetchRsk, SELECT_RSK);
    }

}
