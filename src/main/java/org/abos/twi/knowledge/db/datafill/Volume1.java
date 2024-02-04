package org.abos.twi.knowledge.db.datafill;

import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.core.Skill;
import org.abos.twi.knowledge.core.Species;
import org.abos.twi.knowledge.core.Status;
import org.abos.twi.knowledge.core.event.Battle;
import org.abos.twi.knowledge.core.event.CharacterStatus;
import org.abos.twi.knowledge.core.event.FirstMeeting;
import org.abos.twi.knowledge.core.event.InnArrival;
import org.abos.twi.knowledge.core.event.InnworldArrival;
import org.abos.twi.knowledge.core.event.LevelUp;
import org.abos.twi.knowledge.core.location.Landmark;
import org.abos.twi.knowledge.core.location.LandmassOcean;
import org.abos.twi.knowledge.core.location.LandmassOceanType;
import org.abos.twi.knowledge.core.location.Nation;
import org.abos.twi.knowledge.core.location.NationType;
import org.abos.twi.knowledge.core.location.Settlement;
import org.abos.twi.knowledge.core.location.SettlementType;
import org.abos.twi.knowledge.core.location.World;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.sql.SQLException;

public final class Volume1 {

    public static final World INNWORLD = new World(World.INNWORLD, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(World.INNWORLD));

    public static final World EARTH = new World(World.EARTH, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(World.EARTH));

    public static final LandmassOcean BLUE_MOON = new LandmassOcean(LandmassOcean.BLUE_MOON, LandmassOceanType.MOON, INNWORLD, null);

    public static final LandmassOcean GREEN_MOON = new LandmassOcean(LandmassOcean.GREEN_MOON, LandmassOceanType.MOON, INNWORLD, null);

    public static final LandmassOcean NORTH_AMERICA = new LandmassOcean(LandmassOcean.NORTH_AMERICA, LandmassOceanType.CONTINENT, EARTH, null);

    public static final LandmassOcean IZRIL = new LandmassOcean(LandmassOcean.IZRIL, LandmassOceanType.CONTINENT, INNWORLD, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(LandmassOcean.IZRIL));

    public static final LandmassOcean NORTHERN_IZRIL = new LandmassOcean(LandmassOcean.NORTHERN_IZRIL, LandmassOceanType.HALF_CONTINENT, INNWORLD, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(LandmassOcean.IZRIL));

    public static final LandmassOcean SOUTHERN_IZRIL = new LandmassOcean(LandmassOcean.SOUTHERN_IZRIL, LandmassOceanType.HALF_CONTINENT, INNWORLD, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(LandmassOcean.IZRIL));

