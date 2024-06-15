package org.abos.twi.knowledge.db.datafill;

import org.abos.common.LogUtil;
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
import org.abos.twi.knowledge.core.location.Nation;
import org.abos.twi.knowledge.core.location.Settlement;
import org.abos.twi.knowledge.core.location.World;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.DbHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public final class Volume1 {

    private static final Logger LOGGER = LogManager.getLogger(Volume1.class);

    public static final Battle PISCES_FIGHT = new Battle("Erin vs. Pisces", null);
    private static final Battle CHIEFTAIN_BATTLE = new Battle("Erin vs. Goblin Chieftain", null);

    private Volume1() {
        /* No instantiation. */
    }

    public static void fillDb(final DbHelper dbHelper) throws SQLException {
        LOGGER.info("Volume 1 data filling...");
        final Instant start = Instant.now();
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
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Volume 1 data filling", time.toMinutes(), time.toSecondsPart());
    }

    private static void ch00(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.00");
        // events
        dbHelper.addInnworldArrival(new InnworldArrival(Character.ERIN, ch));
        dbHelper.addCharacterStatus(new CharacterStatus(new Status(Status.ALIVE), Character.ERIN, ch));
        dbHelper.addCharacterStatus(new CharacterStatus(new Status(Status.ALIVE), Character.TERIARCH, ch));
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.ERIN, Character.TERIARCH, ch));
        dbHelper.addInnArrival(new InnArrival(Character.ERIN, ch));
        final LevelUp lvErin = new LevelUp(Character.ERIN, ch, 1, Class.INNKEEPER, false, false);
        dbHelper.addLevelUp(lvErin);
        dbHelper.addLevelUpSkill(lvErin, Skill.BASIC_CLEANING);
        dbHelper.addLevelUpSkill(lvErin, Skill.BASIC_COOKING);
        // misc
        dbHelper.addCharacterFirstName(Character.ERIN, ch, "Erin");
        dbHelper.addCharacterLastName(Character.ERIN, ch, "Solstice");
        dbHelper.addCharacterSpecies(Character.ERIN, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.TERIARCH, Species.DRAGON, ch);
        dbHelper.addClassSkill(Class.INNKEEPER, Skill.BASIC_CLEANING, ch);
        dbHelper.addClassSkill(Class.INNKEEPER, Skill.BASIC_COOKING, ch);
        // appearances/mentions
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkMention(Landmark.HIGH_PASSES, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addSpeciesMention(Species.DRAGON, ch);
        dbHelper.addSpeciesMention(Species.GOBLIN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addCharacterMention(Character.TERIARCH, ch);
        dbHelper.addClassMention(Class.INNKEEPER, ch);
        dbHelper.addSkillMention(Skill.BASIC_CLEANING, ch);
        dbHelper.addSkillMention(Skill.BASIC_COOKING, ch);
        // chess is mentioned
        // day 1 since Erin arrived
    }

    private static void ch01(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.01");
        // events
        final Battle rockCrabBattle = new Battle("Erin gets surprised by a Rock Crab", null);
        dbHelper.addBattle(rockCrabBattle);
        dbHelper.addBattleChapter(rockCrabBattle, ch);
        dbHelper.addBattleCharacter(rockCrabBattle, Character.ERIN);
        // appearances/mentions
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.BLUE_MOON, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.GREEN_MOON, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addSpeciesMention(Species.GOBLIN, ch);
        // (chess is mentioned)
        // (day 1 and 2 since Erin arrived)
        dbHelper.addWorldMention(World.EARTH, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.NORTH_AMERICA, ch);
        dbHelper.addNationMention(Nation.USA, ch);
        dbHelper.addSettlementMention(Settlement.CHICAGO, ch);
        dbHelper.addNationMention(Nation.LISCOR, ch);
        dbHelper.addSettlementMention(Settlement.LISCOR, ch);
        dbHelper.addLandmarkAppearance(Landmark.AMENTUS_GROVE, ch);
        // (Erin picks up an Amentus fruit)
        dbHelper.addSpeciesAppearance(Species.HOLLOWSTONE_DECEIVER, ch);
        dbHelper.addCharacterAppearance(Character.TERIARCH, ch);
        dbHelper.addSpeciesAppearance(Species.DRAGON, ch);
        dbHelper.addLandmarkAppearance(Landmark.TERIARCHS_OLD_HIDEOUT, ch);
        dbHelper.addLandmarkAppearance(Landmark.TERIARCHS_NEW_HIDEOUT, ch);
        dbHelper.addCharacterMention(Character.AZKERASH, ch);
        dbHelper.addCharacterNickName(Character.AZKERASH, ch, "The Necromancer");
        dbHelper.addClassMention(Class.KNIGHT, ch);
        dbHelper.addClassMention(Class.HERO, ch);
        // (Teriarch checks cave from day 1 to day 3 since erin appeared + 2 weeks checking for pursuit)
        dbHelper.addCharacterMention(Character.MAGNOLIA, ch);
        dbHelper.addSpeciesMention(Species.DEMON, ch);
        dbHelper.addSpeciesMention(Species.DJINNI, ch);
        dbHelper.addCharacterMention(Character.QUARASS, ch);
        dbHelper.addCharacterNickName(Character.QUARASS, ch, "The Quarass");
        dbHelper.addNationMention(Nation.FIVE_FAMILIES_TERRITORY, ch);
        dbHelper.addSettlementMention(Settlement.CELUM, ch);
    }

    private static void ch02(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.02");
        // events
        // (Erin eats Amentus fruits)
        final Battle rockCrabBattle2 = new Battle("Erin defeats the Rock Crab", null);
        dbHelper.addBattle(rockCrabBattle2);
        dbHelper.addBattleChapter(rockCrabBattle2, ch);
        dbHelper.addBattleCharacter(rockCrabBattle2, Character.ERIN);
        // (Chess, Shogi and Go are mentioned)
        final Battle sevenGoblins = new Battle("Erin fights off seven Goblins", null);
        dbHelper.addBattle(sevenGoblins);
        dbHelper.addBattleChapter(sevenGoblins, ch);
        dbHelper.addBattleCharacter(sevenGoblins, Character.ERIN);
        final LevelUp lvErin = new LevelUp(Character.ERIN, ch, 4, dbHelper.fetchClass("Innkeeper"), false, false);
        dbHelper.addLevelUp(lvErin);
        // misc
        dbHelper.addCharacterAge(Character.ERIN, 20, ch);
        dbHelper.addCharacterAge(Character.RAGS, 1, ch);
        dbHelper.addCharacterSpecies(Character.RAGS, Species.GOBLIN, ch);
        dbHelper.addCharacterSpecies(Character.FLOODED_WATERS_CHIEFTAIN, Species.GOBLIN, ch);
        // appearances/mentions
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.AMENTUS_GROVE, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        dbHelper.addNationMention(Nation.MICHIGAN, ch);
        dbHelper.addSettlementMention(Settlement.GRAND_RAPIDS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS_STREAM, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addCharacterAppearance(Character.RAGS, ch);
        dbHelper.addClassMention(Class.CHIEFTAIN, ch);
        // (appearance of a goblin called Injured Crotch)
        // (appearance of the Flooded Water Tribe)
        dbHelper.addLandmarkAppearance(Landmark.FLOODED_WATERS_CAVE, ch);
        dbHelper.addCharacterAppearance(Character.FLOODED_WATERS_CHIEFTAIN, ch);
        dbHelper.addClassMention(Class.SHAMAN, ch);
    }

    private static void ch03(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.03");
        // events
        final Battle flatfishBattle = new Battle("Erin defeats a flat fish", null);
        dbHelper.addBattle(flatfishBattle);
        dbHelper.addBattleChapter(flatfishBattle, ch);
        dbHelper.addBattleCharacter(flatfishBattle, Character.ERIN);
        // appearances/mentions
        // (day 3 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addLandmarkAppearance(Landmark.AMENTUS_GROVE, ch);
        dbHelper.addSpeciesMention(Species.GOBLIN, ch);
        dbHelper.addSpeciesMention(Species.HOLLOWSTONE_DECEIVER, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS_STREAM, ch);
        dbHelper.addSkillMention(Skill.BASIC_CLEANING, ch);
        dbHelper.addSpeciesAppearance(Species.FLATFISH, ch);
        dbHelper.addSkillMention(Skill.BASIC_COOKING, ch);
        // (chess is mentioned)
    }

    private static void ch04(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.04");
        // events
        // (Erin destroys the preservation runes)
        final Battle sevenGoblins2 = new Battle("Erin defeats a goblin sneaking through the inn", null);
        dbHelper.addBattle(sevenGoblins2);
        dbHelper.addBattleChapter(sevenGoblins2, ch);
        dbHelper.addBattleCharacter(sevenGoblins2, Character.ERIN);
        // appearances/mentions
        // (day 4 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        // (appearance of Injured Crotch, indirectly)
        dbHelper.addCharacterAppearance(Character.RAGS, ch);
        dbHelper.addCharacterAppearance(Character.PISCES, ch);
        dbHelper.addLandmarkMention(Landmark.PISCES_HIDEOUT, ch);
    }

    private static void ch05(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.05");
        // events
        // (Erin makes Pasta)
        dbHelper.addInnArrival(new InnArrival(Character.KLBKCH, ch));
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.ERIN, Character.KLBKCH, ch));
        // appearances/mentions
        // (day 5 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addSpeciesMention(Species.GOBLIN, ch);
        dbHelper.addSpeciesAppearance(Species.FLATFISH, ch);
        dbHelper.addSpeciesAppearance(Species.ACID_FLY, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS_STREAM, ch);
        dbHelper.addSkillMention(Skill.BASIC_COOKING, ch);
        dbHelper.addNationAppearance(Nation.LISCOR, ch);
        dbHelper.addSettlementAppearance(Settlement.LISCOR, ch);
        // (appearance of unnamed guard)
        dbHelper.addClassMention(Class.GUARDSWOMAN, ch);
        dbHelper.addCharacterAppearance(Character.BEILMARK, ch);
        dbHelper.addCharacterFirstName(Character.BEILMARK, ch, "Beilmark");
        // (Beilmark is a [Guardwoman])
        dbHelper.addCharacterMention(Character.RELC, ch);
        dbHelper.addCharacterMention(Character.ZEVARA, ch);
        dbHelper.addSpeciesAppearance(Species.RAZORBEAK, ch);
        dbHelper.addSpeciesAppearance(Species.ANTINIUM, ch);
        // note that Klbkch is Centenium?
        dbHelper.addCharacterAppearance(Character.KLBKCH, ch);
        dbHelper.addCharacterSpecies(Character.KLBKCH, Species.ANTINIUM, ch);
    }

    private static void ch06(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.06");
        // events
        dbHelper.addInnArrival(new InnArrival(Character.RELC, ch));
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.ERIN, Character.RELC, ch));
        final LevelUp lvErin = new LevelUp(Character.ERIN, ch, 5, Class.INNKEEPER, false, false);
        dbHelper.addLevelUp(lvErin);
        dbHelper.addLevelUpSkill(lvErin, Skill.BASIC_CRAFTING);
        // (healing potion is used)
        // misc
        dbHelper.addClassSkill(Class.INNKEEPER, Skill.BASIC_CRAFTING, ch);
        // appearances/mentions
        // (still day 5 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.ANTINIUM, ch);
        dbHelper.addCharacterAppearance(Character.KLBKCH, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addSpeciesMention(Species.DRAGON, ch);
        dbHelper.addSpeciesAppearance(Species.DRAKE, ch);
        dbHelper.addCharacterAppearance(Character.RELC, ch);
        dbHelper.addCharacterSpecies(Character.RELC, Species.DRAKE, ch);
        // mention of subspecies Oldblood
        // appearance of Liscors Watch
        dbHelper.addCharacterMention(Character.ZEVARA, ch);
        dbHelper.addNationMention(Nation.MICHIGAN, ch);
        dbHelper.addSkillMention(Skill.TELEPORTATION, ch);
        // mention of Mage Guild
        dbHelper.addSpeciesMention(Species.GOBLIN, ch);
        dbHelper.addSpeciesMention(Species.RAZORBEAK, ch);
        dbHelper.addSpeciesMention(Species.HOLLOWSTONE_DECEIVER, ch);
        dbHelper.addCharacterNickName(Character.KLBKCH, ch, "Klbkch");
        dbHelper.addCharacterNickName(Character.KLBKCH, ch, "Klb");
        dbHelper.addCharacterFirstName(Character.RELC, ch, "Relc");
        dbHelper.addClassMention(Class.CHEF, ch);
        dbHelper.addClassMention(Class.INNKEEPER, ch);
        // Relc is [Spearmaster], Klbkch is [Swordslayer], both are [Guardsman]
        dbHelper.addClassMention(Class.GUARDSMAN, ch);
        dbHelper.addClassMention(Class.SPEARMASTER, ch);
        dbHelper.addClassMention(Class.SWORDSLAYER, ch);
        dbHelper.addClassMention(Class.WARRIOR, ch);
        dbHelper.addClassMention(Class.SURVIVOR, ch);
        dbHelper.addClassMention(Class.LORD, ch);
        dbHelper.addClassMention(Class.LADY, ch);
        dbHelper.addClassMention(Class.GOOD_PERSON, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.SOUTHERN_IZRIL, ch);
        dbHelper.addNationMention(Nation.PALLASS, ch);
        dbHelper.addSettlementMention(Settlement.PALLASS, ch);
        dbHelper.addLandmarkMention(Landmark.BLOODFIELDS, ch);
        dbHelper.addSkillMention(Skill.BASIC_CRAFTING, ch);
    }

    private static void ch07(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.07");
        // events
        // (Erin crafts a basket out of grass)
        final Battle razorbeak = new Battle("Erin fights a Razorbeak for its eggs", null);
        dbHelper.addBattle(razorbeak);
        dbHelper.addBattleChapter(razorbeak, ch);
        dbHelper.addBattleCharacter(razorbeak, Character.ERIN);
        dbHelper.addInnArrival(new InnArrival(Character.PISCES, ch));
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.ERIN, Character.PISCES, ch));
        dbHelper.addBattle(PISCES_FIGHT);
        dbHelper.addBattleChapter(PISCES_FIGHT, ch);
        dbHelper.addBattleCharacter(PISCES_FIGHT, Character.ERIN);
        dbHelper.addBattleCharacter(PISCES_FIGHT, Character.PISCES);
        // misc
        dbHelper.addClassSkill(Class.GATHERER, Skill.DETECT_POISON, ch);
        // appearances/mentions
        // (day 6 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        // (mention of healing potion)
        dbHelper.addClassMention(Class.INNKEEPER, ch);
        dbHelper.addSkillMention(Skill.BASIC_CRAFTING, ch);
        dbHelper.addLandmarkAppearance(Landmark.AMENTUS_GROVE, ch);
        dbHelper.addSpeciesAppearance(Species.RAZORBEAK, ch);
        dbHelper.addSpeciesAppearance(Species.HOLLOWSTONE_DECEIVER, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS_STREAM, ch);
        dbHelper.addSpeciesAppearance(Species.FLATFISH, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        dbHelper.addCharacterAppearance(Character.RAGS, ch);
        dbHelper.addSpeciesAppearance(Species.ANTINIUM, ch);
        dbHelper.addClassMention(Class.GUARD, ch);
        dbHelper.addClassMention(Class.CHIEFTAIN, ch);
        dbHelper.addClassMention(Class.GATHERER, ch);
        dbHelper.addSkillMention(Skill.DETECT_POISON, ch);
        dbHelper.addSkillMention(Skill.BAD_FRUIT_DETECTOR, ch);
        dbHelper.addCharacterAppearance(Character.KLBKCH, ch);
        dbHelper.addSpeciesAppearance(Species.DRAKE, ch);
        dbHelper.addCharacterAppearance(Character.RELC, ch);
        dbHelper.addCharacterNickName(Character.ZEVARA, ch, "Captain Z");
        dbHelper.addCharacterAppearance(Character.ZEVARA, ch);
        dbHelper.addCharacterAppearance(Character.PISCES, ch);
    }

    private static void ch08(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.08");
        // events
        dbHelper.addBattleChapter(PISCES_FIGHT, ch);
        // (Pisces eats Amentus and Pasta)
        // misc
        dbHelper.addCharacterSpecies(Character.PISCES, Species.HUMAN, ch);
        dbHelper.addCharacterFirstName(Character.PISCES, ch, "Pisces");
        // appearances/mentions
        // (still day 6 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.PISCES, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addClassMention(Class.INNKEEPER, ch);
        dbHelper.addSkillMention(Skill.TELEPORTATION, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.WISTRAM_ISLE, ch);
        dbHelper.addNationMention(Nation.WISTRAM, ch);
        dbHelper.addSettlementMention(Settlement.WISTRAM, ch);
    }

    private static void ch09(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.09");
        // events
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.PISCES, Character.KLBKCH, ch));
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.PISCES, Character.RELC, ch));
        final Battle pisces = new Battle("Relc and Klbkch apprehend Pisces", null);
        dbHelper.addBattle(pisces);
        dbHelper.addBattleCharacter(pisces, Character.PISCES);
        dbHelper.addBattleCharacter(pisces, Character.RELC);
        dbHelper.addBattleCharacter(pisces, Character.KLBKCH);
        dbHelper.addBattleChapter(pisces, ch);
        // misc
        dbHelper.addClassSkill(Class.GUARDSMAN, Skill.DETECT_GUILT, ch);
        dbHelper.addClassSkill(Class.GUARDSMAN, Skill.DANGERSENSE, ch);
        dbHelper.addClassSkill(Class.MAGE, Skill.FIREBALL, ch);
        dbHelper.addClassSkill(Class.MAGE, Skill.FLASH_STEP, ch);
        // implicitly:
        dbHelper.addClassSkill(Class.GUARD, Skill.DETECT_GUILT, ch);
        dbHelper.addClassSkill(Class.GUARDSWOMAN, Skill.DETECT_GUILT, ch);
        dbHelper.addClassSkill(Class.GUARD, Skill.DANGERSENSE, ch);
        dbHelper.addClassSkill(Class.GUARDSWOMAN, Skill.DANGERSENSE, ch);
        // (Pisces is a [Necromancer])
        // appearances/mentions
        // (day 7 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addCharacterAppearance(Character.PISCES, ch);
        dbHelper.addClassMention(Class.NECROMANCER, ch);
        dbHelper.addClassMention(Class.INNKEEPER, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.SOUTHERN_IZRIL, ch);
        dbHelper.addNationMention(Nation.PALLASS, ch);
        dbHelper.addSettlementMention(Settlement.PALLASS, ch);
        dbHelper.addClassMention(Class.MAGE, ch);
        dbHelper.addClassMention(Class.SORCERER, ch);
        dbHelper.addClassMention(Class.WARLOCK, ch);
        dbHelper.addClassMention(Class.WITCH, ch);
        dbHelper.addClassMention(Class.WIZARD, ch);
        dbHelper.addSpeciesAppearance(Species.DRAKE, ch);
        dbHelper.addCharacterAppearance(Character.RELC, ch);
        dbHelper.addSpeciesAppearance(Species.ANTINIUM, ch);
        dbHelper.addCharacterAppearance(Character.KLBKCH, ch);
        // appearance/mention of the watch
        dbHelper.addSkillMention(Skill.BASIC_CRAFTING, ch);
        dbHelper.addSettlementMention(Settlement.LISCOR, ch);
        dbHelper.addClassMention(Class.GUARDSMAN, ch);
        dbHelper.addClassMention(Class.GUARD, ch);
        dbHelper.addSkillMention(Skill.DETECT_GUILT, ch);
        // (Klbkch has [Detect Guilt])
        dbHelper.addCharacterMention(Character.ZEVARA, ch);
        dbHelper.addSkillMention(Skill.FIREBALL, ch);
        dbHelper.addSkillMention(Skill.FLASH_STEP, ch);
        dbHelper.addSkillMention(Skill.DANGERSENSE, ch);
        // (Relc has [Dangersense])
        dbHelper.addCharacterNickName(Character.KLBKCH, ch, "The Slayer");
    }

    private static void ch10(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.10");
        // events
        final Battle pisces2 = new Battle("Relc and Klbkch apprehend Pisces again.", null);
        dbHelper.addBattle(pisces2);
        dbHelper.addBattleChapter(pisces2, ch);
        dbHelper.addBattleCharacter(pisces2, Character.PISCES);
        dbHelper.addBattleCharacter(pisces2, Character.RELC);
        dbHelper.addBattleCharacter(pisces2, Character.KLBKCH);
        // misc
        dbHelper.addClassSkill(Class.MAGE, Skill.BARRIER_OF_AIR, ch);
        dbHelper.addClassSkill(Class.MAGE, Skill.APPRAISAL, ch);
        dbHelper.addClassSkill(Class.MAGE, Skill.DETECT_TRUTH, ch);
        // appearances/mentions
        // (still day 7 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addClassMention(Class.INNKEEPER, ch);
        dbHelper.addCharacterAppearance(Character.PISCES, ch);
        dbHelper.addClassMention(Class.MAGE, ch);
        dbHelper.addClassMention(Class.NECROMANCER, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.TERANDRIA, ch);
        dbHelper.addCharacterNickName(Character.AZKERASH, ch, "Necromancer of Terandria");
        dbHelper.addCharacterNickName(Character.AZKERASH, ch, "Az’kerash");
        dbHelper.addSpeciesAppearance(Species.DRAKE, ch);
        dbHelper.addCharacterAppearance(Character.RELC, ch);
        dbHelper.addSpeciesAppearance(Species.ANTINIUM, ch);
        dbHelper.addCharacterAppearance(Character.KLBKCH, ch);
        dbHelper.addSkillMention(Skill.BARRIER_OF_AIR, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.WISTRAM_ISLE, ch);
        dbHelper.addNationMention(Nation.WISTRAM, ch);
        dbHelper.addSettlementMention(Settlement.WISTRAM, ch);
        // (mention of Wistram Graduate)
        dbHelper.addClassMention(Class.GUARDSMAN, ch);
        dbHelper.addCharacterMention(Character.ZEL, ch);
        dbHelper.addCharacterNickName(Character.ZEL, ch, "The Tidebreaker");
        dbHelper.addCharacterMention(Character.GEWILENA, ch);
        dbHelper.addSkillMention(Skill.APPRAISAL, ch);
        dbHelper.addCharacterMention(Character.ZEVARA, ch);
        dbHelper.addSkillMention(Skill.DETECT_TRUTH, ch);
    }

    private static void theGreatRitual(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Interlude – The Great Ritual");
        // events
        // (the Great Ritual)
        dbHelper.addInnworldArrival(new InnworldArrival(Character.CHOLE, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.CYNTHIA, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.EDDY, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.EMILY, ch));
//        dbHelper.addInnworldArrival(new InnworldArrival(Character.KATY, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.KEITH, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.MARIAN_US, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.RED, ch));
//        dbHelper.addInnworldArrival(new InnworldArrival(Character.REYANNE, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.RICHARD, ch));
//        dbHelper.addInnworldArrival(new InnworldArrival(Character.RON, ch));
//        dbHelper.addInnworldArrival(new InnworldArrival(Character.STACY, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.TOM, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.VINCENT, ch));
        dbHelper.maybeAddFirstMeetings(FirstMeeting.generateMeetings(
                new Character[]{Character.CHOLE, Character.CYNTHIA, Character.EDDY, Character.EMILY, Character.KEITH, Character.MARIAN_US, Character.RED, Character.RICHARD, Character.TOM, Character.VINCENT},
                new Character[]{Character.OTHIUS, Character.CORETINE, Character.NERESHAL, Character.HAYVON}, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.KEVIN, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.TROYDEL, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.GALINA, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.ROSE, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.JOSEPH, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.LEON, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.RYOKA, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.TREY, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.TERES, ch));
        dbHelper.maybeAddFirstMeetings(FirstMeeting.generateMeetings(
                new Character[]{Character.TERES, Character.TREY},
                new Character[]{Character.FLOS, Character.ORTHENON}, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.LILY, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.CARA, ch));
        dbHelper.addInnworldArrival(new InnworldArrival(Character.LAKEN, ch));
        // misc
        dbHelper.addClassUpgrade(Class.MAGE, Class.HIGH_MAGE, ch);
        dbHelper.addCharacterNickName(Character.OTHIUS, ch, "Blighted King");
        dbHelper.addCharacterNickName(Character.CORETINE, ch, "Blighted Queen");
        dbHelper.addCharacterSpecies(Character.CHOLE, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.CYNTHIA, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.EDDY, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.EMILY, Species.HUMAN, ch);
//        dbHelper.addCharacterSpecies(Character.KATY, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.KEITH, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.MARIAN_US, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.RED, Species.HUMAN, ch);
//        dbHelper.addCharacterSpecies(Character.REYANNE, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.RICHARD, Species.HUMAN, ch);
//        dbHelper.addCharacterSpecies(Character.RON, Species.HUMAN, ch);
//        dbHelper.addCharacterSpecies(Character.STACY, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.TOM, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.VINCENT, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.KEVIN, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.JOSEPH, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.GALINA, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.ROSE, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.TROYDEL, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.LEON, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.RYOKA, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.TREY, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.TERES, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.LILY, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.CARA, Species.HUMAN, ch);
        dbHelper.addCharacterSpecies(Character.LAKEN, Species.HUMAN, ch);
        // appearances/mentions
        // (still day 7 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        dbHelper.addCharacterAppearance(Character.RAGS, ch);
        dbHelper.addClassMention(Class.NECROMANCER, ch);
        dbHelper.addLandmarkMention(Landmark.PISCES_HIDEOUT, ch);
        dbHelper.addCharacterAppearance(Character.PISCES, ch);
        dbHelper.addSpeciesMention(Species.DRAKE, ch);
        dbHelper.addCharacterMention(Character.RELC, ch);
        dbHelper.addSpeciesMention(Species.ANTINIUM, ch);
        dbHelper.addCharacterMention(Character.KLBKCH, ch);
        dbHelper.addClassMention(Class.MAGE, ch);
        dbHelper.addClassMention(Class.SORCERER, ch);
        dbHelper.addSpeciesMention(Species.DRAGON, ch);
        // (getting to day 8)
        dbHelper.addLandmassOceanAppearance(LandmassOcean.RHIR, ch);
        dbHelper.addNationAppearance(Nation.BLIGHTED_KINGDOM, ch);
        dbHelper.addSettlementAppearance(Settlement.PARANFER, ch);
        dbHelper.addSpeciesAppearance(Species.HALF_ELF, ch);
        dbHelper.addSpeciesAppearance(Species.GARUDA, ch);
        dbHelper.addSpeciesAppearance(Species.DULLAHAN, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.BLUE_MOON, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.GREEN_MOON, ch);
        dbHelper.addClassMention(Class.HIGH_MAGE, ch);
        dbHelper.addClassMention(Class.SCHOLAR, ch);
        // (Othius is a [King], Cortine a [Queen], Nereshal a "[Mage]")
        dbHelper.addClassMention(Class.KING, ch);
        dbHelper.addCharacterAppearance(Character.OTHIUS, ch);
        dbHelper.addCharacterAppearance(Character.CORETINE, ch);
        dbHelper.addCharacterAppearance(Character.NERESHAL, ch);
        dbHelper.addClassMention(Class.LORD, ch);
        dbHelper.addCharacterAppearance(Character.HAYVON, ch);
        dbHelper.addClassMention(Class.QUEEN, ch);
        dbHelper.addClassMention(Class.HERO, ch);
        dbHelper.addCharacterAppearance(Character.CHOLE, ch);
        dbHelper.addCharacterAppearance(Character.CYNTHIA, ch);
        dbHelper.addCharacterAppearance(Character.EDDY, ch);
        dbHelper.addCharacterAppearance(Character.EMILY, ch);
//        dbHelper.addCharacterAppearance(Character.KATY, ch);
        dbHelper.addCharacterAppearance(Character.KEITH, ch);
        dbHelper.addCharacterAppearance(Character.MARIAN_US, ch);
        dbHelper.addCharacterAppearance(Character.RED, ch);
//        dbHelper.addCharacterAppearance(Character.REYANNE, ch);
        dbHelper.addCharacterAppearance(Character.RICHARD, ch);
//        dbHelper.addCharacterAppearance(Character.RON, ch);
//        dbHelper.addCharacterAppearance(Character.STACY, ch);
        dbHelper.addCharacterAppearance(Character.TOM, ch);
        dbHelper.addCharacterAppearance(Character.VINCENT, ch);
        dbHelper.addSpeciesMention(Species.GOBLIN, ch);
        dbHelper.addClassMention(Class.SNEAK_THIEF, ch);
        dbHelper.addClassMention(Class.SWORDSMAN, ch);
        dbHelper.addClassMention(Class.BANDIT, ch);
        dbHelper.addClassMention(Class.ROGUE, ch);
        dbHelper.addCharacterAppearance(Character.KEVIN, ch);
        dbHelper.addCharacterAppearance(Character.JOSEPH, ch);
        dbHelper.addCharacterAppearance(Character.GALINA, ch);
        dbHelper.addCharacterAppearance(Character.ROSE, ch);
        dbHelper.addCharacterAppearance(Character.TROYDEL, ch);
        dbHelper.addCharacterAppearance(Character.LEON, ch);
        dbHelper.addSettlementMention(Settlement.INVRISIL, ch);
        dbHelper.addSettlementAppearance(Settlement.CELUM, ch);
        // (appearance of one of Celum's Runner Guild's receptionists)
        dbHelper.addClassMention(Class.RECEPTIONIST, ch);
        dbHelper.addCharacterAppearance(Character.RYOKA, ch);
        dbHelper.addWorldAppearance(World.EARTH, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.GREAT_BRITAIN, ch);
        dbHelper.addNationAppearance(Nation.UK, ch);
        dbHelper.addSettlementAppearance(Settlement.LONDON, ch);
        dbHelper.addCharacterAppearance(Character.TREY, ch);
        dbHelper.addCharacterAppearance(Character.TERES, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.CHANDRAR, ch);
        dbHelper.addNationAppearance(Nation.REIM, ch);
        dbHelper.addSettlementAppearance(Settlement.REIM, ch);
        dbHelper.addCharacterAppearance(Character.FLOS, ch);
        dbHelper.addCharacterAppearance(Character.ORTHENON, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.TERANDRIA, ch);
        dbHelper.addNationAppearance(Nation.KALIV, ch);
        dbHelper.addCharacterAppearance(Character.LILY, ch);
        dbHelper.addNationAppearance(Nation.NOELICTUS, ch);
        dbHelper.addCharacterAppearance(Character.CARA, ch);
        dbHelper.addCharacterAppearance(Character.LAKEN, ch);
        dbHelper.addCharacterAppearance(Character.DURENE, ch);
    }

    private static void ch11(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.11");
        // events
        // (Erin meets an unnamed guard and unnamed hunter)
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.ERIN, Character.BELSC, ch));
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.ERIN, Character.DRASSI, ch));
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.ERIN, Character.SELYS, ch));
        // misc
        dbHelper.addCharacterSpecies(Character.BELSC, Species.DRAKE, ch);
        dbHelper.addCharacterSpecies(Character.DRASSI, Species.DRAKE, ch);
        dbHelper.addCharacterFirstName(Character.DRASSI, ch, "Drassi");
        dbHelper.addCharacterSpecies(Character.SELYS, Species.DRAKE, ch);
        dbHelper.addCharacterFirstName(Character.SELYS, ch, "Selys");
        // appearances/mentions
        // (still day 8 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addClassMention(Class.NECROMANCER, ch);
        dbHelper.addSpeciesMention(Species.HOLLOWSTONE_DECEIVER, ch);
        dbHelper.addClassMention(Class.INNKEEPER, ch);
        dbHelper.addCharacterMention(Character.ARTHUR, ch);
        dbHelper.addCharacterMention(Character.RELC, ch);
        dbHelper.addSpeciesMention(Species.ANTINIUM, ch);
        dbHelper.addCharacterMention(Character.KLBKCH, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        dbHelper.addSpeciesMention(Species.HORSE, ch);
        dbHelper.addSpeciesMention(Species.COCKROACH, ch);
        dbHelper.addCharacterAppearance(Character.BELSC, ch);
        dbHelper.addNationAppearance(Nation.LISCOR, ch);
        dbHelper.addSettlementAppearance(Settlement.LISCOR, ch);
        dbHelper.addClassMention(Class.GUARD, ch);
        dbHelper.addClassMention(Class.TRADER, ch);
        dbHelper.addClassMention(Class.HUNTER, ch);
        // (appearance of hunter Gnoll)
        dbHelper.addSpeciesAppearance(Species.GNOLL, ch);
        dbHelper.addSpeciesAppearance(Species.CORUSDEER, ch);
        dbHelper.addSkillMention(Skill.BROADER_SHOULDERS, ch);
        dbHelper.addClassMention(Class.GUARDSMAN, ch);
        dbHelper.addClassMention(Class.BUTCHER, ch);
        dbHelper.addSpeciesMention(Species.ACID_FLY, ch);
        dbHelper.addCharacterAppearance(Character.DRASSI, ch);
        // (appearance of silverfang tribe)
        dbHelper.addCharacterMention(Character.HAWK, ch);
        // (appearance of Mister Drells)
        dbHelper.addLandmassOceanMention(LandmassOcean.EUROPE, ch);
        // (appearance of [Driver] Drake)
        dbHelper.addClassMention(Class.DRIVER, ch);
        dbHelper.addClassMention(Class.BARMAID, ch);
        dbHelper.addLandmarkAppearance(Landmark.LISCORS_MAGES_GUILD, ch);
        dbHelper.addSpeciesMention(Species.SHIELD_SPIDER, ch);
        dbHelper.addClassMention(Class.ALCHEMIST, ch);
        // (appearance of Liscor Plaza and City Hall or Watch Barracks)
        // (appearance of Liscor playground)
        dbHelper.addLandmarkAppearance(Landmark.LISCORS_ADVENTURERS_GUILD, ch);
        dbHelper.addCharacterAppearance(Character.SELYS, ch);
        dbHelper.addSettlementMention(Settlement.ESTHELM, ch);
        // (appearance of unnamed Gnoll who bullies Erin)
        dbHelper.addSpeciesMention(Species.BEASTKIN, ch);
    }

    private static void ch12(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.12");
        // events
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.ERIN, Character.KRSHIA, ch));
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.ERIN, Character.LISM, ch));
        final Battle relc = new Battle("Relc chases away three Goblins ambushing Erin", null);
        dbHelper.addBattle(relc);
        dbHelper.addBattleCharacter(relc, Character.RELC);
        dbHelper.addBattleCharacter(relc, Character.ERIN);
        dbHelper.addBattleCharacter(relc, Character.RAGS);
        dbHelper.addBattleChapter(relc, ch);
        final Battle relc2 = new Battle("Relc kills two Goblins", null);
        dbHelper.addBattle(relc2);
        dbHelper.addBattleCharacter(relc2, Character.RELC);
        dbHelper.addBattleChapter(relc2, ch);
        dbHelper.addLevelUp(new LevelUp(Character.ERIN, ch, 6, Class.INNKEEPER, false, false));
        // misc
        dbHelper.addCharacterFirstName(Character.KRSHIA, ch, "Krshia");
        dbHelper.addCharacterFirstName(Character.LISM, ch, "Lism");
        dbHelper.addSkillUpgrade(Skill.THICK_SCALES, Skill.IRON_SCALES, ch);
        dbHelper.addClassSkill(Class.SERGEANT, Skill.IRON_SCALES, ch);
        dbHelper.addCharacterFirstName(Character.BELSC, ch, "Belsc");
        // appearances/mentions
        // (still day 8 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addNationAppearance(Nation.LISCOR, ch);
        dbHelper.addSettlementAppearance(Settlement.LISCOR, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addCharacterMention(Character.SELYS, ch);
        dbHelper.addCharacterMention(Character.DRASSI, ch);
        dbHelper.addWorldMention(World.EARTH, ch);
        dbHelper.addSpeciesAppearance(Species.DRAKE, ch);
        dbHelper.addSpeciesAppearance(Species.GNOLL, ch);
        dbHelper.addSpeciesAppearance(Species.ANTINIUM, ch);
        dbHelper.addLandmarkAppearance(Landmark.LISCORS_MARKET_STREET, ch);
        dbHelper.addCharacterAppearance(Character.KRSHIA, ch);
        dbHelper.addCharacterAppearance(Character.LISM, ch);
        dbHelper.addCharacterMention(Character.PISCES, ch);
        dbHelper.addClassMention(Class.NECROMANCER, ch);
        dbHelper.addCharacterMention(Character.KLBKCH, ch);
        dbHelper.addCharacterAppearance(Character.RELC, ch);
        // (appearance of blue guard drake)
        dbHelper.addSkillMention(Skill.THICK_SCALES, ch);
        // (Relc has [Iron Scales])
        dbHelper.addSkillMention(Skill.IRON_SCALES, ch);
        // (Relc is a [Sergeant])
        dbHelper.addClassMention(Class.SERGEANT, ch);
        dbHelper.addClassMention(Class.SPEARMASTER, ch);
        dbHelper.addCharacterMention(Character.TKRN, ch);
        // (appearance of green guard drake)
        dbHelper.addCharacterMention(Character.BELSC, ch);
        dbHelper.addClassMention(Class.GUARD, ch);
        dbHelper.addLandmarkMention(Landmark.BLOODFIELDS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        // (appearance of Rags' parents)
        dbHelper.addCharacterAppearance(Character.RAGS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODED_WATERS_CAVE, ch);
        dbHelper.addCharacterAppearance(Character.FLOODED_WATERS_CHIEFTAIN, ch);
        // (day 9 since Erin got to Innworld)
        dbHelper.addClassMention(Class.WARRIOR, ch);
    }

    private static void ch13(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.13");
        // events
        dbHelper.maybeAddFirstMeeting(new FirstMeeting(Character.ERIN, Character.FLOODED_WATERS_CHIEFTAIN, ch));
        dbHelper.addBattle(CHIEFTAIN_BATTLE);
        dbHelper.addBattleChapter(CHIEFTAIN_BATTLE, ch);
        dbHelper.addBattleCharacter(CHIEFTAIN_BATTLE, Character.ERIN);
        dbHelper.addBattleCharacter(CHIEFTAIN_BATTLE, Character.FLOODED_WATERS_CHIEFTAIN);
        dbHelper.addInnArrival(new InnArrival(Character.FLOODED_WATERS_CHIEFTAIN, ch));
        // misc
        // appearances/mentions
        // (day 9 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addCharacterMention(Character.PISCES, ch);
        dbHelper.addSpeciesAppearance(Species.DRAKE, ch);
        dbHelper.addCharacterAppearance(Character.RELC, ch);
        dbHelper.addClassMention(Class.GUARDSMAN, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        // (appearance of Rags' parents)
        dbHelper.addSpeciesAppearance(Species.ACID_FLY, ch);
        dbHelper.addCharacterAppearance(Character.RAGS, ch);
        dbHelper.addCharacterAppearance(Character.FLOODED_WATERS_CHIEFTAIN, ch);
        // chess is mentioned
    }

    private static void ch14(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.14");
        // events
        dbHelper.addBattleChapter(CHIEFTAIN_BATTLE, ch);
        dbHelper.addCharacterStatus(new CharacterStatus(new Status(Status.DECEASED), Character.FLOODED_WATERS_CHIEFTAIN, ch));
        final LevelUp lvErin = new LevelUp(Character.ERIN, ch, 9, Class.INNKEEPER, false, false);
        dbHelper.addLevelUp(lvErin);
        dbHelper.addLevelUpSkill(lvErin, Skill.BAR_FIGHTING);
        dbHelper.addLevelUpSkill(lvErin, Skill.UNERRING_THROW);
        // misc
        dbHelper.addClassSkill(Class.INNKEEPER, Skill.BAR_FIGHTING, ch);
        dbHelper.addClassSkill(Class.INNKEEPER, Skill.UNERRING_THROW, ch);
        // appearances/mentions
        // (still day 9 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        dbHelper.addCharacterAppearance(Character.FLOODED_WATERS_CHIEFTAIN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        dbHelper.addClassMention(Class.INNKEEPER, ch);
        dbHelper.addSkillMention(Skill.BAR_FIGHTING, ch);
        dbHelper.addSkillMention(Skill.UNERRING_THROW, ch);
    }

    private static void ch15(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.15");
        // events
        // misc
        // appearances/mentions
        // (still day 9 since Erin got to Innworld)
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addNationMention(Nation.LISCOR, ch);
        dbHelper.addSettlementMention(Settlement.LISCOR, ch);
        dbHelper.addSpeciesAppearance(Species.ANTINIUM, ch);
        dbHelper.addCharacterAppearance(Character.KLBKCH, ch);
        dbHelper.addSpeciesAppearance(Species.DRAKE, ch);
        dbHelper.addCharacterAppearance(Character.RELC, ch);
        dbHelper.addSpeciesAppearance(Species.GNOLL, ch);
        dbHelper.addCharacterAppearance(Character.BEILMARK, ch);
        dbHelper.addClassMention(Class.GUARDSMAN, ch);
        dbHelper.addClassMention(Class.GUARD, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);
        // mention of the Black Tide
        dbHelper.addSkillMention(Skill.ENHANCED_MOVEMENT, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addCharacterAppearance(Character.FLOODED_WATERS_CHIEFTAIN, ch);
        // Erin admits to Klbkch she's not from innworld
        dbHelper.addNationMention(Nation.MICHIGAN, ch);
        dbHelper.addSpeciesMention(Species.SELPHID, ch);
        dbHelper.addSpeciesMention(Species.DULLAHAN, ch);
        dbHelper.addSpeciesMention(Species.BEASTKIN, ch);
        // first time phrase "No Killing Goblins" is uttered
        dbHelper.addSkillMention(Skill.BAR_FIGHTING, ch);
        dbHelper.addSkillMention(Skill.UNERRING_THROW, ch);
        dbHelper.addClassMention(Class.INNKEEPER, ch);
        dbHelper.addClassMention(Class.BUTCHER, ch);
        dbHelper.addClassMention(Class.MUSICIAN, ch);
        dbHelper.addClassMention(Class.RULER, ch);
        dbHelper.addClassMention(Class.SOLDIER, ch);
        dbHelper.addSkillMention(Skill.DETECT_GUILT, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.RHIR, ch);
        dbHelper.addLandmarkMention(Landmark.LISCORS_ADVENTURERS_GUILD, ch);
        dbHelper.addCharacterMention(Character.QUEEN_OF_THE_FREE_ANTINIUM, ch);
    }

    private static void ch16(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.16");
        // events
        // misc
        // appearances/mentions
        // (day 10 since Erin got to Innworld)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.FIRST_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.ERIN, ch);

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
