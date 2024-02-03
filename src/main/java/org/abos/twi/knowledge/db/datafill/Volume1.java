package org.abos.twi.knowledge.db.datafill;

import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.Species;
import org.abos.twi.knowledge.core.Status;
import org.abos.twi.knowledge.core.event.CharacterStatus;
import org.abos.twi.knowledge.core.event.FirstMeeting;
import org.abos.twi.knowledge.core.event.InnworldArrival;
import org.abos.twi.knowledge.core.location.Landmark;
import org.abos.twi.knowledge.core.location.LandmassOcean;
import org.abos.twi.knowledge.core.location.LandmassOceanType;
import org.abos.twi.knowledge.core.location.World;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.sql.SQLException;

public final class Volume1 {

    public static final World INNWORLD = new World(World.INNWORLD, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(World.INNWORLD));

    public static final World EARTH = new World(World.EARTH, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(World.EARTH));

    public static final LandmassOcean IZRIL = new LandmassOcean(LandmassOcean.IZRIL, LandmassOceanType.CONTINENT, INNWORLD, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(LandmassOcean.IZRIL));

    public static final Landmark FLOODPLAINS = new Landmark(Landmark.FLOODPLAINS, true, IZRIL, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Landmark.FLOODPLAINS));

    public static final Landmark HIGH_PASSES = new Landmark(Landmark.HIGH_PASSES, true, IZRIL, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Landmark.HIGH_PASSES));

    public static final Landmark FIRST_WANDERING_INN = new Landmark("Wandering Inn", false, IZRIL, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Landmark.FIRST_WANDERING_INN));

    public static final Species HUMAN = new Species(Species.HUMAN, true, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Species.HUMAN + "s"));

    public static final Species GOBLIN = new Species(Species.GOBLIN, true, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Species.GOBLIN + "s"));

    public static final Species DRAGON = new Species(Species.DRAGON, false, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Species.DRAGON + "s"));

    public static final Character ERIN = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.ERIN));

    public static final Character TERIARCH = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.TERIARCH));

    private Volume1() {
        /* No instantiation. */
    }

    public static void fillDb(final DbHelper dbHelper) throws SQLException {
        ch00(dbHelper);
        ch01(dbHelper);
        ch02(dbHelper);
        ch03(dbHelper);
        ch04(dbHelper);
        ch05(dbHelper);
        ch06(dbHelper);
        ch07(dbHelper);
        ch08(dbHelper);
        ch09(dbHelper);
        ch10(dbHelper);
        theGreatRitual(dbHelper);
        ch11(dbHelper);
        ch12(dbHelper);
        ch13(dbHelper);
        ch14(dbHelper);
        ch15(dbHelper);
        ch16(dbHelper);
        ch17(dbHelper);
        ch18(dbHelper);
        ch19r(dbHelper);
        ch20r(dbHelper);
        ch21(dbHelper);
        ch22(dbHelper);
        ch23a(dbHelper);
        ch24(dbHelper);
        king(dbHelper);
        ch25(dbHelper);
        ch26r(dbHelper);
        ch27r(dbHelper);
        ch28a(dbHelper);
        ch29(dbHelper);
        ch30(dbHelper);
        ch31(dbHelper);
        ch32r(dbHelper);
        ch33r(dbHelper);
        ch34(dbHelper);
        ch35r(dbHelper);
        ch36(dbHelper);
        ch37(dbHelper);
        ch38(dbHelper);
        ch39r(dbHelper);
        ch40r(dbHelper);
        ch41(dbHelper);
        ch42(dbHelper);
        ch43r(dbHelper);
        ch44r(dbHelper);
        ch45(dbHelper);
        ch46(dbHelper);
        ch47r(dbHelper);
        ch48r(dbHelper);
        ch49(dbHelper);
        ch50(dbHelper);
        ch51(dbHelper);
        ch52r(dbHelper);
        ch53(dbHelper);
        ch54(dbHelper);
        ch55r(dbHelper);
        ch56(dbHelper);
        ch57h(dbHelper);
        ch58h(dbHelper);
        ch59h(dbHelper);
        ch60(dbHelper);
        ch61(dbHelper);
        ch62(dbHelper);
        ch63(dbHelper);
    }

    private static void ch00(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.00");
        // first appearances/mentions
        dbHelper.addWorld(INNWORLD);
        dbHelper.addWorld(EARTH);
        dbHelper.addLandmassOcean(IZRIL);
        dbHelper.addLandmark(FLOODPLAINS);
        dbHelper.addLandmark(HIGH_PASSES);
        dbHelper.addLandmark(FIRST_WANDERING_INN);
        dbHelper.addSpecies(HUMAN);
        dbHelper.addSpecies(GOBLIN);
        dbHelper.addSpecies(DRAGON);
        // events
        dbHelper.addInnworldArrival(new InnworldArrival(ERIN, ch));
        dbHelper.addCharacterStatus(new CharacterStatus(new Status(Status.ALIVE), ERIN, ch));
        dbHelper.addCharacterStatus(new CharacterStatus(new Status(Status.ALIVE), TERIARCH, ch));
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(ERIN, TERIARCH, ch));
        // misc
        dbHelper.addCharacterFirstName(ERIN, ch, "Erin");
        dbHelper.addCharacterLastName(ERIN, ch, "Solstice");
        dbHelper.addCharacterSpecies(ERIN, HUMAN, ch);
        dbHelper.addCharacterSpecies(TERIARCH, DRAGON, ch);
        // appearances/mentions
        dbHelper.addWorldAppearance(INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(IZRIL, ch);
        dbHelper.addLandmarkAppearance(FLOODPLAINS, ch);
        dbHelper.addLandmarkMention(HIGH_PASSES, ch);
        dbHelper.addLandmarkAppearance(FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(HUMAN, ch);
        dbHelper.addSpeciesMention(DRAGON, ch);
        dbHelper.addSpeciesMention(GOBLIN, ch);
        dbHelper.addCharacterAppearance(ERIN, ch);
        dbHelper.addCharacterMention(TERIARCH, ch);
    }

    private static void ch01(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch02(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch03(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch04(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch05(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch06(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch07(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch08(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch09(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch10(final DbHelper dbHelper) throws SQLException {

    }

    private static void theGreatRitual(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch11(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch12(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch13(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch14(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch15(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch16(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch17(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch18(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch19r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch20r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch21(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch22(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch23a(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch24(final DbHelper dbHelper) throws SQLException {

    }

    private static void king(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch25(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch26r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch27r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch28a(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch29(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch30(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch31(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch32r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch33r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch34(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch35r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch36(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch37(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch38(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch39r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch40r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch41(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch42(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch43r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch44r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch45(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch46(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch47r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch48r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch49(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch50(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch51(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch52r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch53(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch54(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch55r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch56(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch57h(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch58h(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch59h(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch60(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch61(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch62(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch63(final DbHelper dbHelper) throws SQLException {

    }

}
