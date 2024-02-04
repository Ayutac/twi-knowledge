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
import org.abos.twi.knowledge.core.location.Nation;
import org.abos.twi.knowledge.core.location.Settlement;
import org.abos.twi.knowledge.core.location.World;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.DbHelper;

import java.sql.SQLException;

public final class Volume1 {

    public static final Battle PISCES_FIGHT = new Battle("Erin vs. Pisces", null);

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
        dbHelper.addWorld(World.INNWORLD);
        dbHelper.addWorld(World.EARTH);
        dbHelper.addLandmassOcean(LandmassOcean.IZRIL);
        dbHelper.addLandmassOcean(LandmassOcean.NORTHERN_IZRIL);
        dbHelper.addLandmark(Landmark.FLOODPLAINS);
        dbHelper.addLandmark(Landmark.HIGH_PASSES);
        dbHelper.addLandmark(Landmark.FIRST_WANDERING_INN);
        dbHelper.addSpecies(Species.HUMAN);
        dbHelper.addSpecies(Species.GOBLIN);
        dbHelper.addSpecies(Species.DRAGON);
        dbHelper.addClass(Class.INNKEEPER);
        dbHelper.addSkill(Skill.BASIC_CLEANING);
        dbHelper.addClassSkill(Class.INNKEEPER, Skill.BASIC_CLEANING);
        dbHelper.addSkill(Skill.BASIC_COOKING);
        dbHelper.addClassSkill(Class.INNKEEPER, Skill.BASIC_COOKING);
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
        // first appearances/mentions
        dbHelper.addLandmassOcean(LandmassOcean.BLUE_MOON);
        dbHelper.addLandmassOcean(LandmassOcean.GREEN_MOON);
        dbHelper.addLandmassOcean(LandmassOcean.NORTH_AMERICA);
        dbHelper.addNation(Nation.USA);
        dbHelper.addSettlement(Settlement.CHICAGO);
        dbHelper.addNation(Nation.LISCOR);
        dbHelper.addSettlement(Settlement.LISCOR);
        dbHelper.addLandmark(Landmark.AMENTUS_GROVE);
        dbHelper.addSpecies(Species.HOLLOWSTONE_DECEIVER);
        dbHelper.addLandmark(Landmark.TERIARCHS_OLD_HIDEOUT);
        dbHelper.addLandmark(Landmark.TERIARCHS_NEW_HIDEOUT);
        dbHelper.addSpecies(Species.DEMON);
        dbHelper.addSpecies(Species.DJINNI);
        dbHelper.addClass(Class.KNIGHT);
        dbHelper.addClass(Class.HERO);
        dbHelper.addNation(Nation.FIVE_FAMILIES_TERRITORY);
        dbHelper.addSettlement(Settlement.CELUM);
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
        dbHelper.addClassMention(Class.KNIGHT, ch);
        dbHelper.addClassMention(Class.HERO, ch);
        // (Teriarch checks cave from day 1 to day 3 since erin appeared + 2 weeks checking for pursuit)
        dbHelper.addCharacterMention(Character.MAGNOLIA, ch);
        dbHelper.addSpeciesMention(Species.DEMON, ch);
        dbHelper.addSpeciesMention(Species.DJINNI, ch);
        dbHelper.addCharacterMention(Character.QUARASS, ch);
        dbHelper.addNationMention(Nation.FIVE_FAMILIES_TERRITORY, ch);
        dbHelper.addSettlementMention(Settlement.CELUM, ch);
    }

    private static void ch02(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.02");
        // first appearances/mentions
        dbHelper.addNation(Nation.MICHIGAN);
        dbHelper.addSettlement(Settlement.GRAND_RAPIDS);
        dbHelper.addLandmark(Landmark.FLOODPLAINS_STREAM);
        dbHelper.addLandmark(Landmark.FLOODED_WATERS_CAVE);
        dbHelper.addClass(Class.CHIEFTAIN);
        dbHelper.addClass(Class.SHAMAN);
        dbHelper.addCharacterSpecies(Character.RAGS, Species.GOBLIN, ch);
        dbHelper.addCharacterSpecies(Character.FLOODED_WATERS_CHIEFTAIN, Species.GOBLIN, ch);
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
        // first appearances/mentions
        dbHelper.addSpecies(Species.FLATFISH);
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
        // first appearances/mentions
        dbHelper.addLandmark(Landmark.PISCES_HIDEOUT);
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
        // first appearances/mentions
        dbHelper.addSpecies(Species.ACID_FLY);
        dbHelper.addClass(Class.GUARDSWOMAN);
        dbHelper.addSpecies(Species.RAZORBEAK);
        dbHelper.addSpecies(Species.ANTINIUM);
        // events
        // (Erin makes Pasta)
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
        // first appearances/mentions
        dbHelper.addSpecies(Species.DRAKE);
        dbHelper.addSkill(Skill.TELEPORTATION);
        dbHelper.addClass(Class.CHEF);
        dbHelper.addClass(Class.GUARDSMAN);
        dbHelper.addClass(Class.SPEARMASTER);
        dbHelper.addClass(Class.SWORDSLAYER);
        dbHelper.addClass(Class.WARRIOR);
        dbHelper.addClass(Class.SURVIVOR);
        dbHelper.addClass(Class.LORD);
        dbHelper.addClass(Class.LADY);
        dbHelper.addClass(Class.GOOD_PERSON);
        dbHelper.addLandmassOcean(LandmassOcean.SOUTHERN_IZRIL);
        dbHelper.addNation(Nation.PALLASS);
        dbHelper.addSettlement(Settlement.PALLASS);
        dbHelper.addLandmark(Landmark.BLOODFIELDS);
        dbHelper.addSkill(Skill.BASIC_CRAFTING);
        dbHelper.addClassSkill(Class.INNKEEPER, Skill.BASIC_CRAFTING);
        // events
        final LevelUp lvErin = new LevelUp(Character.ERIN, ch, 5, Class.INNKEEPER, false, false);
        dbHelper.addLevelUp(lvErin);
        dbHelper.addLevelUpSkill(lvErin, Skill.BASIC_CRAFTING);
        // (healing potion is used)
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
        // Klbkch's names Klbkch and Klb are revealed
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
        // first appearances/mentions
        dbHelper.addClass(Class.GUARD);
        dbHelper.addClass(Class.GATHERER);
        dbHelper.addSkill(Skill.DETECT_POISON);
        dbHelper.addClassSkill(Class.GATHERER, Skill.DETECT_POISON);
        dbHelper.addSkill(Skill.BAD_FRUIT_DETECTOR);
        // events
        // (Erin crafts a basket out of grass)
        final Battle razorbeak = new Battle("Erin fights a Razorbeak for its eggs", null);
        dbHelper.addBattle(razorbeak);
        dbHelper.addBattleChapter(razorbeak, ch);
        dbHelper.addBattleCharacter(razorbeak, Character.ERIN);
        dbHelper.addBattle(PISCES_FIGHT);
        dbHelper.addBattleChapter(PISCES_FIGHT, ch);
        dbHelper.addBattleCharacter(PISCES_FIGHT, Character.ERIN);
        dbHelper.addBattleCharacter(PISCES_FIGHT, Character.PISCES);
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
        // (Caption Z as nickname for Zevara)
        dbHelper.addCharacterAppearance(Character.ZEVARA, ch);
        dbHelper.addCharacterAppearance(Character.PISCES, ch);
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
