package org.abos.twi.knowledge.db;

import org.abos.common.LogUtil;
import org.abos.common.Named;
import org.abos.common.StringUtil;
import org.abos.twi.knowledge.core.Species;
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
import org.abos.twi.knowledge.db.datafill.Volume1;
import org.abos.twi.knowledge.db.datafill.Volume2;
import org.abos.twi.knowledge.wiki.WikiHelper;
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
import java.util.Properties;
import java.util.Scanner;

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

    private static final String SELECT_SPECIES = "SELECT name, since, wiki_link FROM species";

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
     * @param pStmt the prepared statement to set the string into
     * @param index the parameter index
     * @param s the string to set
     * @throws SQLException If an SQL error occurs.
     */
    private static void setString(final PreparedStatement pStmt, final int index, final String s) throws SQLException {
        if (s == null) {
            pStmt.setNull(index, JDBCType.LONGVARCHAR.getVendorTypeNumber());
        }
        else {
            pStmt.setString(index, sanitizeString(s));
        }
    }

    private static void setInt(final PreparedStatement pStmt, final int index, final Integer n) throws SQLException {
        if (n == null) {
            pStmt.setNull(index, JDBCType.INTEGER.getVendorTypeNumber());
        }
        else {
            pStmt.setInt(index, n);
        }
    }

    private static void setBoolean(final PreparedStatement pStmt, final int index, final Boolean b) throws SQLException {
        if (b == null) {
            pStmt.setNull(index, JDBCType.BOOLEAN.getVendorTypeNumber());
        }
        else {
            pStmt.setBoolean(index, b);
        }
    }

    private static void setLocalDate(final PreparedStatement pStmt, final int index, final LocalDate d) throws SQLException {
        if (d == null) {
            pStmt.setNull(index, JDBCType.BIGINT.getVendorTypeNumber());
        }
        else {
            pStmt.setLong(index, d.toEpochDay());
        }
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

    private <T extends Named> Integer internalFetchId(BidiMap<T, Integer> cache, SQLFunction<String, Integer> fetcher, final T ref) throws SQLException {
        if (ref != null && !cache.containsKey(ref)) {
            final Integer fetched = fetcher.apply(ref.getName());
            if (fetched != null) {
                cache.put(ref, fetched);
            }
        }
        return cache.get(ref);
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

    private Integer internalFetchIdByName(final String tableName, final String name) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("SELECT id FROM " + sanitizeString(tableName) + " WHERE name=?")) {
            setString(cs.preparedStatement(), 1, name);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return null;
            }
        }
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

    private <T> T internalFetchByName(final SQLFunction<ResultSet, T> fetcher, final String selectSql, final String name) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement(selectSql + " WHERE name=?")) {
            cs.preparedStatement().setString(1, name);
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

    private void addVolume(final Volume volume, final PreparedStatement pStmt) throws SQLException {
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
        return internalFetchIdByName("volume", volumeName);
    }

    private Volume internalFetchVolume(final ResultSet rs) throws SQLException {
        return new Volume(rs.getString(1), rs.getString(2));
    }

    private Volume fetchVolume(final int volumeId) throws SQLException {
        return internalFetchById(this::internalFetchVolume, SELECT_VOLUME, volumeId);
    }

    public Volume fetchVolume(final String volumeName) throws SQLException {
        return internalFetchByName(this::internalFetchVolume, SELECT_VOLUME, volumeName);
    }

    public List<Volume> fetchVolumes() throws SQLException {
        return internalFetchAll(this::internalFetchVolume, SELECT_VOLUME);
    }

    private void addBook(final Book book, final PreparedStatement pStmt) throws SQLException {
        setString(pStmt, 1, book.name());
        setInt(pStmt, 2, book.volumeOrd());
        setString(pStmt, 3, book.wikiLink());
        setString(pStmt, 4, book.publicationLink());
        setLocalDate(pStmt, 5, book.publicationDate());
        setString(pStmt, 6, book.audibleLink());
        setLocalDate(pStmt, 7, book.audibleDate());
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
        return internalFetchIdByName("book", bookName);
    }

    private Book internalFetchBook(final ResultSet rs) throws SQLException {
        final Volume volume = internalFetchReference(volumeIdMap, this::fetchVolume, rs.getInt(8));
        final int volumeOrd = rs.getInt(2);
        return new Book(rs.getString(1), volumeOrd == 0 ? null : volumeOrd, volume, rs.getString(3), rs.getString(4), LocalDate.ofEpochDay(rs.getLong(5)), rs.getString(6), LocalDate.ofEpochDay(rs.getLong(7)));
    }

    private Book fetchBook(final int bookId) throws SQLException {
        return internalFetchById(this::internalFetchBook, SELECT_BOOK, bookId);
    }

    public Book fetchBook(final String bookName) throws SQLException {
        return internalFetchByName(this::internalFetchBook, SELECT_BOOK, bookName);
    }

    public List<Book> fetchBooks() throws SQLException {
        return internalFetchAll(this::internalFetchBook, SELECT_BOOK);
    }

    private void addChapter(final Chapter chapter, PreparedStatement pStmt) throws SQLException {
        setString(pStmt, 1, chapter.name());
        setInt(pStmt, 2, chapter.volumeOrd());
        setInt(pStmt, 3, chapter.bookOrd());
        setLocalDate(pStmt, 4, chapter.release());
        setInt(pStmt, 5, chapter.words());
        setBoolean(pStmt, 6, chapter.lettered());
        setBoolean(pStmt, 7, chapter.interlude());
        setBoolean(pStmt, 8, chapter.inParts());
        final Integer bookId = internalFetchId(bookIdMap, this::fetchBookId, chapter.book());
        setInt(pStmt, 9, bookId);
        final Integer volumeId = internalFetchId(volumeIdMap, this::fetchVolumeId, chapter.volume());
        setInt(pStmt, 10, volumeId);
        setString(pStmt, 11, chapter.link());
        setString(pStmt, 12, chapter.wikiLink());
        pStmt.execute();
    }

    public void addChapters(final List<Chapter> chapters) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO chapter (name, volume_ord, book_ord, release, words, lettered, interlude, in_parts, book_id, volume_id, link, wiki_link) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);")) {
            for (Chapter chapter : chapters) {
                addChapter(chapter, cs.preparedStatement());
            }
        }
    }

    private Integer fetchChapterId(final String chapterName) throws SQLException {
        return internalFetchIdByName("chapter", chapterName);
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

    public Chapter fetchChapter(final String chapterName) throws SQLException {
        return internalFetchByName(this::internalFetchChapter, SELECT_CHAPTER, chapterName);
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

    public void addWorld(final World world) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO world (name, since, wiki_link) VALUES (?,?,?);")) {
            setString(cs.preparedStatement(), 1, world.name());
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, world.since());
            setInt(cs.preparedStatement(), 2, chapterId);
            setString(cs.preparedStatement(), 3, world.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchWorldId(final String worldName) throws SQLException {
        return internalFetchIdByName("world", worldName);
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

    public void addLandmassOcean(final LandmassOcean landmassOcean) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO landmass_ocean (name, type, since, world_id, wiki_link) VALUES (?,?::landmass_ocean_type,?,?,?);")) {
            setString(cs.preparedStatement(), 1, landmassOcean.name());
            setString(cs.preparedStatement(), 2, StringUtil.toCapitalized(landmassOcean.type().name().replace("_"," ")));
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, landmassOcean.since());
            setInt(cs.preparedStatement(), 3, chapterId);
            final Integer worldId = internalFetchId(worldIdMap, this::fetchWorldId, landmassOcean.world());
            setInt(cs.preparedStatement(), 4, worldId);
            setString(cs.preparedStatement(), 5, landmassOcean.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchLandmassOceanId(final String landmassOceanName) throws SQLException {
        return internalFetchIdByName("landmass_ocean", landmassOceanName);
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

    public void addLandmark(final Landmark landmark) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO landmark (name, is_natural, since, landmass_ocean_id, wiki_link) VALUES (?,?,?,?,?);")) {
            setString(cs.preparedStatement(), 1, landmark.name());
            setBoolean(cs.preparedStatement(), 2, landmark.natural());
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, landmark.since());
            setInt(cs.preparedStatement(), 3, chapterId);
            final Integer landmassOceanId = internalFetchId(landmassOceanIdMap, this::fetchLandmassOceanId, landmark.landmassOcean());
            setInt(cs.preparedStatement(), 4, landmassOceanId);
            setString(cs.preparedStatement(), 5, landmark.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchLandmarkId(final String landmarkName) throws SQLException {
        return internalFetchIdByName("landmark", landmarkName);
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

    private Species internalFetchSpecies(final ResultSet rs) throws SQLException {
        final Chapter chapter = internalFetchReference(chapterIdMap, this::fetchChapter, rs.getInt(2));
        return new Species(rs.getString(1), chapter, rs.getBoolean(3), rs.getString(4));
    }

    private Species fetchSpecies(final int speciesId) throws SQLException {
        return internalFetchById(this::internalFetchSpecies, SELECT_SPECIES, speciesId);
    }

    public List<Species> fetchSpecies() throws SQLException {
        return internalFetchAll(this::internalFetchSpecies, SELECT_SPECIES);
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

    public void initialize(final List<Volume> volumes, final List<Book> books, final List<Chapter> chapters, final List<Character> characters) throws SQLException, IOException {
        setupTables();
        preFetchDataFill();
        addVolumes(volumes);
        addBooks(books);
        addChapters(chapters);
        addCharacters(characters);
        Volume1.fillDb(this);
        Volume2.fillDb(this);
    }

    public static void main(String[] args) throws IOException, SQLException {
        final WikiHelper wikiHelper = new WikiHelper();
        final DbHelper dbHelper = new DbHelper();
        try {dbHelper.tearDownTables();}
        catch (SQLException ex) {/* Ignore. */}
        dbHelper.initialize(wikiHelper.fetchVolumes(), wikiHelper.fetchBooks(), wikiHelper.fetchChapters(), wikiHelper.fetchCharacters());
        System.out.print("DB is up and running. Press Enter to tear it down. ");
        new Scanner(System.in).nextLine();
        dbHelper.tearDownTables();
    }

}
