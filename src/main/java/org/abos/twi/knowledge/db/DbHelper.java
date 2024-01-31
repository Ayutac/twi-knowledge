package org.abos.twi.knowledge.db;

import org.abos.common.LogUtil;
import org.abos.twi.knowledge.core.Book;
import org.abos.twi.knowledge.core.Chapter;
import org.abos.twi.knowledge.core.Volume;
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

    public static final String TABLE_TEAR_DOWN_FILE_NAME = "tableTearDown.sql";

    public static final String PROPERTY_URL = "postgresql_url";

    public static final String PROPERTY_SU_NAME = "postgresql_su_name";

    public static final String PROPERTY_SU_PW = "postgresql_su_pw";

    private static final Logger LOGGER = LogManager.getLogger(DbHelper.class);

    private static final String LOG_SQL_MSG = "SQL about to be executed: {}";

    private final HostSpec[] specs = new HostSpec[1];

    private final Properties suInfo = new Properties();

    private final BidiMap<Volume, Integer> volumeIdMap = new DualHashBidiMap<>();

    private final BidiMap<Book, Integer> bookIdMap = new DualHashBidiMap<>();

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

    public void tearDownTables() throws IOException, SQLException {
        LOGGER.info("Tearing down tables...");
        final Instant start = Instant.now();
        executeScript(TABLE_TEAR_DOWN_FILE_NAME);
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Tearing down tables", time.toMinutes(), time.toSecondsPart());
    }

    public PreparedStatement prepareAddVolumeStatement() throws SQLException {
        return getConnection().prepareStatement("INSERT INTO volume (name, wiki_link) VALUES (?,?);");
    }

    public void addVolume(final Volume volume, final PreparedStatement pStmt) throws SQLException {
        setString(pStmt, 1, volume.name());
        setString(pStmt, 2, volume.wikiLink());
        pStmt.execute();
    }

    public void addVolumes(final List<Volume> volumes) throws SQLException {
        try (final PreparedStatement pStmt = prepareAddVolumeStatement()) {
            for (Volume volume : volumes) {
                addVolume(volume, pStmt);
            }
        }
    }

    private Integer fetchVolumeId(final String volumeName) throws SQLException {
        try (final PreparedStatement pStmt = getConnection().prepareStatement("SELECT id FROM volume WHERE name=?")) {
            setString(pStmt, 1, Objects.requireNonNull(volumeName));
            try (final ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return null;
            }
        }
    }

    private Volume fetchVolume(final int volumeId) throws SQLException {
        try (final PreparedStatement pStmt = getConnection().prepareStatement("SELECT name, wiki_link FROM volume WHERE id=?")) {
            pStmt.setInt(1, volumeId);
            try (final ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    return new Volume(rs.getString(1), rs.getString(2));
                }
                return null;
            }
        }
    }

    public List<Volume> fetchVolumes() throws SQLException {
        List<Volume> result = new LinkedList<>();
        try (final PreparedStatement pStmt = getConnection().prepareStatement("SELECT name, wiki_link FROM volume ORDER BY id");
             final ResultSet rs = pStmt.executeQuery()) {
            while (rs.next()) {
                result.add(new Volume(rs.getString(1), rs.getString(2)));
            }
        }
        return result;
    }

    public PreparedStatement prepareAddBookStatement() throws SQLException {
        return getConnection().prepareStatement("INSERT INTO book (name, volume_ord, wiki_link, publication_link, publication_date, audible_link, audible_date) VALUES (?,?,?,?,?,?,?);");
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
        try (final PreparedStatement pStmt = prepareAddBookStatement()) {
            for (Book book : books) {
                addBook(book, pStmt);
            }
        }
    }

    private Integer fetchBookId(final String bookName) throws SQLException {
        try (final PreparedStatement pStmt = getConnection().prepareStatement("SELECT id FROM book WHERE name=?")) {
            setString(pStmt, 1, Objects.requireNonNull(bookName));
            try (final ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return null;
            }
        }
    }

    public List<Book> fetchBooks() throws SQLException {
        List<Book> result = new LinkedList<>();
        try (final PreparedStatement pStmt = getConnection().prepareStatement("SELECT name, volume_ord, wiki_link, publication_link, publication_date, audible_link, audible_date, volume_id FROM book_with_volume ORDER BY id");
             final ResultSet rs = pStmt.executeQuery()) {
            while (rs.next()) {
                final int volumeId = rs.getInt(8);
                if (volumeId != 0 && !volumeIdMap.containsValue(volumeId)) {
                    volumeIdMap.put(fetchVolume(volumeId), volumeId);
                }
                final Volume volume = volumeIdMap.getKey(volumeId);
                final int volumeOrd = rs.getInt(2);
                result.add(new Book(rs.getString(1), volumeOrd == 0 ? null : volumeOrd, volume, rs.getString(3), rs.getString(4), LocalDate.ofEpochDay(rs.getLong(5)), rs.getString(6), LocalDate.ofEpochDay(rs.getLong(7))));
            }
        }
        return result;
    }

    private PreparedStatement prepareAddChapterStatement() throws SQLException {
        return getConnection().prepareStatement("INSERT INTO chapter (name, volume_ord, book_ord, release, words, lettered, interlude, in_parts, book_id, volume_id, link, wiki_link) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
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
        try (final PreparedStatement pStmt = prepareAddChapterStatement()) {
            for (Chapter chapter : chapters) {
                addChapter(chapter, pStmt);
            }
        }
    }
    
    public List<Chapter> fetchChapters() throws SQLException {
        try (final PreparedStatement pStmt = getConnection().prepareStatement("")) {

        }
        return null;
    }

}
