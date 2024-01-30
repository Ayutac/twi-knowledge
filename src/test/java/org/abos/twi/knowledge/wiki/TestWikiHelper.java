package org.abos.twi.knowledge.wiki;

import org.abos.twi.knowledge.Facts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public final class TestWikiHelper {

    private WikiHelper wikiHelper;

    @BeforeEach
    public void setup() throws IOException {
        wikiHelper = new WikiHelper();
    }

    @AfterEach
    public void tearDown() {
        wikiHelper = null;
    }

    @Test
    public void testFetchVolumes() throws IOException {
        Assertions.assertEquals(Facts.VOLUME_COUNT, wikiHelper.fetchVolumes().size());
    }

    @Test
    public void testFetchBooks() throws IOException {
        wikiHelper.fetchVolumes();
        Assertions.assertEquals(Facts.BOOK_COUNT, wikiHelper.fetchBooks().size());
    }

    @Test
    public void testFetchChapters() throws IOException {
        wikiHelper.fetchVolumes();
        wikiHelper.fetchBooks();
        Assertions.assertTrue(wikiHelper.fetchChapters().size() >= Facts.CHAPTER_COUNT_LOWER_BOUND);
    }

}
