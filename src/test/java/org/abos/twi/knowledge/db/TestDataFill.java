package org.abos.twi.knowledge.db;

import org.abos.twi.knowledge.Facts;
import org.abos.twi.knowledge.db.datafill.Volume1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDataFill {

    /**
     * Tests {@link org.abos.twi.knowledge.db.datafill.Volume1}.
     */
    @Test
    public void testVolume1() {
        Assertions.assertEquals(Facts.CHAPTER_COUNT_VOLUME_1+1, Volume1.class.getDeclaredMethods().length);
    }
}
