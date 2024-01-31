package org.abos.twi.knowledge.db;

import org.abos.common.LogUtil;
import org.abos.twi.knowledge.core.Book;
import org.abos.twi.knowledge.core.Volume;
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
import java.util.LinkedList;
import java.util.List;
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

    public List<Volume> fetchBooks() throws SQLException {
        throw new UnsupportedOperationException("Not implemented yet!");
//        List<Volume> result = new LinkedList<>();
//        try (final PreparedStatement pStmt = getConnection().prepareStatement("SELECT name, wiki_link FROM volume ORDER BY id");
//             final ResultSet rs = pStmt.executeQuery()) {
//            while (rs.next()) {
//                result.add(new Volume(rs.getString(1), rs.getString(2)));
//            }
//        }
//        return result;
    }

}
