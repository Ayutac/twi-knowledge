package org.abos.twi.knowledge.db.datafill;

import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.Species;
import org.abos.twi.knowledge.core.location.Landmark;
import org.abos.twi.knowledge.core.location.LandmassOcean;
import org.abos.twi.knowledge.core.location.World;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.DbHelper;

import java.sql.SQLException;

public final class Volume10 {

    private Volume10() {
        /* No instantiation. */
    }

    public static void fillDb(final DbHelper dbHelper) throws SQLException {
        ch00l(dbHelper);
    }

    private static void ch00l(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 10.00 L");
        // appearances/mentions
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.THIRD_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.NANETTE, ch);
    }

}
