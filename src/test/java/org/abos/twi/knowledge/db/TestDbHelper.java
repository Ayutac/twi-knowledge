package org.abos.twi.knowledge.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Test class for {@link DbHelper}.
 */
public final class TestDbHelper {

    private DbHelper dbHelper;

    @BeforeAll
    public static void setupDbConfig() {
        System.setProperty(DbHelper.PROPERTY_URL, "localhost:5432/linker_test");
        System.setProperty(DbHelper.PROPERTY_SU_NAME, "postgres");
        System.setProperty(DbHelper.PROPERTY_SU_PW, "postgres");
    }

    @BeforeEach
    public void setupDb() throws SQLException, IOException {
        dbHelper = new DbHelper();
        // for cleanup after aborted tests
        try {
            dbHelper.tearDownTables();
        }
        catch (SQLException ex) {
            /* Ignore. */
        }
        dbHelper.setupTables();
    }

    @AfterEach
    public void tearDownDb() throws SQLException, IOException {
        dbHelper.tearDownTables();
        dbHelper = null;
    }

}
