package org.abos.twi.knowledge.db;

import org.abos.common.LogUtil;
import org.abos.common.Named;
import org.abos.common.StringUtil;
import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.CharacterNameType;
import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.core.Rsk;
import org.abos.twi.knowledge.core.Skill;
import org.abos.twi.knowledge.core.Species;
import org.abos.twi.knowledge.core.Status;
import org.abos.twi.knowledge.core.event.Battle;
import org.abos.twi.knowledge.core.event.CharacterStatus;
import org.abos.twi.knowledge.core.event.FirstMeeting;
import org.abos.twi.knowledge.core.event.InnArrival;
import org.abos.twi.knowledge.core.event.InnworldArrival;
import org.abos.twi.knowledge.core.event.LevelUp;
import org.abos.twi.knowledge.core.event.Solstice;
import org.abos.twi.knowledge.core.event.War;
import org.abos.twi.knowledge.core.location.Landmark;
import org.abos.twi.knowledge.core.location.LandmassOcean;
import org.abos.twi.knowledge.core.location.LandmassOceanType;
import org.abos.twi.knowledge.core.location.Nation;
import org.abos.twi.knowledge.core.location.NationType;
import org.abos.twi.knowledge.core.location.Settlement;
import org.abos.twi.knowledge.core.location.SettlementType;
import org.abos.twi.knowledge.core.location.World;
import org.abos.twi.knowledge.core.publication.Book;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.core.publication.Volume;
import org.abos.twi.knowledge.db.datafill.StaticFields;
import org.abos.twi.knowledge.db.datafill.Volume1;
import org.abos.twi.knowledge.db.datafill.Volume10;
import org.abos.twi.knowledge.db.datafill.Volume2;
import org.abos.twi.knowledge.gui.CharacterNamed;
import org.abos.twi.knowledge.wiki.WikiHelper;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.ds.PGConnectionPoolDataSource;

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
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
    private static final String SELECT_CLASS = "SELECT name, wiki_link FROM class";
    private static final String SELECT_SKILL = "SELECT name, spell, wiki_link FROM skill";
    private static final String SELECT_WORLD = "SELECT name, wiki_link FROM world";
    private static final String SELECT_LANDMASS_OCEAN = "SELECT name, type, world_id, wiki_link FROM landmass_ocean";
    private static final String SELECT_LANDMARK = "SELECT name, is_natural, landmass_ocean_id, wiki_link FROM landmark";
    private static final String SELECT_NATION = "SELECT name, type, landmass_ocean_id, wiki_link FROM nation";
    private static final String SELECT_SETTLEMENT = "SELECT name, type, nation_id, wiki_link FROM settlement";
    private static final String SELECT_SPECIES = "SELECT name, can_level, wiki_link FROM species";
    private static final String SELECT_CHARACTER = "SELECT wiki_link FROM character";
    private static final String SELECT_STATUS = "SELECT name FROM status";
    private static final String SELECT_RSK = "SELECT name FROM rsk";
    private static final String SELECT_FIRST_MEETING = "SELECT * FROM first_meeting WHERE character1_id=? AND character2_id=?;";

    private static final String INSERT_FIRST_MEETING = "INSERT INTO first_meeting_left (character1_id, character2_id, chapter_id) VALUES (?,?,?);";

    private static final String WHERE_ORDERED = "(volume_id < ? OR (volume_id = ? AND volume_ord <= ?)) ORDER BY volume_id DESC, volume_ord DESC";

    private static final Logger LOGGER = LogManager.getLogger(DbHelper.class);

    private static final String LOG_SQL_MSG = "SQL about to be executed: {}";

    private final BidiMap<Volume, Integer> volumeIdMap = new DualHashBidiMap<>();

    private final BidiMap<Book, Integer> bookIdMap = new DualHashBidiMap<>();

    private final BidiMap<Chapter, Integer> chapterIdMap = new DualHashBidiMap<>();

    private final BidiMap<Class, Integer> classIdMap = new DualHashBidiMap<>();

    private final BidiMap<Skill, Integer> skillIdMap = new DualHashBidiMap<>();

    private final BidiMap<World, Integer> worldIdMap = new DualHashBidiMap<>();

    private final BidiMap<LandmassOcean, Integer> landmassOceanIdMap = new DualHashBidiMap<>();

    private final BidiMap<Landmark, Integer> landmarkIdMap = new DualHashBidiMap<>();

    private final BidiMap<Nation, Integer> nationIdMap = new DualHashBidiMap<>();

    private final BidiMap<Settlement, Integer> settlementIdMap = new DualHashBidiMap<>();

    private final BidiMap<Species, Integer> speciesIdMap = new DualHashBidiMap<>();

    private final BidiMap<Character, Integer> characterIdMap = new DualHashBidiMap<>();

    private final BidiMap<Status, Integer> statusIdMap = new DualHashBidiMap<>();

    private final BidiMap<CharacterStatus, Integer> characterStatusIdMap = new DualHashBidiMap<>();

    private final BidiMap<Battle, Integer> battleIdMap = new DualHashBidiMap<>();

    private final BidiMap<War, Integer> warIdMap = new DualHashBidiMap<>();

    private final BidiMap<Solstice, Integer> solsticeIdMap = new DualHashBidiMap<>();

    private final BidiMap<LevelUp, Integer> levelUpIdMap = new DualHashBidiMap<>();

    private final PGConnectionPoolDataSource ds;

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
        ds = new PGConnectionPoolDataSource();
        ds.setServerNames(new String[] {host});
        ds.setPortNumbers(new int[] {port});
        ds.setDatabaseName(dbName);
        ds.setUser(System.getProperty(PROPERTY_SU_NAME));
        ds.setPassword(System.getProperty(PROPERTY_SU_PW));
    }

    private Connection getConnection() throws SQLException {
        return ds.getConnection();
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

    private <T extends Named> void internalAddAppearance(final T obj, final Chapter chapter, final String tableName, final BidiMap<T, Integer> cachedId, final SQLFunction<String, Integer> fetcher, final boolean mention) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO " + (mention ? "mention" : "appearance") + "_" + tableName + " VALUES (?,?);")) {
            final Integer objectId = internalFetchId(cachedId, fetcher, obj);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, chapter);
            setInt(cs.preparedStatement(), 1, objectId);
            setInt(cs.preparedStatement(), 2, chapterId);
            cs.preparedStatement().execute();
        }
    }

    private void fillWhereClause(final PreparedStatement pStmt, final int startIndex, final Chapter chapter) throws SQLException {
        int index = startIndex;
        final Integer volumeId = internalFetchId(volumeIdMap, this::fetchVolumeId, chapter.volume());
        setInt(pStmt, index++, volumeId);
        setInt(pStmt, index++, volumeId);
        setInt(pStmt, index, chapter.volumeOrd());
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

    public int fetchWordCount(final Chapter until) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("WITH w AS (SELECT words FROM chapter WHERE " + WHERE_ORDERED + ") SELECT SUM(words) FROM w;")) {
            fillWhereClause(cs.preparedStatement(), 1, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public int fetchWordAvg(final Chapter until) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("WITH w AS (SELECT words FROM chapter WHERE " + WHERE_ORDERED + ") SELECT AVG(words) FROM w;")) {
            fillWhereClause(cs.preparedStatement(), 1, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    return (int)Math.round(rs.getDouble(1));
                }
            }
        }
        return 0;
    }

    public void addClass(final Class clazz) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO class (name, wiki_link) VALUES (?,?);")) {
            setString(cs.preparedStatement(), 1, clazz.name());
            setString(cs.preparedStatement(), 2, clazz.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchClassId(final String worldName) throws SQLException {
        return internalFetchIdByName("class", worldName);
    }

    public void addSkill(final Skill skill) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO skill (name, spell, wiki_link) VALUES (?,?,?);")) {
            setString(cs.preparedStatement(), 1, skill.name());
            setBoolean(cs.preparedStatement(), 2, skill.spell());
            setString(cs.preparedStatement(), 3, skill.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchSkillId(final String skillName) throws SQLException {
        return internalFetchIdByName("skill", skillName);
    }

    private Class internalFetchClass(final ResultSet rs) throws SQLException {
        return new Class(rs.getString(1), rs.getString(2));
    }

    private Class fetchClass(final int classId) throws SQLException {
        return internalFetchById(this::internalFetchClass, SELECT_CLASS, classId);
    }

    public Class fetchClass(final String className) throws SQLException {
        return internalFetchByName(this::internalFetchClass, SELECT_CLASS, className);
    }

    public List<Class> fetchClasses() throws SQLException {
        return internalFetchAll(this::internalFetchClass, SELECT_CLASS);
    }

    public void addClassMention(final Class clazz, final Chapter chapter) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO mention_class VALUES (?,?);")) {
            final Integer classId = internalFetchId(classIdMap, this::fetchClassId, clazz);
            setInt(cs.preparedStatement(), 1, classId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, chapter);
            setInt(cs.preparedStatement(), 2, chapterId);
            cs.preparedStatement().execute();
        }
    }

    public List<Chapter> fetchClassMentions(final Class clazz, final Chapter until) throws SQLException {
        final List<Chapter> result = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement("SELECT chapter_id FROM mention_class_ordered WHERE class_id=? AND " + WHERE_ORDERED + ";")) {
            final Integer classId = internalFetchId(classIdMap, this::fetchClassId, clazz);
            setInt(cs.preparedStatement(), 1, classId);
            fillWhereClause(cs.preparedStatement(), 2, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    result.add(fetchChapter(rs.getInt(1)));
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    private List<Integer> fetchClassIds(Chapter until) throws SQLException {
        final List<Integer> result = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement("SELECT DISTINCT ids.class_id FROM (SELECT class_id FROM mention_class_ordered WHERE " + WHERE_ORDERED + ") AS ids ORDER BY class_id;")) {
            fillWhereClause(cs.preparedStatement(), 1, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getInt(1));
                }
            }
        }
        return result;
    }

    public List<Class> fetchClasses(final Chapter chapter) throws SQLException {
        final List<Class> result = new LinkedList<>();
        for (final Integer id : fetchClassIds(chapter)) {
            // this is suboptimal since we are not reusing prepared statements
            result.add(fetchClass(id));
        }
        Collections.sort(result);
        return result;
    }

    public void addClassUpgrade(final Class base, final Class upgrade, final Chapter chapter) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO class_upgrade VALUES (?,?,?);")) {
            final Integer baseId = internalFetchId(classIdMap, this::fetchClassId, base);
            setInt(cs.preparedStatement(), 1, baseId);
            final Integer upgradeId = internalFetchId(classIdMap, this::fetchClassId, upgrade);
            setInt(cs.preparedStatement(), 2, upgradeId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, chapter);
            setInt(cs.preparedStatement(), 3, chapterId);
            cs.preparedStatement().execute();
        }
    }

    public List<Class> fetchClassUpgradesByBase(final Class base, final Chapter until) throws SQLException {
        final List<Integer> ids = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement("SELECT upgrade_id FROM class_upgrade_ordered WHERE base_id=? AND " + WHERE_ORDERED + ";")) {
            final Integer baseId = internalFetchId(classIdMap, this::fetchClassId, base);
            setInt(cs.preparedStatement(), 1, baseId);
            fillWhereClause(cs.preparedStatement(), 2, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt(1));
                }
            }
        }
        final List<Class> result = new LinkedList<>();
        for (final Integer id : ids) {
            // this is suboptimal since we are not reusing prepared statements
            result.add(fetchClass(id));
        }
        return result;
    }

    public List<Class> fetchClassUpgradesByUpgrade(final Class upgrade, final Chapter until) throws SQLException {
        final List<Integer> ids = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement("SELECT base_id FROM class_upgrade_ordered WHERE upgrade_id=? AND " + WHERE_ORDERED + ";")) {
            final Integer upgradeId = internalFetchId(classIdMap, this::fetchClassId, upgrade);
            setInt(cs.preparedStatement(), 1, upgradeId);
            fillWhereClause(cs.preparedStatement(), 2, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt(1));
                }
            }
        }
        final List<Class> result = new LinkedList<>();
        for (final Integer id : ids) {
            // this is suboptimal since we are not reusing prepared statements
            result.add(fetchClass(id));
        }
        return result;
    }

    private Skill internalFetchSkill(final ResultSet rs) throws SQLException {
        return new Skill(rs.getString(1), rs.getBoolean(2), rs.getString(3));
    }

    private Skill fetchSkill(final int skillId) throws SQLException {
        return internalFetchById(this::internalFetchSkill, SELECT_SKILL, skillId);
    }

    public Skill fetchSkill(final String skillName) throws SQLException {
        return internalFetchByName(this::internalFetchSkill, SELECT_SKILL, skillName);
    }

    public List<Skill> fetchSkills() throws SQLException {
        return internalFetchAll(this::internalFetchSkill, SELECT_SKILL);
    }

    public void addSkillMention(final Skill skill, final Chapter chapter) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO mention_skill VALUES (?,?);")) {
            final Integer skillId = internalFetchId(skillIdMap, this::fetchSkillId, skill);
            setInt(cs.preparedStatement(), 1, skillId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, chapter);
            setInt(cs.preparedStatement(), 2, chapterId);
            cs.preparedStatement().execute();
        }
    }

    public List<Chapter> fetchSkillMentions(final Skill skill, final Chapter until) throws SQLException {
        final List<Chapter> result = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement("SELECT chapter_id FROM mention_skill_ordered WHERE skill_id=? AND " + WHERE_ORDERED + ";")) {
            final Integer classId = internalFetchId(skillIdMap, this::fetchSkillId, skill);
            setInt(cs.preparedStatement(), 1, classId);
            fillWhereClause(cs.preparedStatement(), 2, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    result.add(fetchChapter(rs.getInt(1)));
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    private List<Integer> fetchSkillIds(Chapter until) throws SQLException {
        final List<Integer> result = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement("SELECT DISTINCT ids.skill_id FROM (SELECT skill_id FROM mention_skill_ordered WHERE " + WHERE_ORDERED + ") AS ids ORDER BY skill_id;")) {
            fillWhereClause(cs.preparedStatement(), 1, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getInt(1));
                }
            }
        }
        return result;
    }

    public List<Skill> fetchSkills(final Chapter chapter) throws SQLException {
        final List<Skill> result = new LinkedList<>();
        for (final Integer id : fetchSkillIds(chapter)) {
            // this is suboptimal since we are not reusing prepared statements
            result.add(fetchSkill(id));
        }
        Collections.sort(result);
        return result;
    }

    public void addSkillUpgrade(final Skill base, final Skill upgrade, final Chapter chapter) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO skill_upgrade VALUES (?,?,?);")) {
            final Integer baseId = internalFetchId(skillIdMap, this::fetchClassId, base);
            setInt(cs.preparedStatement(), 1, baseId);
            final Integer upgradeId = internalFetchId(skillIdMap, this::fetchClassId, upgrade);
            setInt(cs.preparedStatement(), 2, upgradeId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, chapter);
            setInt(cs.preparedStatement(), 3, chapterId);
            cs.preparedStatement().execute();
        }
    }

    public List<Skill> fetchSkillUpgradesByBase(final Skill base, final Chapter until) throws SQLException {
        final List<Integer> ids = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement("SELECT upgrade_id FROM skill_upgrade_ordered WHERE base_id=? AND " + WHERE_ORDERED + ";")) {
            final Integer baseId = internalFetchId(skillIdMap, this::fetchSkillId, base);
            setInt(cs.preparedStatement(), 1, baseId);
            fillWhereClause(cs.preparedStatement(), 2, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt(1));
                }
            }
        }
        final List<Skill> result = new LinkedList<>();
        for (final Integer id : ids) {
            // this is suboptimal since we are not reusing prepared statements
            result.add(fetchSkill(id));
        }
        return result;
    }

    public List<Skill> fetchSkillUpgradesByUpgrade(final Skill upgrade, final Chapter until) throws SQLException {
        final List<Integer> ids = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement("SELECT base_id FROM skill_upgrade_ordered WHERE upgrade_id=? AND " + WHERE_ORDERED + ";")) {
            final Integer baseId = internalFetchId(skillIdMap, this::fetchSkillId, upgrade);
            setInt(cs.preparedStatement(), 1, baseId);
            fillWhereClause(cs.preparedStatement(), 2, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt(1));
                }
            }
        }
        final List<Skill> result = new LinkedList<>();
        for (final Integer id : ids) {
            // this is suboptimal since we are not reusing prepared statements
            result.add(fetchSkill(id));
        }
        return result;
    }

    public void addClassSkill(final Class clazz, final Skill skill, final Chapter chapter) throws  SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO class_skill VALUES (?,?,?);")) {
            final Integer classId = internalFetchId(classIdMap, this::fetchClassId, clazz);
            setInt(cs.preparedStatement(), 1, classId);
            final Integer skillId = internalFetchId(skillIdMap, this::fetchSkillId, skill);
            setInt(cs.preparedStatement(), 2, skillId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, chapter);
            setInt(cs.preparedStatement(), 3, chapterId);
            cs.preparedStatement().execute();
        }
    }

    public List<Skill> fetchClassSkillsByClass(final Class clazz, final Chapter until) throws SQLException {
        final List<Skill> result = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement("SELECT skill_id FROM class_skill_ordered WHERE class_id=? AND " + WHERE_ORDERED + ";")) {
            final Integer classId = internalFetchId(classIdMap, this::fetchClassId, clazz);
            setInt(cs.preparedStatement(), 1, classId);
            fillWhereClause(cs.preparedStatement(), 2, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    // this is suboptimal since we are not reusing prepared statements
                    result.add(fetchSkill(rs.getInt(1)));
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    public List<Class> fetchClassSkillsBySkill(final Skill skill, final Chapter until) throws SQLException {
        final List<Class> result = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement("SELECT class_id FROM class_skill_ordered WHERE skill_id=? AND " + WHERE_ORDERED + ";")) {
            final Integer skillId = internalFetchId(skillIdMap, this::fetchSkillId, skill);
            setInt(cs.preparedStatement(), 1, skillId);
            fillWhereClause(cs.preparedStatement(), 2, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    // this is suboptimal since we are not reusing prepared statements
                    result.add(fetchClass(rs.getInt(1)));
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    public void addWorld(final World world) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO world (name, wiki_link) VALUES (?,?);")) {
            setString(cs.preparedStatement(), 1, world.name());
            setString(cs.preparedStatement(), 2, world.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchWorldId(final String worldName) throws SQLException {
        return internalFetchIdByName("world", worldName);
    }

    private World internalFetchWorld(final ResultSet rs) throws SQLException {
        return new World(rs.getString(1), rs.getString(2));
    }

    private World fetchWorld(final int worldId) throws SQLException {
        return internalFetchById(this::internalFetchWorld, SELECT_WORLD, worldId);
    }

    public List<World> fetchWorlds() throws SQLException {
        return internalFetchAll(this::internalFetchWorld, SELECT_WORLD);
    }

    public void addWorldAppearance(final World world, final Chapter chapter) throws SQLException {
        internalAddAppearance(world, chapter, "world", worldIdMap, this::fetchWorldId, false);
    }

    public void addWorldMention(final World world, final Chapter chapter) throws SQLException {
        internalAddAppearance(world, chapter, "world", worldIdMap, this::fetchWorldId, true);
    }

    public void addLandmassOcean(final LandmassOcean landmassOcean) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO landmass_ocean (name, type, world_id, wiki_link) VALUES (?,?::landmass_ocean_type,?,?);")) {
            setString(cs.preparedStatement(), 1, landmassOcean.name());
            setString(cs.preparedStatement(), 2, StringUtil.toCapitalized(landmassOcean.type().name().replace("_"," ")));
            final Integer worldId = internalFetchId(worldIdMap, this::fetchWorldId, landmassOcean.world());
            setInt(cs.preparedStatement(), 3, worldId);
            setString(cs.preparedStatement(), 4, landmassOcean.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchLandmassOceanId(final String landmassOceanName) throws SQLException {
        return internalFetchIdByName("landmass_ocean", landmassOceanName);
    }

    private LandmassOcean internalFetchLandmassOcean(final ResultSet rs) throws SQLException {
        final World world = internalFetchReference(worldIdMap, this::fetchWorld, rs.getInt(3));
        return new LandmassOcean(rs.getString(1), Enum.valueOf(LandmassOceanType.class, rs.getString(2).toUpperCase().replace(' ', '_')), world, rs.getString(4));
    }

    private LandmassOcean fetchLandmassOcean(final int landmassOceanId) throws SQLException {
        return internalFetchById(this::internalFetchLandmassOcean, SELECT_LANDMASS_OCEAN, landmassOceanId);
    }

    public List<LandmassOcean> fetchLandmassesOceans() throws SQLException {
        return internalFetchAll(this::internalFetchLandmassOcean, SELECT_LANDMASS_OCEAN);
    }

    public void addLandmassOceanAppearance(final LandmassOcean landmassOcean, final Chapter chapter) throws SQLException {
        internalAddAppearance(landmassOcean, chapter, "landmass_ocean", landmassOceanIdMap, this::fetchLandmassOceanId, false);
    }

    public void addLandmassOceanMention(final LandmassOcean landmassOcean, final Chapter chapter) throws SQLException {
        internalAddAppearance(landmassOcean, chapter, "landmass_ocean", landmassOceanIdMap, this::fetchLandmassOceanId, true);
    }

    public void addLandmark(final Landmark landmark) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO landmark (name, is_natural, landmass_ocean_id, wiki_link) VALUES (?,?,?,?);")) {
            setString(cs.preparedStatement(), 1, landmark.name());
            setBoolean(cs.preparedStatement(), 2, landmark.natural());
            final Integer landmassOceanId = internalFetchId(landmassOceanIdMap, this::fetchLandmassOceanId, landmark.landmassOcean());
            setInt(cs.preparedStatement(), 3, landmassOceanId);
            setString(cs.preparedStatement(), 4, landmark.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchLandmarkId(final String landmarkName) throws SQLException {
        return internalFetchIdByName("landmark", landmarkName);
    }

    private Landmark internalFetchLandmark(final ResultSet rs) throws SQLException {
        final LandmassOcean landmassOcean = internalFetchReference(landmassOceanIdMap, this::fetchLandmassOcean, rs.getInt(3));
        return new Landmark(rs.getString(1), rs.getBoolean(2), landmassOcean, rs.getString(4));
    }

    private Landmark fetchLandmark(final int landmarkId) throws SQLException {
        return internalFetchById(this::internalFetchLandmark, SELECT_LANDMARK, landmarkId);
    }

    public List<Landmark> fetchLandmarks() throws SQLException {
        return internalFetchAll(this::internalFetchLandmark, SELECT_LANDMARK);
    }

    public void addLandmarkAppearance(final Landmark landmark, final Chapter chapter) throws SQLException {
        internalAddAppearance(landmark, chapter, "landmark", landmarkIdMap, this::fetchLandmarkId, false);
    }

    public void addLandmarkMention(final Landmark landmark, final Chapter chapter) throws SQLException {
        internalAddAppearance(landmark, chapter, "landmark", landmarkIdMap, this::fetchLandmarkId, true);
    }

    public void addNation(final Nation nation) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO nation (name, type, landmass_ocean_id, wiki_link) VALUES (?,?::nation_type,?,?);")) {
            setString(cs.preparedStatement(), 1, nation.name());
            setString(cs.preparedStatement(), 2, StringUtil.toCapitalized(nation.nationType().name().replace('_', ' ')));
            final Integer landmassOceanId = internalFetchId(landmassOceanIdMap, this::fetchLandmassOceanId, nation.landmassOcean());
            setInt(cs.preparedStatement(), 3, landmassOceanId);
            setString(cs.preparedStatement(), 4, nation.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchNationId(final String nationName) throws SQLException {
        return internalFetchIdByName("nation", nationName);
    }

    private Nation internalFetchNation(final ResultSet rs) throws SQLException {
        final LandmassOcean landmassOcean = internalFetchReference(landmassOceanIdMap, this::fetchLandmassOcean, rs.getInt(3));
        return new Nation(rs.getString(1), Enum.valueOf(NationType.class, rs.getString(2).toUpperCase().replace(' ', '_')), landmassOcean, rs.getString(4));
    }

    private Nation fetchNation(final int nationId) throws SQLException {
        return internalFetchById(this::internalFetchNation, SELECT_NATION, nationId);
    }

    public List<Nation> fetchNations() throws SQLException {
        return internalFetchAll(this::internalFetchNation, SELECT_NATION);
    }

    public void addNationAppearance(final Nation nation, final Chapter chapter) throws SQLException {
        internalAddAppearance(nation, chapter, "nation", nationIdMap, this::fetchNationId, false);
    }

    public void addNationMention(final Nation nation, final Chapter chapter) throws SQLException {
        internalAddAppearance(nation, chapter, "nation", nationIdMap, this::fetchNationId, true);
    }

    public void addSettlement(final Settlement settlement) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO settlement (name, type, nation_id, wiki_link) VALUES (?,?::settlement_type,?,?);")) {
            setString(cs.preparedStatement(), 1, settlement.name());
            setString(cs.preparedStatement(), 2, StringUtil.toCapitalized(settlement.settlementType().name().replace('_', ' ')));
            final Integer nationId = internalFetchId(nationIdMap, this::fetchNationId, settlement.nation());
            setInt(cs.preparedStatement(), 3, nationId);
            setString(cs.preparedStatement(), 4, settlement.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchSettlementId(final String settlementName) throws SQLException {
        return internalFetchIdByName("settlement", settlementName);
    }

    private Settlement internalFetchSettlement(final ResultSet rs) throws SQLException {
        final Nation nation = internalFetchReference(nationIdMap, this::fetchNation, rs.getInt(3));
        return new Settlement(rs.getString(1), Enum.valueOf(SettlementType.class, rs.getString(2).toUpperCase()), nation, rs.getString(4));
    }

    private Settlement fetchSettlement(final int settlementId) throws SQLException {
        return internalFetchById(this::internalFetchSettlement, SELECT_SETTLEMENT, settlementId);
    }

    public List<Settlement> fetchSettlements() throws SQLException {
        return internalFetchAll(this::internalFetchSettlement, SELECT_SETTLEMENT);
    }

    public void addSettlementAppearance(final Settlement settlement, final Chapter chapter) throws SQLException {
        internalAddAppearance(settlement, chapter, "settlement", settlementIdMap, this::fetchSettlementId, false);
    }

    public void addSettlementMention(final Settlement settlement, final Chapter chapter) throws SQLException {
        internalAddAppearance(settlement, chapter, "settlement", settlementIdMap, this::fetchSettlementId, true);
    }

    public void addSpecies(final Species species) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO species (name, can_level, wiki_link) VALUES (?,?,?);")) {
            setString(cs.preparedStatement(), 1, species.name());
            setBoolean(cs.preparedStatement(), 2, species.canLevel());
            setString(cs.preparedStatement(), 3, species.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchSpeciesId(final String speciesName) throws SQLException {
        return internalFetchIdByName("species", speciesName);
    }

    private Species internalFetchSpecies(final ResultSet rs) throws SQLException {
        return new Species(rs.getString(1), rs.getBoolean(2), rs.getString(3));
    }

    private Species fetchSpecies(final int speciesId) throws SQLException {
        return internalFetchById(this::internalFetchSpecies, SELECT_SPECIES, speciesId);
    }

    public List<Species> fetchSpecies() throws SQLException {
        return internalFetchAll(this::internalFetchSpecies, SELECT_SPECIES);
    }

    public void addSpeciesAppearance(final Species species, final Chapter chapter) throws SQLException {
        internalAddAppearance(species, chapter, "species", speciesIdMap, this::fetchSpeciesId, false);
    }

    public void addSpeciesMention(final Species species, final Chapter chapter) throws SQLException {
        internalAddAppearance(species, chapter, "species", speciesIdMap, this::fetchSpeciesId, true);
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

    private Integer fetchCharacterId(final String wikiLink) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("SELECT id FROM character WHERE wiki_link=?")) {
            setString(cs.preparedStatement(), 1, wikiLink);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return null;
            }
        }
    }

    private Integer fetchCharacterId(final Character character) throws SQLException {
        if (!characterIdMap.containsKey(character)) {
            final Integer fetch = fetchCharacterId(character.wikiLink());
            if (fetch != null) {
                characterIdMap.put(character, fetch);
            }
        }
        return characterIdMap.get(character);
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

    public void addCharacterSpecies(final Character character, final Species species, final Chapter since) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO character_species VALUES (?,?,?);")) {
            final Integer characterId = fetchCharacterId(character);
            setInt(cs.preparedStatement(), 1, characterId);
            final Integer speciesId = internalFetchId(speciesIdMap, this::fetchSpeciesId, species);
            setInt(cs.preparedStatement(), 2, speciesId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, since);
            setInt(cs.preparedStatement(), 3, chapterId);
            cs.preparedStatement().execute();
        }
    }

    private Species fetchLatestCharacterSpecies(final Character character, final Chapter until) throws SQLException {
        final int speciesId;
        try (final ConnectionStatement cs = prepareStatement("SELECT species_id FROM character_species_ordered WHERE character_id = ? AND " + WHERE_ORDERED + " LIMIT 1;")) {
            final Integer characterId = fetchCharacterId(character);
            setInt(cs.preparedStatement(), 1, characterId);
            final Integer volumeId = internalFetchId(volumeIdMap, this::fetchVolumeId, until.volume());
            setInt(cs.preparedStatement(), 2, volumeId);
            setInt(cs.preparedStatement(), 3, volumeId);
            setInt(cs.preparedStatement(), 4, until.volumeOrd());
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    speciesId = rs.getInt(1);
                }
                else {
                    return null;
                }
            }
        }
        return fetchSpecies(speciesId);
    }

    public void addCharacterAge(final Character character, final int age, final Chapter since) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO character_age VALUES (?,?,?);")) {
            setInt(cs.preparedStatement(), 1, age);
            final Integer characterId = fetchCharacterId(character);
            setInt(cs.preparedStatement(), 2, characterId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, since);
            setInt(cs.preparedStatement(), 3, chapterId);
            cs.preparedStatement().execute();
        }
    }

    private void internalAddCharacterAppearance(final Character character, final Chapter chapter, final boolean mention) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO " + (mention ? "mention" : "appearance") + "_character VALUES (?,?);")) {
            final Integer characterId = fetchCharacterId(character);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, chapter);
            setInt(cs.preparedStatement(), 1, characterId);
            setInt(cs.preparedStatement(), 2, chapterId);
            cs.preparedStatement().execute();
        }
    }

    public void addCharacterAppearance(final Character character, final Chapter chapter) throws SQLException {
        internalAddCharacterAppearance(character, chapter, false);
    }

    public void addCharacterMention(final Character character, final Chapter chapter) throws SQLException {
        internalAddCharacterAppearance(character, chapter, true);
    }

    private void internalAddCharacterName(final Character character, final Chapter chapter, final String name, final CharacterNameType type) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO " + type.name().toLowerCase() + "_name (name, character_id, since) VALUES (?,?,?);")) {
            final Integer characterId = fetchCharacterId(character);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, chapter);
            setString(cs.preparedStatement(), 1, name);
            setInt(cs.preparedStatement(), 2, characterId);
            setInt(cs.preparedStatement(), 3, chapterId);
            cs.preparedStatement().execute();
        }
    }

    private String internalFetchLatestCharacterName(final Character character, final Chapter until, final CharacterNameType type) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("SELECT name FROM " + type.name().toLowerCase() + "_name_ordered WHERE character_id = ? AND " + WHERE_ORDERED + " LIMIT 1;")) {
            final Integer characterId = fetchCharacterId(character);
            setInt(cs.preparedStatement(), 1, characterId);
            final Integer volumeId = internalFetchId(volumeIdMap, this::fetchVolumeId, until.volume());
            setInt(cs.preparedStatement(), 2, volumeId);
            setInt(cs.preparedStatement(), 3, volumeId);
            setInt(cs.preparedStatement(), 4, until.volumeOrd());
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        }
        return null;
    }

    public void addCharacterFirstName(final Character character, final Chapter chapter, final String name) throws SQLException {
        internalAddCharacterName(character, chapter, name, CharacterNameType.FIRST);
    }

    public String fetchLatestCharacterFirstName(final Character character, final Chapter until) throws SQLException {
        return internalFetchLatestCharacterName(character, until, CharacterNameType.FIRST);
    }

    public void addCharacterMiddleName(final Character character, final Chapter chapter, final String name) throws SQLException {
        internalAddCharacterName(character, chapter, name, CharacterNameType.MIDDLE);
    }

    public String fetchLatestCharacterMiddleName(final Character character, final Chapter until) throws SQLException {
        return internalFetchLatestCharacterName(character, until, CharacterNameType.MIDDLE);
    }

    public void addCharacterLastName(final Character character, final Chapter chapter, final String name) throws SQLException {
        internalAddCharacterName(character, chapter, name, CharacterNameType.LAST);
    }

    public String fetchLatestCharacterLastName(final Character character, final Chapter until) throws SQLException {
        return internalFetchLatestCharacterName(character, until, CharacterNameType.LAST);
    }

    public void addCharacterNickName(final Character character, final Chapter chapter, final String name) throws SQLException {
        internalAddCharacterName(character, chapter, name, CharacterNameType.NICK);
    }

    public String fetchLatestCharacterNickName(final Character character, final Chapter until) throws SQLException {
        return internalFetchLatestCharacterName(character, until, CharacterNameType.NICK);
    }

    private String internalBuildCharacterName(final String firstName, final String middleName, final String lastName, final String nickName, final String speciesName) {
        final StringBuilder s = new StringBuilder();
        boolean anything = false;
        if (firstName != null) {
            s.append(firstName);
            anything = true;
        }
        if (middleName != null) {
            if (anything) {
                s.append(' ');
            }
            s.append(middleName);
            anything = true;
        }
        if (lastName != null) {
            if (anything) {
                s.append(' ');
            }
            s.append(lastName);
            anything = true;
        }
        if (!anything) {
            if (nickName != null) {
                s.append(nickName);
                anything = true;
            }
        }
        if (!anything) {
            if (speciesName != null) {
                s.append("the ");
                s.append(speciesName);
                anything = true;
            }
        }
        if (!anything) {
            s.append("the creature");
        }
        return s.toString();
    }

    public String buildLatestCharacterName(final Character character, final Chapter until) throws SQLException {
        final String first = fetchLatestCharacterFirstName(character, until);
        final String middle = fetchLatestCharacterMiddleName(character, until);
        final String last = fetchLatestCharacterLastName(character, until);
        final String nick = (first == null && middle == null && last == null) ? fetchLatestCharacterNickName(character, until) : null;
        final String speciesName;
        if (first == null && middle == null && last == null && nick == null) {
            final Species species = fetchLatestCharacterSpecies(character, until);
            if (species != null) {
                speciesName = species.name();
            }
            else {
                speciesName = null;
            }
        }
        else {
            speciesName = null;
        }
        return internalBuildCharacterName(first, middle, last, nick, speciesName);
    }

    private record NameOrdered(String name, int characterId, int volumeId, int volumeOrd) {
        /* Nothing to add. */
    }

    private static String nameNullSafe(NameOrdered nameOrdered) {
        if (nameOrdered == null) {
            return null;
        }
        return nameOrdered.name;
    }

    private static void fillNameMap(Chapter until, int volumeId, List<NameOrdered> names, Map<Integer, NameOrdered> nameMap) {
        for (final NameOrdered nameOrdered : names) {
            // if hit is after until, ignore
            if (nameOrdered.volumeId > volumeId || (nameOrdered.volumeId == volumeId && nameOrdered.volumeOrd > until.volumeOrd())) {
                continue;
            }
            // if no hit for that character yet, add
            if (!nameMap.containsKey(nameOrdered.characterId)) {
                nameMap.put(nameOrdered.characterId, nameOrdered);
            }
            // else check which one is more recent (if both are the same, the first in the list will be taken)
            else {
                final NameOrdered mappedOne = nameMap.get(nameOrdered.characterId);
                if (mappedOne.volumeId > nameOrdered.volumeId || (mappedOne.volumeId == nameOrdered.volumeId && mappedOne.volumeOrd > nameOrdered.volumeOrd)) {
                    nameMap.put(nameOrdered.characterId, nameOrdered);
                }
            }
        }
    }

    public List<CharacterNamed> fetchCharacters(final Chapter until) throws SQLException {
        // fetch relevant characters
        final List<Integer> ids = new LinkedList<>();
        System.out.println(LocalTime.now());
        try (final ConnectionStatement cs = prepareStatement("WITH ch AS (SELECT character_id FROM appearance_mention_character_ordered WHERE " + WHERE_ORDERED + ") SELECT DISTINCT character_id FROM ch ORDER BY character_id;")) {
            fillWhereClause(cs.preparedStatement(), 1, until);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt(1));
                }
            }
        }
        System.out.println(LocalTime.now());

        // fetch relevant names
        final String selectString = "SELECT * FROM %s WHERE character_id IN (%s);";
        final String inSet = ids.stream().map(n -> Integer.toString(n)).collect(Collectors.joining(","));
        System.out.println(LocalTime.now());
        final List<NameOrdered> firstNames = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement(String.format(selectString, CharacterNameType.FIRST.name().toLowerCase() + "_name_ordered", inSet));
             final ResultSet rs = cs.preparedStatement().executeQuery()) {
            while (rs.next()) {
                firstNames.add(new NameOrdered(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
        }
        System.out.println(LocalTime.now());
        final List<NameOrdered> middleNames = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement(String.format(selectString, CharacterNameType.MIDDLE.name().toLowerCase() + "_name_ordered", inSet));
             final ResultSet rs = cs.preparedStatement().executeQuery()) {
            while (rs.next()) {
                middleNames.add(new NameOrdered(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
        }
        System.out.println(LocalTime.now());
        final List<NameOrdered> lastNames = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement(String.format(selectString, CharacterNameType.LAST.name().toLowerCase() + "_name_ordered", inSet));
             final ResultSet rs = cs.preparedStatement().executeQuery()) {
            while (rs.next()) {
                lastNames.add(new NameOrdered(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
        }
        System.out.println(LocalTime.now());
        final List<NameOrdered> nickNames = new LinkedList<>();
        try (final ConnectionStatement cs = prepareStatement(String.format(selectString, CharacterNameType.NICK.name().toLowerCase() + "_name_ordered", inSet));
             final ResultSet rs = cs.preparedStatement().executeQuery()) {
            while (rs.next()) {
                nickNames.add(new NameOrdered(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
        }
        System.out.println(LocalTime.now());
        // TODO species Name

        // get most recent names
        final Map<Integer, NameOrdered> firstNameMap = new TreeMap<>();
        final Map<Integer, NameOrdered> middleNameMap = new TreeMap<>();
        final Map<Integer, NameOrdered> lastNameMap = new TreeMap<>();
        final Map<Integer, NameOrdered> nickNameMap = new TreeMap<>();
        final Map<Integer, NameOrdered> speciesNameMap = new TreeMap<>();
        if (!volumeIdMap.containsKey(until.volume())) {
            volumeIdMap.put(until.volume(), fetchVolumeId(until.volume().name()));
        }
        final int volumeId = volumeIdMap.get(until.volume());
        fillNameMap(until, volumeId, firstNames, firstNameMap);
        fillNameMap(until, volumeId, middleNames, middleNameMap);
        fillNameMap(until, volumeId, lastNames, lastNameMap);
        fillNameMap(until, volumeId, nickNames, nickNameMap);
        // TODO species Name
        System.out.println(LocalTime.now());

        // save results
        final List<CharacterNamed> result = new LinkedList<>();
        for (final Integer characterId : ids) {
            // TODO fetch all missing characters at once
            if (!characterIdMap.containsValue(characterId)) {
                characterIdMap.put(fetchCharacter(characterId), characterId);
            }
            final Character character = characterIdMap.getKey(characterId);
            result.add(new CharacterNamed(character, internalBuildCharacterName(
                    nameNullSafe(firstNameMap.get(characterId)),
                    nameNullSafe(middleNameMap.get(characterId)),
                    nameNullSafe(lastNameMap.get(characterId)),
                    nameNullSafe(nickNameMap.get(characterId)),
                    nameNullSafe(speciesNameMap.get(characterId))
            )));
        }
        System.out.println(LocalTime.now());
        Collections.sort(result);
        return result;
    }

    private Integer fetchStatusId(final String statusName) throws SQLException {
        return internalFetchIdByName("status", statusName);
    }

    private Status internalFetchStatus(final ResultSet rs) throws SQLException {
        return new Status(rs.getString(1));
    }

    private Status fetchStatus(final int statusId) throws SQLException {
        return internalFetchById(this::internalFetchStatus, SELECT_STATUS, statusId);
    }

    public List<Status> fetchStatuses() throws SQLException {
        return internalFetchAll(this::internalFetchStatus, SELECT_STATUS);
    }

    public void addCharacterStatus(final CharacterStatus characterStatus) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO character_status (status_id, character_id, since) VALUES (?,?,?);")) {
            final Integer statusId = internalFetchId(statusIdMap, this::fetchStatusId, characterStatus.status());
            setInt(cs.preparedStatement(), 1, statusId);
            final Integer characterId = fetchCharacterId(characterStatus.character());
            setInt(cs.preparedStatement(), 2, characterId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, characterStatus.since());
            setInt(cs.preparedStatement(), 3, chapterId);
            cs.preparedStatement().execute();
        }
    }

    public Integer fetchCharacterStatusId(final CharacterStatus characterStatus) throws SQLException {
        if (characterStatusIdMap.containsKey(characterStatus)) {
            return characterStatusIdMap.get(characterStatus);
        }
        try (final ConnectionStatement cs = prepareStatement("SELECT id FROM character_status WHERE status_id=? AND character_id=? AND since=?;")) {
            final Integer statusId = internalFetchId(statusIdMap, this::fetchStatusId, characterStatus.status());
            setInt(cs.preparedStatement(), 1, statusId);
            final Integer characterId = fetchCharacterId(characterStatus.character());
            setInt(cs.preparedStatement(), 2, characterId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, characterStatus.since());
            setInt(cs.preparedStatement(), 3, chapterId);
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    final int fetch = rs.getInt(1);
                    characterStatusIdMap.put(characterStatus, fetch);
                    return fetch;
                }
            }
        }
        return null;
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

    private void internalMaybeAddFirstMeeting(final FirstMeeting firstMeeting, ConnectionStatement selectCs, ConnectionStatement insertCs) throws SQLException {
        final Integer character1Id = fetchCharacterId(firstMeeting.character1());
        final Integer character2Id = fetchCharacterId(firstMeeting.character2());
        final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, firstMeeting.chapter());
        setInt(selectCs.preparedStatement(), 1, character1Id);
        setInt(selectCs.preparedStatement(), 2, character2Id);
        try (final ResultSet rs = selectCs.preparedStatement().executeQuery()) {
            if (rs.next()) {
                return;
            }
        }
        setInt(insertCs.preparedStatement(), 1, character1Id);
        setInt(insertCs.preparedStatement(), 2, character2Id);
        setInt(insertCs.preparedStatement(), 3, chapterId);
        insertCs.preparedStatement().execute();
    }

    public void maybeAddFirstMeeting(final FirstMeeting firstMeeting) throws SQLException {
        try (final ConnectionStatement selectCs = prepareStatement(SELECT_FIRST_MEETING);
             final ConnectionStatement insertCs = prepareStatement(INSERT_FIRST_MEETING)) {
            internalMaybeAddFirstMeeting(firstMeeting, selectCs, insertCs);
        }
    }

    public void maybeAddFirstMeetings(final List<FirstMeeting> firstMeetings) throws SQLException {
        try (final ConnectionStatement selectCs = prepareStatement(SELECT_FIRST_MEETING);
             final ConnectionStatement insertCs = prepareStatement(INSERT_FIRST_MEETING)) {
            for (FirstMeeting firstMeeting : firstMeetings) {
                internalMaybeAddFirstMeeting(firstMeeting, selectCs, insertCs);
            }
        }
    }

    public void addBattle(final Battle battle) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO battle (name, wiki_link) VALUES (?,?);")) {
            setString(cs.preparedStatement(), 1, battle.name());
            setString(cs.preparedStatement(), 2, battle.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchBattleId(final String battleName) throws SQLException {
        return internalFetchIdByName("battle", battleName);
    }

    public void addBattleCharacter(final Battle battle, final Character character) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO battle_character (battle_id, character_id) VALUES (?,?);")) {
            final Integer battleId = internalFetchId(battleIdMap, this::fetchBattleId, battle);
            setInt(cs.preparedStatement(), 1, battleId);
            final Integer characterId = fetchCharacterId(character);
            setInt(cs.preparedStatement(), 2, characterId);
            cs.preparedStatement().execute();
        }
    }

    public void addBattleChapter(final Battle battle, final Chapter chapter) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO battle_chapter (battle_id, chapter_id) VALUES (?,?);")) {
            final Integer battleId = internalFetchId(battleIdMap, this::fetchBattleId, battle);
            setInt(cs.preparedStatement(), 1, battleId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, chapter);
            setInt(cs.preparedStatement(), 2, chapterId);
            cs.preparedStatement().execute();
        }
    }

    public void addBattleStatus(final Battle battle, final CharacterStatus status) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO battle_status (battle_id, status_id) VALUES (?,?);")) {
            final Integer battleId = internalFetchId(battleIdMap, this::fetchBattleId, battle);
            setInt(cs.preparedStatement(), 1, battleId);
            final Integer statusId = fetchCharacterStatusId(status);
            setInt(cs.preparedStatement(), 2, statusId);
            cs.preparedStatement().execute();
        }
    }

    public void addWar(final War war) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO war (name, wiki_link) VALUES (?,?);")) {
            setString(cs.preparedStatement(), 1, war.name());
            setString(cs.preparedStatement(), 2, war.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    public Integer fetchWarId(final String warName) throws SQLException {
        return internalFetchIdByName("war", warName);
    }

    public void addWarBattle(final War war, final Battle battle) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO war_battle (war_id, battle_id) VALUES (?,?);")) {
            final Integer warId = internalFetchId(warIdMap, this::fetchWarId, war);
            setInt(cs.preparedStatement(), 1, warId);
            final Integer battleId = internalFetchId(battleIdMap, this::fetchBattleId, battle);
            setInt(cs.preparedStatement(), 2, battleId);
            cs.preparedStatement().execute();
        }
    }

    public void addSolstice(final Solstice solstice) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO solstice (name, wiki_link) VALUES (?,?);")) {
            setString(cs.preparedStatement(), 1, solstice.name());
            setString(cs.preparedStatement(), 2, solstice.wikiLink());
            cs.preparedStatement().execute();
        }
    }

    public Integer fetchSolsticeId(final String solsticeName) throws SQLException {
        return internalFetchIdByName("solstice", solsticeName);
    }

    public void addSolsticeChapter(final Solstice solstice, final Chapter chapter) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO solstice_chapter (solstice_id, chapter_id) VALUES (?,?);")) {
            final Integer solsticeId = internalFetchId(solsticeIdMap, this::fetchSolsticeId, solstice);
            setInt(cs.preparedStatement(), 1, solsticeId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, chapter);
            setInt(cs.preparedStatement(), 2, chapterId);
            cs.preparedStatement().execute();
        }
    }

    public void addInnworldArrival(final InnworldArrival innworldArrival) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO innworld_arrival (character_id, chapter_id) VALUES (?,?);")) {
            final Integer characterId = fetchCharacterId(innworldArrival.character());
            setInt(cs.preparedStatement(), 1, characterId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, innworldArrival.chapter());
            setInt(cs.preparedStatement(), 2, chapterId);
            cs.preparedStatement().execute();
        }
    }

    public void addInnArrival(final InnArrival innArrival) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO inn_arrival (character_id, chapter_id) VALUES (?,?);")) {
            final Integer characterId = fetchCharacterId(innArrival.character());
            setInt(cs.preparedStatement(), 1, characterId);
            final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, innArrival.chapter());
            setInt(cs.preparedStatement(), 2, chapterId);
            cs.preparedStatement().execute();
        }
    }

    private void internalFillLevelUpStatement(final LevelUp levelUp, final PreparedStatement pStmt) throws SQLException {
        final Integer characterId = fetchCharacterId(levelUp.character());
        setInt(pStmt, 1, characterId);
        final Integer chapterId = internalFetchId(chapterIdMap, this::fetchChapterId, levelUp.chapter());
        setInt(pStmt, 2, chapterId);
        setInt(pStmt, 3, levelUp.newLevel());
        final Integer classId = levelUp.clazz() == null ? null : internalFetchId(classIdMap, this::fetchClassId, levelUp.clazz());
        setInt(pStmt, 4, classId);
        setBoolean(pStmt, 5, levelUp.capstone());
        setBoolean(pStmt, 6, levelUp.canceled());
    }

    public void addLevelUp(final LevelUp levelUp) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO level_up (character_id, chapter_id, new_level, class_id, capstone, canceled) VALUES (?,?,?,?,?,?);")) {
            internalFillLevelUpStatement(levelUp, cs.preparedStatement());
            cs.preparedStatement().execute();
        }
    }

    private Integer fetchLevelUpId(final LevelUp levelUp) throws SQLException {
        if (levelUpIdMap.containsKey(levelUp)) {
            return levelUpIdMap.get(levelUp);
        }
        try (final ConnectionStatement cs = prepareStatement("SELECT id FROM level_up WHERE character_id=? AND chapter_id=? AND new_level=? AND class_id=? AND capstone=? AND canceled=?;")) {
            internalFillLevelUpStatement(levelUp, cs.preparedStatement());
            try (final ResultSet rs = cs.preparedStatement().executeQuery()) {
                if (rs.next()) {
                    final int fetch = rs.getInt(1);
                    levelUpIdMap.put(levelUp, fetch);
                    return fetch;
                }
            }
        }
        return null;
    }

    public void addLevelUpSkill(final LevelUp levelUp, final Skill skill) throws SQLException {
        try (final ConnectionStatement cs = prepareStatement("INSERT INTO level_up_skill VALUES (?,?);")) {
            final Integer levelUpId = fetchLevelUpId(levelUp);
            setInt(cs.preparedStatement(), 1, levelUpId);
            final Integer skillId = internalFetchId(skillIdMap, this::fetchSkillId, skill);
            setInt(cs.preparedStatement(), 2, skillId);
            cs.preparedStatement().execute();
        }
    }

    public void initialize(final List<Volume> volumes, final List<Book> books, final List<Chapter> chapters, final List<Character> characters) throws SQLException, IOException, IllegalAccessException {
        setupTables();
        preFetchDataFill();
        addVolumes(volumes);
        addBooks(books);
        addChapters(chapters);
        addCharacters(characters);
        StaticFields.fillDb(this);
        Volume1.fillDb(this);
        Volume2.fillDb(this);
        Volume10.fillDb(this);
    }

    public static void main(String[] args) throws IOException, SQLException, IllegalAccessException {
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
