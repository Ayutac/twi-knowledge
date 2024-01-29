package org.abos.twi.knowledge.wiki;

import org.abos.twi.knowledge.Facts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public final class TestWikiHelper {

    @Test
    public void testFetchVolumes() throws IOException {
        Assertions.assertEquals(Facts.VOLUME_COUNT, WikiHelper.fetchVolumes().size());
    }

    @Test
    public void testFetchBooks() throws IOException {
        Assertions.assertEquals(Facts.BOOK_COUNT, WikiHelper.fetchBooks(WikiHelper.fetchVolumes()).size());
    }

}