    public static final Landmark FLOODPLAINS = new Landmark(Landmark.FLOODPLAINS, true, NORTHERN_IZRIL, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Landmark.FLOODPLAINS));

    public static final Landmark HIGH_PASSES = new Landmark(Landmark.HIGH_PASSES, true, IZRIL, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Landmark.HIGH_PASSES));

    public static final Landmark FIRST_WANDERING_INN = new Landmark("Wandering Inn", false, NORTHERN_IZRIL, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Landmark.FIRST_WANDERING_INN));

    public static final Landmark AMENTUS_GROVE = new Landmark(Landmark.AMENTUS_GROVE, true, NORTHERN_IZRIL, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName("Amentus"));

    public static final Landmark TERIARCHS_OLD_HIDEOUT = new Landmark(Landmark.TERIARCHS_OLD_HIDEOUT, true, NORTHERN_IZRIL, null);

    public static final Landmark TERIARCHS_NEW_HIDEOUT = new Landmark(Landmark.TERIARCHS_NEW_HIDEOUT, true, NORTHERN_IZRIL, null);

    public static final Landmark FLOODPLAINS_STREAM = new Landmark(Landmark.FLOODPLAINS_STREAM, true, NORTHERN_IZRIL, null);

    public static final Landmark FLOODED_WATERS_CAVE = new Landmark(Landmark.FLOODED_WATERS_CAVE, true, NORTHERN_IZRIL, null);

    public static final Landmark PISCES_HIDEOUT = new Landmark(Landmark.PISCES_HIDEOUT, true, NORTHERN_IZRIL, null);

    public static final Nation USA = new Nation(Nation.USA, NationType.REPUBLIC, NORTH_AMERICA, null);

    public static final Nation MICHIGAN = new Nation(Nation.MICHIGAN, NationType.REPUBLIC, NORTH_AMERICA, null);

    public static final Nation FIVE_FAMILIES_TERRITORY = new Nation(Nation.FIVE_FAMILIES + " Territory", NationType.OLIGARCHY, NORTHERN_IZRIL, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Nation.FIVE_FAMILIES));

    public static final Nation LISCOR_NATION = new Nation(Nation.LISCOR, NationType.CITY_STATE, NORTHERN_IZRIL, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Nation.LISCOR));

    public static final Settlement CHICAGO = new Settlement(Settlement.CHICAGO, SettlementType.CITY, USA, null);

    public static final Settlement GRAND_RAPIDS = new Settlement(Settlement.GRAND_RAPIDS, SettlementType.CITY, USA, null);

    public static final Settlement LISCOR = new Settlement(Settlement.LISCOR, SettlementType.CITY, LISCOR_NATION, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Settlement.LISCOR));

    public static final Settlement CELUM = new Settlement(Settlement.CELUM, SettlementType.CITY, FIVE_FAMILIES_TERRITORY, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Settlement.CELUM));

    public static final Species HUMAN = new Species(Species.HUMAN, true, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Species.HUMAN + "s"));

    public static final Species GOBLIN = new Species(Species.GOBLIN, true, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Species.GOBLIN + "s"));

    public static final Species DRAGON = new Species(Species.DRAGON, false, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Species.DRAGON + "s"));

    public static final Species HOLLOWSTONE_DECEIVER = new Species(Species.HOLLOWSTONE_DECEIVER, false, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName("Rock Crabs"));

    public static final Species DEMON = new Species(Species.DEMON, true, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Species.DEMON + "s"));

    public static final Species DJINN = new Species(Species.DJINN, false, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Species.DJINN + "i"));

    public static final Species FLATFISH = new Species(Species.FLATFISH, false, null);

    public static final Species ACID_FLY = new Species(Species.ACID_FLY, false, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName("Acid Flies"));

    public static final Species RAZORBEAK = new Species(Species.RAZORBEAK, false, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Species.RAZORBEAK + "s"));

    public static final Species ANTINIUM = new Species(Species.ANTINIUM, true, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Species.ANTINIUM));

    public static final Class INNKEEPER = new Class(Class.INNKEEPER, WikiHelper.WIKI_URL + Class.INNKEEPER + "s");

    public static final Class KNIGHT = new Class(Class.KNIGHT, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Class.KNIGHT + "s"));

    public static final Class HERO = new Class(Class.HERO, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Class.HERO + "es"));

    public static final Class CHIEFTAIN = new Class(Class.CHIEFTAIN, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Class.CHIEFTAIN + "s"));

    public static final Class SHAMAN = new Class(Class.SHAMAN, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Class.SHAMAN + "s"));

    public static final Class GUARDSWOMAN = new Class(Class.GUARDWOMAN, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName("Watch"));

    public static final Skill BASIC_CLEANING = new Skill(Skill.BASIC_CLEANING, null);

    public static final Skill BASIC_COOKING = new Skill(Skill.BASIC_COOKING, null);

    public static final Character ERIN = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.ERIN));

    public static final Character TERIARCH = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.TERIARCH));

    public static final Character AZKERASH = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.AZKERASH));

    public static final Character MAGNOLIA = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.MAGNOLIA));

    public static final Character QUARASS = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.QUARASS));

    public static final Character RAGS = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.RAGS));

    public static final Character PISCES = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.PISCES));

    public static final Character FLOODED_WATERS_CHIEFTAIN = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.FLOODED_WATERS_CHIEFTAIN));

    public static final Character BEILMARK = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.BEILMARK));

    public static final Character RELC = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.RELC));

    public static final Character KLBKCH = new Character(WikiHelper.WIKI_URL + WikiHelper.sanitizePageName(Character.KLBKCH));

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
        dbHelper.addLandmassOcean(NORTHERN_IZRIL);
        dbHelper.addLandmark(FLOODPLAINS);
        dbHelper.addLandmark(HIGH_PASSES);
        dbHelper.addLandmark(FIRST_WANDERING_INN);
        dbHelper.addSpecies(HUMAN);
        dbHelper.addSpecies(GOBLIN);
        dbHelper.addSpecies(DRAGON);
        dbHelper.addClass(INNKEEPER);
        dbHelper.addSkill(BASIC_CLEANING);
        dbHelper.addClassSkill(INNKEEPER, BASIC_CLEANING);
        dbHelper.addSkill(BASIC_COOKING);
        dbHelper.addClassSkill(INNKEEPER, BASIC_COOKING);
        // events
        dbHelper.addInnworldArrival(new InnworldArrival(ERIN, ch));
        dbHelper.addCharacterStatus(new CharacterStatus(new Status(Status.ALIVE), ERIN, ch));
        dbHelper.addCharacterStatus(new CharacterStatus(new Status(Status.ALIVE), TERIARCH, ch));
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(ERIN, TERIARCH, ch));
        dbHelper.addInnArrival(new InnArrival(ERIN, ch));
        final LevelUp lvErin = new LevelUp(ERIN, ch, 1, INNKEEPER, false, false);
        dbHelper.addLevelUp(lvErin);
        dbHelper.addLevelUpSkill(lvErin, BASIC_CLEANING);
        dbHelper.addLevelUpSkill(lvErin, BASIC_COOKING);
        // misc
        dbHelper.addCharacterFirstName(ERIN, ch, "Erin");
        dbHelper.addCharacterLastName(ERIN, ch, "Solstice");
        dbHelper.addCharacterSpecies(ERIN, HUMAN, ch);
        dbHelper.addCharacterSpecies(TERIARCH, DRAGON, ch);
        // appearances/mentions
        dbHelper.addWorldAppearance(INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(FLOODPLAINS, ch);
        dbHelper.addLandmarkMention(HIGH_PASSES, ch);
        dbHelper.addLandmarkAppearance(FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(HUMAN, ch);
        dbHelper.addSpeciesMention(DRAGON, ch);
        dbHelper.addSpeciesMention(GOBLIN, ch);
        dbHelper.addCharacterAppearance(ERIN, ch);
        dbHelper.addCharacterMention(TERIARCH, ch);
        dbHelper.addClassMention(INNKEEPER, ch);
        dbHelper.addSkillMention(BASIC_CLEANING, ch);
        dbHelper.addSkillMention(BASIC_COOKING, ch);
        // chess is mentioned
        // day 1 since Erin arrived
    }

    private static void ch01(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.01");
        // first appearances/mentions
        dbHelper.addLandmassOcean(BLUE_MOON);
        dbHelper.addLandmassOcean(GREEN_MOON);
        dbHelper.addLandmassOcean(NORTH_AMERICA);
        dbHelper.addNation(USA);
        dbHelper.addSettlement(CHICAGO);
        dbHelper.addNation(LISCOR_NATION);
        dbHelper.addSettlement(LISCOR);
        dbHelper.addLandmark(AMENTUS_GROVE);
        dbHelper.addSpecies(HOLLOWSTONE_DECEIVER);
        dbHelper.addLandmark(TERIARCHS_OLD_HIDEOUT);
        dbHelper.addLandmark(TERIARCHS_NEW_HIDEOUT);
        dbHelper.addSpecies(DEMON);
        dbHelper.addSpecies(DJINN);
        dbHelper.addClass(KNIGHT);
        dbHelper.addClass(HERO);
        dbHelper.addNation(FIVE_FAMILIES_TERRITORY);
        dbHelper.addSettlement(CELUM);
        // events
        final Battle rockCrabBattle = new Battle("Erin gets surprised by a Rock Crab", null);
        dbHelper.addBattle(rockCrabBattle);
        dbHelper.addBattleChapter(rockCrabBattle, ch);
        dbHelper.addBattleCharacter(rockCrabBattle, ERIN);
        // appearances/mentions
        dbHelper.addWorldAppearance(INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(FIRST_WANDERING_INN, ch);
        dbHelper.addLandmassOceanMention(BLUE_MOON, ch);
        dbHelper.addLandmassOceanMention(GREEN_MOON, ch);
        dbHelper.addSpeciesAppearance(HUMAN, ch);
        dbHelper.addCharacterAppearance(ERIN, ch);
        dbHelper.addSpeciesMention(GOBLIN, ch);
        // (chess is mentioned)
        // (day 1 and 2 since Erin arrived)
        dbHelper.addWorldMention(EARTH, ch);
        dbHelper.addLandmassOceanMention(NORTH_AMERICA, ch);
        dbHelper.addNationMention(USA, ch);
        dbHelper.addSettlementMention(CHICAGO, ch);
        dbHelper.addNationMention(LISCOR_NATION, ch);
        dbHelper.addSettlementMention(LISCOR, ch);
        dbHelper.addLandmarkAppearance(AMENTUS_GROVE, ch);
        // (Erin picks up an Amentus fruit)
        dbHelper.addSpeciesAppearance(HOLLOWSTONE_DECEIVER, ch);
        dbHelper.addCharacterAppearance(TERIARCH, ch);
        dbHelper.addSpeciesAppearance(DRAGON, ch);
        dbHelper.addLandmarkAppearance(TERIARCHS_OLD_HIDEOUT, ch);
        dbHelper.addLandmarkAppearance(TERIARCHS_NEW_HIDEOUT, ch);
        dbHelper.addCharacterMention(AZKERASH, ch);
        dbHelper.addClassMention(KNIGHT, ch);
        dbHelper.addClassMention(HERO, ch);
        // (Teriarch checks cave from day 1 to day 3 since erin appeared + 2 weeks checking for pursuit)
        dbHelper.addCharacterMention(MAGNOLIA, ch);
        dbHelper.addSpeciesMention(DEMON, ch);
        dbHelper.addSpeciesMention(DJINN, ch);
        dbHelper.addCharacterMention(QUARASS, ch);
        dbHelper.addNationMention(FIVE_FAMILIES_TERRITORY, ch);
        dbHelper.addSettlementMention(CELUM, ch);
    }

    private static void ch02(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.02");
        // first appearances/mentions
        dbHelper.addNation(MICHIGAN);
        dbHelper.addSettlement(GRAND_RAPIDS);
        dbHelper.addLandmark(FLOODPLAINS_STREAM);
        dbHelper.addLandmark(FLOODED_WATERS_CAVE);
        dbHelper.addClass(CHIEFTAIN);
        dbHelper.addClass(SHAMAN);
        dbHelper.addCharacterSpecies(RAGS, GOBLIN, ch);
        dbHelper.addCharacterSpecies(FLOODED_WATERS_CHIEFTAIN, GOBLIN, ch);
        // events
        // (Erin eats Amentus fruits)
        final Battle rockCrabBattle2 = new Battle("Erin defeats the Rock Crab", null);
        dbHelper.addBattle(rockCrabBattle2);
        dbHelper.addBattleChapter(rockCrabBattle2, ch);
        dbHelper.addBattleCharacter(rockCrabBattle2, ERIN);
        // (Chess, Shogi and Go are mentioned)
        final Battle sevenGoblins = new Battle("Erin fights off seven Goblins", null);
        dbHelper.addBattle(sevenGoblins);
        dbHelper.addBattleChapter(sevenGoblins, ch);
        dbHelper.addBattleCharacter(sevenGoblins, ERIN);
        final LevelUp lvErin = new LevelUp(ERIN, ch, 4, dbHelper.fetchClass(Class.INNKEEPER), false, false);
        dbHelper.addLevelUp(lvErin);
        // misc
        dbHelper.addCharacterAge(ERIN, 20, ch);
        dbHelper.addCharacterAge(RAGS, 1, ch);
        // appearances/mentions
        dbHelper.addWorldAppearance(INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(AMENTUS_GROVE, ch);
        dbHelper.addSpeciesAppearance(HUMAN, ch);
        dbHelper.addCharacterAppearance(ERIN, ch);
        dbHelper.addSpeciesAppearance(GOBLIN, ch);
        dbHelper.addNationMention(MICHIGAN, ch);
        dbHelper.addSettlementMention(GRAND_RAPIDS, ch);
        dbHelper.addLandmarkAppearance(FLOODPLAINS_STREAM, ch);
        dbHelper.addLandmarkAppearance(FIRST_WANDERING_INN, ch);
        dbHelper.addCharacterAppearance(RAGS, ch);
        dbHelper.addClassMention(CHIEFTAIN, ch);
        // (appearance of a goblin called Injured Crotch)
        // (appearance of the Flooded Water Tribe)
        dbHelper.addLandmarkAppearance(FLOODED_WATERS_CAVE, ch);
        dbHelper.addCharacterAppearance(FLOODED_WATERS_CHIEFTAIN, ch);
        dbHelper.addClassMention(SHAMAN, ch);
    }

    private static void ch03(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.03");
        // first appearances/mentions
        dbHelper.addSpecies(FLATFISH);
        // events
        final Battle flatfishBattle = new Battle("Erin defeats a flat fish", null);
        dbHelper.addBattle(flatfishBattle);
        dbHelper.addBattleChapter(flatfishBattle, ch);
        dbHelper.addBattleCharacter(flatfishBattle, ERIN);
        // appearances/mentions
        // (day 3 since Erin got to Innworld)
        dbHelper.addWorldAppearance(INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(HUMAN, ch);
        dbHelper.addCharacterAppearance(ERIN, ch);
        dbHelper.addLandmarkAppearance(AMENTUS_GROVE, ch);
        dbHelper.addSpeciesMention(GOBLIN, ch);
        dbHelper.addSpeciesMention(HOLLOWSTONE_DECEIVER, ch);
        dbHelper.addLandmarkAppearance(FLOODPLAINS_STREAM, ch);
        dbHelper.addSkillMention(BASIC_CLEANING, ch);
        dbHelper.addSpeciesAppearance(FLATFISH, ch);
        dbHelper.addSkillMention(BASIC_COOKING, ch);
        // (chess is mentioned)
    }

    private static void ch04(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.04");
        // first appearances/mentions
        dbHelper.addLandmark(PISCES_HIDEOUT);
        // events
        // (Erin destroys the preservation runes)
        final Battle sevenGoblins2 = new Battle("Erin defeats a goblin sneaking through the inn", null);
        dbHelper.addBattle(sevenGoblins2);
        dbHelper.addBattleChapter(sevenGoblins2, ch);
        dbHelper.addBattleCharacter(sevenGoblins2, ERIN);
        // appearances/mentions
        // (day 4 since Erin got to Innworld)
        dbHelper.addWorldAppearance(INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(HUMAN, ch);
        dbHelper.addCharacterAppearance(ERIN, ch);
        dbHelper.addSpeciesAppearance(GOBLIN, ch);
        // (appearance of Injured Crotch, indirectly)
        dbHelper.addCharacterAppearance(RAGS, ch);
        dbHelper.addCharacterAppearance(PISCES, ch);
        dbHelper.addLandmarkMention(PISCES_HIDEOUT, ch);
    }

    private static void ch05(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.05");
        // first appearances/mentions
        dbHelper.addSpecies(ACID_FLY);
        dbHelper.addClass(GUARDSWOMAN);
        dbHelper.addSpecies(RAZORBEAK);
        dbHelper.addSpecies(ANTINIUM);
        // events
        // (Erin makes Pasta)
        // appearances/mentions
        // (day 5 since Erin got to Innworld)
        dbHelper.addWorldAppearance(INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(HUMAN, ch);
        dbHelper.addCharacterAppearance(ERIN, ch);
        dbHelper.addSpeciesMention(GOBLIN, ch);
        dbHelper.addSpeciesAppearance(FLATFISH, ch);
        dbHelper.addSpeciesAppearance(ACID_FLY, ch);
        dbHelper.addLandmarkAppearance(FLOODPLAINS_STREAM, ch);
        dbHelper.addSkillMention(BASIC_COOKING, ch);
        dbHelper.addNationAppearance(LISCOR_NATION, ch);
        dbHelper.addSettlementAppearance(LISCOR, ch);
        // (appearance of unnamed guard)
        dbHelper.addClassMention(GUARDSWOMAN, ch);
        dbHelper.addCharacterAppearance(BEILMARK, ch);
        // (Beilmark is a [Guardwoman])
        dbHelper.addCharacterMention(RELC, ch);
        dbHelper.addSpeciesAppearance(RAZORBEAK, ch);
        dbHelper.addSpeciesAppearance(ANTINIUM, ch);
        dbHelper.addCharacterAppearance(KLBKCH, ch);
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
