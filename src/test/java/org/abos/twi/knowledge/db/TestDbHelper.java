package org.abos.twi.knowledge.db;

import org.abos.twi.knowledge.Facts;
import org.abos.twi.knowledge.wiki.WikiHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Test class for {@link DbHelper}.
 */
public final class TestDbHelper {

    private DbHelper dbHelper;

    private WikiHelper wikiHelper;

    @BeforeAll
    public static void setupDbConfig() {
        System.setProperty(DbHelper.PROPERTY_URL, "localhost:5432/test");
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
        wikiHelper = new WikiHelper();
    }

    @AfterEach
    public void tearDownDb() throws SQLException, IOException {
        wikiHelper = null;
        dbHelper.tearDownTables();
        dbHelper = null;
    }

    /**
     * Test for {@link DbHelper#addVolumes(List)}
     */
    @Test
    public void testAddVolumes() throws IOException, SQLException {
        dbHelper.addVolumes(wikiHelper.fetchVolumes());
        Assertions.assertEquals(Facts.VOLUME_COUNT, dbHelper.fetchVolumes().size());
    }

    /**
     * Test for {@link DbHelper#addBooks(List)}
     */
    @Test
    public void testAddBooks() throws IOException, SQLException {
        wikiHelper.fetchVolumes();
        dbHelper.addBooks(wikiHelper.fetchBooks());
    }

}
