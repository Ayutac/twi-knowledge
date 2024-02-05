package org.abos.twi.knowledge.db;

import org.abos.twi.knowledge.Facts;
import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.publication.Book;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.core.publication.Volume;
import org.abos.twi.knowledge.db.datafill.Volume1;
import org.abos.twi.knowledge.wiki.WikiHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for {@link DbHelper}.
 */
public final class TestDbHelper {

    private static final List<Volume> VOLUMES = new ArrayList<>();

    private static final List<Book> BOOKS = new ArrayList<>();

    private static final List<Chapter> CHAPTERS = new ArrayList<>();

    private static final List<Character> CHARACTERS = new ArrayList<>();

    private DbHelper dbHelper;

    @BeforeAll
    public static void setupDbConfigAndFetchFromWiki() throws IOException {
        System.setProperty(DbHelper.PROPERTY_URL, "localhost:5432/test");
        System.setProperty(DbHelper.PROPERTY_SU_NAME, "postgres");
        System.setProperty(DbHelper.PROPERTY_SU_PW, "postgres");
        final WikiHelper wikiHelper = new WikiHelper();
        VOLUMES.addAll(wikiHelper.fetchVolumes());
        BOOKS.addAll(wikiHelper.fetchBooks());
        CHAPTERS.addAll(wikiHelper.fetchChapters());
        CHARACTERS.addAll(wikiHelper.fetchCharacters());
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
        dbHelper.preFetchDataFill();
    }

    @AfterEach
    public void tearDownDb() throws SQLException, IOException {
        dbHelper.tearDownTables();
        dbHelper = null;
    }

    /**
     * Test for {@link DbHelper#addVolumes(List)}
     */
    @Test
    public void testAddVolumes() throws SQLException {
        dbHelper.addVolumes(VOLUMES);
        Assertions.assertEquals(Facts.VOLUME_COUNT, dbHelper.fetchVolumes().size());
    }

    /**
     * Test for {@link DbHelper#addBooks(List)}
     */
    @Test
    public void testAddBooks() throws SQLException {
        dbHelper.addBooks(BOOKS);
    }

    /**
     * Test for {@link DbHelper#addChapters(List)}, {@link DbHelper#fetchBooks()} and {@link DbHelper#fetchChapters()}.
     */
    @Test
    public void testAddChaptersAndFetch() throws SQLException {
        dbHelper.addVolumes(VOLUMES);
        dbHelper.addBooks(BOOKS);
        dbHelper.addChapters(CHAPTERS);
        Assertions.assertEquals(Facts.BOOK_COUNT, dbHelper.fetchBooks().size());
        Assertions.assertTrue(Facts.CHAPTER_COUNT_LOWER_BOUND <= dbHelper.fetchChapters().size());
    }

    /**
     * Test for {@link DbHelper#addChapters(List)}.
     */
    @Test
    public void testAddCharacters() throws SQLException {
        dbHelper.addCharacters(CHARACTERS);
        Assertions.assertTrue(Facts.CHARACTER_COUNT_LOWER_BOUND <= dbHelper.fetchCharacters().size());
    }

    /**
     * Test for {@link DbHelper#buildLatestCharacterName(Character, Chapter)}.
     */
    @Test
    public void testBuildLatestCharacterName() throws SQLException {
        dbHelper.addVolumes(VOLUMES);
        dbHelper.addBooks(BOOKS);
        dbHelper.addChapters(CHAPTERS);
        dbHelper.addCharacters(CHARACTERS);
        Volume1.fillDb(dbHelper);
        Assertions.assertEquals("the Goblin", dbHelper.buildLatestCharacterName(Character.RAGS, dbHelper.fetchChapter("Chapter 1.07")));
        Assertions.assertEquals("the Dragon", dbHelper.buildLatestCharacterName(Character.TERIARCH, dbHelper.fetchChapter("Chapter 1.07")));
        Assertions.assertEquals("Captain Z", dbHelper.buildLatestCharacterName(Character.ZEVARA, dbHelper.fetchChapter("Chapter 1.07")));
        Assertions.assertEquals("Erin Solstice", dbHelper.buildLatestCharacterName(Character.ERIN, dbHelper.fetchChapter("Chapter 1.07")));
    }

}
