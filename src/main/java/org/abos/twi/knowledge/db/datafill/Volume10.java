package org.abos.twi.knowledge.db.datafill;

import org.abos.common.LogUtil;
import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.core.Skill;
import org.abos.twi.knowledge.core.Species;
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

public final class Volume10 {

    private static final Logger LOGGER = LogManager.getLogger(Volume10.class);

    private Volume10() {
        /* No instantiation. */
    }

    public static void fillDb(final DbHelper dbHelper) throws SQLException {
        LOGGER.info("Volume 10 data filling...");
        final Instant start = Instant.now();
        ch00l(dbHelper);
        ch01l(dbHelper);
        ch02y(dbHelper);
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Volume 10 data filling", time.toMinutes(), time.toSecondsPart());
    }

    private static void ch00l(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 10.00 L");
        // events
        final LevelUp lvlUp = new LevelUp(Character.LYONETTE, ch, 30, Class.PRINCESS_OF_THE_INN, true, false);
        dbHelper.addLevelUp(lvlUp);
        dbHelper.addLevelUpSkill(lvlUp, Skill.REMEMBER_MY_DEFINING_MOMENT);
        dbHelper.addLevelUpSkill(lvlUp, Skill.STAFF_FLURRY_OF_EFFICIENCY);
        dbHelper.addLevelUpSkill(lvlUp, Skill.CONJURE_PREPARED_DISH);
        dbHelper.addLevelUpSkill(lvlUp, Skill.CONCEAL_REPUTATION);
        dbHelper.addLevelUpSkill(lvlUp, Skill.SHARED_AUTHORITY_THE_WANDERING_INN);
        // misc
        dbHelper.addClassSkill(Class.SCRIBBLER, Skill.FAST_SCRAWL, ch);
        dbHelper.addCharacterAge(Character.NANETTE, 13, ch);
        dbHelper.addClassSkill(Class.QUEEN, Skill.PERFECTION_IS_OVERRATED, ch);
        dbHelper.addClassSkill(Class.PRINCESS_OF_THE_INN, Skill.REMEMBER_MY_DEFINING_MOMENT, ch);
        dbHelper.addClassSkill(Class.PRINCESS_OF_THE_INN, Skill.STAFF_FLURRY_OF_EFFICIENCY, ch);
        dbHelper.addClassSkill(Class.PRINCESS_OF_THE_INN, Skill.CONJURE_PREPARED_DISH, ch);
        dbHelper.addClassSkill(Class.PRINCESS_OF_THE_INN, Skill.CONCEAL_REPUTATION, ch);
        dbHelper.addClassSkill(Class.PRINCESS_OF_THE_INN, Skill.SHARED_AUTHORITY_THE_WANDERING_INN, ch);
        // (Hethon is mentioned to be 14)
        // (Sammiel is mentioned to be 10)
        dbHelper.addCharacterLastName(Character.ROSE, ch, "Cinevoy");
        dbHelper.addClassUpgrade(Class.LORD, Class.SUAVE_LORD, ch);
        // appearances/mentions
        // (Lyonette is Lv 30 in [Princess of the Inn])
        // (been a month or so since the end of vol 8, final month of winter)
        // (1 day after end of Volume 9)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.THIRD_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterAppearance(Character.NANETTE, ch);
        dbHelper.addClassMention(Class.SOLDIER, ch);
        dbHelper.addClassMention(Class.WITCH, ch);
        dbHelper.addSpeciesAppearance(Species.GNOLL, ch);
        dbHelper.addCharacterAppearance(Character.MRSHA, ch);
        // (appearance of Thronebearer)
        dbHelper.addClassMention(Class.KNIGHT, ch);
        dbHelper.addCharacterAppearance(Character.USHAR, ch);
        dbHelper.addCharacterMention(Character.SEST, ch);
        dbHelper.addCharacterMention(Character.LORMEL, ch);
        dbHelper.addCharacterMention(Character.CALIFOR, ch);
        dbHelper.addClassMention(Class.LADY, ch);
        dbHelper.addClassMention(Class.WARRIOR, ch);
        dbHelper.addNationMention(Nation.LISCOR, ch);
        dbHelper.addSettlementMention(Settlement.LISCOR, ch);
        dbHelper.addSpeciesMention(Species.HOLLOWSTONE_DECEIVER, ch);
        dbHelper.addSpeciesMention(Species.SHIELD_SPIDER, ch);
        dbHelper.addClassMention(Class.SCRIBBLER, ch);
        dbHelper.addSkillMention(Skill.FAST_SCRAWL, ch);
        dbHelper.addClassMention(Class.PRINCESS, ch);
        dbHelper.addClassMention(Class.TUTOR, ch);
        dbHelper.addCharacterMention(Character.SERAPHEL, ch);
        dbHelper.addClassMention(Class.BLACKMAILER, ch);
        dbHelper.addClassMention(Class.CHEF, ch);
        dbHelper.addClassMention(Class.BARD, ch);
        dbHelper.addSkillMention(Skill.WORLDS_EYE_THEATRE, ch);
        dbHelper.addSkillMention(Skill.HEATED_AIR, ch);
        // (appearance of Stone of Elements)
        dbHelper.addClassMention(Class.MINER, ch);
        dbHelper.addClassMention(Class.VILLAGER, ch);
        dbHelper.addSpeciesMention(Species.HALF_GIANT, ch);
        dbHelper.addCharacterMention(Character.MOORE, ch);
        dbHelper.addClassMention(Class.BLOODEARTH_MAGE, ch);
        dbHelper.addCharacterMention(Character.HALRAC, ch);
        dbHelper.addCharacterMention(Character.KEVIN, ch);
        dbHelper.addCharacterMention(Character.SILVERMOP, ch);
        dbHelper.addCharacterMention(Character.ALCAZ, ch);
        dbHelper.addCharacterMention(Character.SEVE, ch);
        dbHelper.addCharacterMention(Character.HEROVE, ch);
        dbHelper.addSpeciesMention(Species.CARN_WOLF, ch);
//        dbHelper.addCharacterMention(Character.THUNDERFUR, ch);
        dbHelper.addCharacterMention(Character.GERSHAL, ch);
        dbHelper.addCharacterMention(Character.OLIYAYA, ch);
        dbHelper.addSpeciesMention(Species.DRAKE, ch);
        dbHelper.addCharacterMention(Character.TEKSHIA, ch);
        dbHelper.addCharacterMention(Character.NORMEN, ch);
        dbHelper.addCharacterMention(Character.BAMER, ch);
        dbHelper.addCharacterMention(Character.KHORPE, ch);
        dbHelper.addSettlementMention(Settlement.RIVERFARM, ch);
        dbHelper.addSettlementMention(Settlement.WINDREST, ch);
        dbHelper.addCharacterMention(Character.HELM, ch);
        dbHelper.addCharacterMention(Character.TYPHENOUS, ch);
        dbHelper.addCharacterMention(Character.BRIGANDA, ch);
        // (mention of the Brothers)
        dbHelper.addNationMention(Nation.ACTELIOS_SALASH, ch);
        dbHelper.addSettlementMention(Settlement.CARVEN_CITY, ch);
        dbHelper.addCharacterMention(Character.PELT, ch);
        dbHelper.addCharacterMention(Character.KASIGNA, ch);
        dbHelper.addClassMention(Class.ROGUE, ch);
        dbHelper.addClassMention(Class.SPY, ch);
        dbHelper.addCharacterMention(Character.URKSH, ch);
        dbHelper.addCharacterMention(Character.PRHA, ch);
        dbHelper.addCharacterMention(Character.EKIRRA, ch);
        dbHelper.addCharacterMention(Character.LACRESE, ch);
        dbHelper.addCharacterMention(Character.ERIN, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.BALEROS, ch);
        dbHelper.addLandmarkMention(Landmark.LISCORS_MAGES_GUILD, ch);
        dbHelper.addSpeciesMention(Species.MINOTAUR, ch);
        dbHelper.addCharacterMention(Character.BEZALE, ch);
        dbHelper.addClassMention(Class.MAGE, ch);
        dbHelper.addSkillMention(Skill.MESSAGE, ch);
        dbHelper.addSpeciesMention(Species.SELPHID, ch);
        dbHelper.addCharacterMention(Character.JELAQUA, ch);
        dbHelper.addNationMention(Nation.NOELICTUS, ch);
        // (appearance of the Bone Giant)
        dbHelper.addSettlementMention(Settlement.RHEIRGEST, ch);
        dbHelper.addCharacterMention(Character.RELC, ch);
        dbHelper.addCharacterMention(Character.MENOLIT, ch);
        // (mention of Liscor's Council)
        dbHelper.addSkillMention(Skill.DOOR_OF_PORTALS, ch);
        dbHelper.addCharacterAppearance(Character.LISKA, ch);
        dbHelper.addSkillMention(Skill.PARTIAL_RECONSTRUCTION, ch);
        // (appearance of Shivertail Plaza)
        dbHelper.addClassMention(Class.GUARD, ch);
        dbHelper.addClassMention(Class.TRADER, ch);
        // (mention of Driver's Guild of Liscor)
        // (mention of Watch Barracks/House of Liscor)
        dbHelper.addLandmarkMention(Landmark.BLOODFIELDS, ch);
        // (mention of Silverfang tribe)
        dbHelper.addLandmarkMention(Landmark.LISCORS_MARKET_STREET, ch);
        dbHelper.addCharacterMention(Character.VISMA, ch);
        dbHelper.addClassMention(Class.PAINTER, ch);
        dbHelper.addClassMention(Class.HANDY_WORKER, ch);
        dbHelper.addClassMention(Class.GUILDMASTER, ch);
        dbHelper.addCharacterMention(Character.YESNE, ch);
        dbHelper.addCharacterMention(Character.HEXEL, ch);
        dbHelper.addClassMention(Class.JEWELER, ch);
        dbHelper.addCharacterMention(Character.KRSHIA, ch);
        dbHelper.addSettlementMention(Settlement.ESTHELM, ch);
        dbHelper.addCharacterMention(Character.LISM, ch);
        // (mention of House Ulta and House Walchaís)
        dbHelper.addClassMention(Class.ALCHEMIST, ch);
        dbHelper.addCharacterAppearance(Character.LYONETTE, ch);
        dbHelper.addCharacterAppearance(Character.DALIMONT, ch);
        dbHelper.addClassMention(Class.POET, ch);
        dbHelper.addClassMention(Class.WOODCUTTER, ch);
        dbHelper.addCharacterMention(Character.RECLIS, ch);
        dbHelper.addCharacterAppearance(Character.ISHKR, ch);
        dbHelper.addCharacterAppearance(Character.YELROAN, ch);
        dbHelper.addClassMention(Class.BARMAID, ch);
        dbHelper.addClassMention(Class.WORLDLY_PRINCESS, ch);
        dbHelper.addClassMention(Class.PRINCESS_OF_THE_INN, ch);
        dbHelper.addClassMention(Class.APPRENTICE, ch);
        dbHelper.addClassMention(Class.HEAD_SERVER_OF_TALES_AND_FABLES, ch);
        dbHelper.addClassMention(Class.GEMSTONE_SMELTER, ch);
        dbHelper.addClassMention(Class.ENCHANTER, ch);
        dbHelper.addClassMention(Class.MATHEMATICIAN, ch);
        dbHelper.addClassMention(Class.BODYGUARD, ch);
        dbHelper.addClassMention(Class.HEALER, ch);
        dbHelper.addSkillMention(Skill.GARDEN_OF_SANCTUARY, ch);
        dbHelper.addSkillMention(Skill.STAFF_FLURRY_OF_EFFICIENCY, ch);
        dbHelper.addSkillMention(Skill.FLURRY_OF_MOTION, ch);
        dbHelper.addCharacterAppearance(Character.VALETERISA, ch);
        dbHelper.addCharacterMention(Character.BELAVIERR, ch);
        dbHelper.addCharacterMention(Character.SNATCHER, ch);
        dbHelper.addCharacterMention(Character.MONTRESSA, ch);
        dbHelper.addCharacterMention(Character.HEDAULT, ch);
        dbHelper.addCharacterAppearance(Character.HETHON, ch);
        dbHelper.addCharacterAppearance(Character.SAMMIAL, ch);
        dbHelper.addCharacterAppearance(Character.JERICHA, ch);
        dbHelper.addCharacterAppearance(Character.ULLIM, ch);
        dbHelper.addCharacterMention(Character.JOSEPH, ch);
        dbHelper.addNationMention(Nation.PALLASS, ch);
        dbHelper.addSettlementMention(Settlement.PALLASS, ch);
        dbHelper.addCharacterMention(Character.IMANI, ch);
        dbHelper.addSpeciesMention(Species.CENTAUR, ch);
        dbHelper.addCharacterMention(Character.PALT, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        dbHelper.addCharacterAppearance(Character.STICK, ch);
        dbHelper.addCharacterAppearance(Character.IELANE, ch);
        dbHelper.addSpeciesAppearance(Species.ANTINIUM, ch);
//        dbHelper.addCharacterAppearance(Character.TIGORE, ch);
        dbHelper.addCharacterAppearance(Character.ROSENCRANTZ, ch);
        dbHelper.addCharacterAppearance(Character.CHALDION, ch);
        dbHelper.addCharacterMention(Character.DULN, ch);
        dbHelper.addSpeciesMention(Species.DRAGON, ch);
        dbHelper.addCharacterMention(Character.TERIARCH, ch);
        dbHelper.addSpeciesMention(Species.UNICORN, ch);
        dbHelper.addCharacterMention(Character.TALETEVIRION, ch);
        dbHelper.addCharacterMention(Character.ILVRISS, ch);
        dbHelper.addCharacterMention(Character.ALDONSS, ch);
        dbHelper.addCharacterMention(Character.RAFAEMA, ch);
        dbHelper.addSpeciesMention(Species.UNDEAD, ch);
        dbHelper.addCharacterMention(Character.FETOHEP, ch);
        dbHelper.addCharacterMention(Character.ALTESTIEL, ch);
        dbHelper.addSettlementMention(Settlement.INVRISIL, ch);
        // (appearance of Forgotten Wing Company)
        dbHelper.addSpeciesAppearance(Species.FRAERLING, ch);
        dbHelper.addCharacterAppearance(Character.NIERS, ch);
        // (Niers parents are dead)
        // (appearance of the Ironwood Wand)
        // (mention of Players of Celum)
        dbHelper.addClassMention(Class.MAID, ch);
        dbHelper.addClassMention(Class.SERVANT, ch);
        dbHelper.addClassMention(Class.MOTHER, ch);
        dbHelper.addClassMention(Class.DUELIST, ch);
        dbHelper.addClassMention(Class.PIRATE, ch);
        dbHelper.addClassMention(Class.ADMIRAL, ch);
        dbHelper.addClassMention(Class.CAPTAIN, ch);
        dbHelper.addClassMention(Class.DIPLOMAT, ch);
        dbHelper.addClassMention(Class.EMISSARY, ch);
        dbHelper.addClassMention(Class.ARCHITECT, ch);
        dbHelper.addClassMention(Class.FINANCIER, ch);
        dbHelper.addClassMention(Class.PRINCE, ch);
        dbHelper.addClassMention(Class.WARLORD, ch);
        dbHelper.addClassMention(Class.MAJORDOMO, ch);
        dbHelper.addClassMention(Class.SUAVE_LORD, ch);
        dbHelper.addClassMention(Class.DRUID, ch);
        dbHelper.addClassMention(Class.AMBASSADOR, ch);
        dbHelper.addClassMention(Class.COURIER, ch);
        dbHelper.addClassMention(Class.GRANDMASTER, ch);
        dbHelper.addClassMention(Class.CRIMINAL_MASTERMIND, ch);
        dbHelper.addClassMention(Class.SQUIRE, ch);
        dbHelper.addSkillMention(Skill.SCRY, ch);
        dbHelper.addSkillMention(Skill.SILVERGLOW_ENCHANTMENT, ch);
        dbHelper.addSkillMention(Skill.FLAWLESS_ATTEMPT, ch);
        dbHelper.addSkillMention(Skill.REMEMBER_MY_DEFINING_MOMENT, ch);
        dbHelper.addSkillMention(Skill.PERFECTION_IS_OVERRATED, ch);
        dbHelper.addSkillMention(Skill.PERFECT_STRIKE, ch);
        dbHelper.addSkillMention(Skill.IMMORTAL_MOMENT, ch);
        dbHelper.addSkillMention(Skill.LIKE_FIRE_MEMORY, ch);
        dbHelper.addSkillMention(Skill.DISABLE_FRIENDLY_FIRE, ch);
        dbHelper.addSkillMention(Skill.COMPARTMENTS_OF_HOLDING, ch);
        dbHelper.addSkillMention(Skill.THE_TRANSIENT_ETC, ch);
        dbHelper.addSkillMention(Skill.CONJURE_PREPARED_DISH, ch);
        dbHelper.addSkillMention(Skill.CONCEAL_REPUTATION, ch);
        dbHelper.addSkillMention(Skill.SHARED_AUTHORITY_THE_WANDERING_INN, ch);
        dbHelper.addNationAppearance(Nation.PAETH, ch);
        dbHelper.addSettlementAppearance(Settlement.PAETH, ch);
        dbHelper.addNationMention(Nation.BALEROS_UNCLAIMED, ch);
        dbHelper.addSettlementMention(Settlement.TALENQUAL, ch);
        dbHelper.addLandmarkMention(Landmark.INVRISILS_ADVENTURE_ROOM, ch);
        dbHelper.addNationMention(Nation.KAAZ, ch);
        dbHelper.addNationMention(Nation.ERRIBATHE, ch);
        dbHelper.addNationMention(Nation.GOLAEN, ch);
        dbHelper.addNationMention(Nation.PHEISLANT, ch);
        dbHelper.addNationMention(Nation.TAIMAGUROS, ch);
        dbHelper.addNationMention(Nation.KALIV, ch);
        dbHelper.addSpeciesMention(Species.FLATFISH, ch);
        dbHelper.addSpeciesMention(Species.ACID_FLY, ch);
        dbHelper.addSpeciesMention(Species.MOTHBEAR, ch);
        dbHelper.addCharacterMention(Character.MENISI, ch);
        dbHelper.addCharacterMention(Character.SHARDELE, ch);
        dbHelper.addCharacterMention(Character.AIELEF, ch);
        dbHelper.addCharacterAppearance(Character.VERNOUE, ch);
        dbHelper.addCharacterMention(Character.IRADOREN, ch);
        dbHelper.addCharacterMention(Character.RYOKA, ch);
        dbHelper.addSpeciesMention(Species.ORANGUTAN, ch);
        dbHelper.addCharacterMention(Character.EREK, ch);
        dbHelper.addSpeciesMention(Species.STITCH_FOLK, ch);
        dbHelper.addCharacterMention(Character.REVI, ch);
        dbHelper.addCharacterAppearance(Character.RESK, ch);
        dbHelper.addCharacterMention(Character.ELOISE, ch);
        dbHelper.addCharacterMention(Character.ATLANNA, ch);
        dbHelper.addCharacterAppearance(Character.VENSHA, ch);
        dbHelper.addSpeciesAppearance(Species.VAMPIRE, ch);
        dbHelper.addCharacterAppearance(Character.FIERRE, ch);
        dbHelper.addCharacterAppearance(Character.CALESCENT, ch);
        dbHelper.addCharacterMention(Character.NUMBTONGUE, ch);
        dbHelper.addCharacterMention(Character.OCTAVIA, ch);
        dbHelper.addCharacterMention(Character.SALKIS, ch);
        dbHelper.addCharacterMention(Character.GARIA, ch);
        dbHelper.addCharacterMention(Character.BIRD, ch);
        dbHelper.addCharacterMention(Character.BADARROW, ch);
        dbHelper.addCharacterMention(Character.RESSGA, ch);
        dbHelper.addCharacterMention(Character.NOKHA, ch);
        dbHelper.addCharacterMention(Character.TESSA, ch);
        dbHelper.addCharacterMention(Character.MARAN, ch);
        dbHelper.addCharacterMention(Character.SAFRY, ch);
        dbHelper.addCharacterMention(Character.PRYDE, ch);
        dbHelper.addCharacterAppearance(Character.PEGGY, ch);
        dbHelper.addCharacterAppearance(Character.INKPAPER, ch);
        dbHelper.addCharacterMention(Character.XARKOUTH, ch);
        dbHelper.addCharacterAppearance(Character.ASGRA, ch);
        dbHelper.addCharacterMention(Character.SALISS, ch);
        // (mention of Kyetne (f), Loxlet (m), Isni (f))
    }

    private static void ch01l(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 10.01 L");
        // events
        // misc
        // appearances/mentions
        // (2 day after end of Volume 9)
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmarkAppearance(Landmark.FLOODPLAINS, ch);
        dbHelper.addLandmarkAppearance(Landmark.THIRD_WANDERING_INN, ch);
        dbHelper.addSpeciesAppearance(Species.GOBLIN, ch);
        dbHelper.addCharacterAppearance(Character.CALESCENT, ch);
        dbHelper.addSpeciesMention(Species.VAMPIRE, ch);
        dbHelper.addCharacterMention(Character.FIERRE, ch);
        dbHelper.addSpeciesMention(Species.UNDEAD, ch);
        // (mention of Plain's Eye, Redfang Tribe, Free Antinium, House Veltras, Ullsinoi, Liscor’s Council, Watch, Earthers of the Wandering Inn)
        // (mention of the Ironwood wand)
        dbHelper.addClassMention(Class.SPICE_CHEF, ch);
        dbHelper.addClassMention(Class.MATHEMATICIAN, ch);
        dbHelper.addClassMention(Class.KNIGHT, ch);
        dbHelper.addClassMention(Class.ALCHEMIST, ch);
        dbHelper.addClassMention(Class.FLOOR_BOSS, ch);
        dbHelper.addClassMention(Class.BARMAID, ch);
        dbHelper.addClassMention(Class.SWORDMASTER, ch);
        dbHelper.addClassMention(Class.PRINCESS, ch);
        dbHelper.addClassMention(Class.SHAMAN, ch);
        dbHelper.addClassMention(Class.GRANDMASTER, ch);
        dbHelper.addClassMention(Class.THUG, ch);
        dbHelper.addClassMention(Class.LADY, ch);
        dbHelper.addClassMention(Class.NECROMANCER, ch);
        dbHelper.addClassMention(Class.SHEPHERD, ch);
        dbHelper.addClassMention(Class.FARMHAND, ch);
        dbHelper.addClassMention(Class.INNKEEPER, ch);
        dbHelper.addClassMention(Class.FARMER, ch);
        dbHelper.addClassMention(Class.AEGISCASTER, ch);
        dbHelper.addClassMention(Class.FISHER, ch);
        dbHelper.addClassMention(Class.DRIVER, ch);
        dbHelper.addClassMention(Class.GRAVEKEEPER, ch);
        dbHelper.addClassMention(Class.WITCH, ch);
        dbHelper.addClassMention(Class.RECEPTIONIST, ch);
        dbHelper.addClassMention(Class.LANDLORD, ch);
        dbHelper.addClassMention(Class.FINANCIER, ch);
        dbHelper.addClassMention(Class.COUNCILWOMAN, ch);
        dbHelper.addClassMention(Class.LORD, ch);
        dbHelper.addClassMention(Class.ARCHMAGE, ch);
        dbHelper.addClassMention(Class.HEAD_SERVER, ch);
        dbHelper.addClassMention(Class.MAGE, ch);
        dbHelper.addClassMention(Class.PRINCE_OF_MEN, ch);
        dbHelper.addClassMention(Class.MERCHANT, ch);
        dbHelper.addClassMention(Class.DIPLOMAT, ch);
        dbHelper.addClassMention(Class.TRADER, ch);
        dbHelper.addClassMention(Class.FORGER, ch);
        dbHelper.addClassMention(Class.COLLECTOR, ch);
        dbHelper.addClassMention(Class.NEGOTIATOR, ch);
        dbHelper.addClassMention(Class.HEIRESS, ch);
        dbHelper.addSpeciesAppearance(Species.GOAT, ch);
//        dbHelper.addCharacterAppearance(Character.FLUFFLES_IV, ch);
        dbHelper.addSkillMention(Skill.QUICK_BOIL, ch);
        dbHelper.addSkillMention(Skill.MESSAGE, ch);
        dbHelper.addSkillMention(Skill.WORLD_OF_YOU_AND_ME, ch);
        dbHelper.addSkillMention(Skill.CREATE_FOOD, ch);
        dbHelper.addSkillMention(Skill.WORLDS_EYE_THEATRE, ch);
        dbHelper.addSkillMention(Skill.REPAIR, ch);
        dbHelper.addSkillMention(Skill.THE_TRANSIENT_ETC, ch);
        dbHelper.addSkillMention(Skill.AN_APPLE_A_DAY, ch);
        dbHelper.addSkillMention(Skill.CLEANSE, ch);
        dbHelper.addSkillMention(Skill.BENEDICTION_OF_HOPE, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.BALEROS, ch);
        dbHelper.addNationMention(Nation.ITALY, ch);
        dbHelper.addNationAppearance(Nation.LISCOR, ch);
        dbHelper.addSettlementAppearance(Settlement.LISCOR, ch);
        dbHelper.addSpeciesAppearance(Species.HUMAN, ch);
        dbHelper.addCharacterMention(Character.JOSEPH, ch);
        dbHelper.addCharacterAppearance(Character.LYONETTE, ch);
        dbHelper.addCharacterMention(Character.IEKA, ch);
        dbHelper.addSpeciesAppearance(Species.GNOLL, ch);
        dbHelper.addCharacterAppearance(Character.YELROAN, ch);
        dbHelper.addCharacterMention(Character.MAGNOLIA, ch);
        dbHelper.addCharacterAppearance(Character.VALETERISA, ch);
        dbHelper.addCharacterAppearance(Character.MRSHA, ch);
        dbHelper.addCharacterAppearance(Character.NANETTE, ch);
        dbHelper.addCharacterMention(Character.ERIN, ch);
        dbHelper.addCharacterAppearance(Character.NORMEN, ch);
        dbHelper.addCharacterAppearance(Character.PEGGY, ch);
        dbHelper.addCharacterMention(Character.NUMBTONGUE, ch);
        dbHelper.addSpeciesAppearance(Species.ANTINIUM, ch);
        dbHelper.addCharacterMention(Character.BIRD, ch);
        dbHelper.addCharacterMention(Character.MENOLIT, ch);
        dbHelper.addSpeciesAppearance(Species.DRAKE, ch);
        dbHelper.addCharacterAppearance(Character.RELC, ch);
        dbHelper.addCharacterAppearance(Character.SELYS, ch);
        dbHelper.addCharacterAppearance(Character.KRSHIA, ch);
        dbHelper.addCharacterMention(Character.GRIMALKIN, ch);
        dbHelper.addCharacterMention(Character.ILVRISS, ch);
        dbHelper.addCharacterAppearance(Character.ROSENCRANTZ, ch);
        dbHelper.addCharacterAppearance(Character.ISHKR, ch);
        dbHelper.addCharacterMention(Character.RAGS, ch);
        dbHelper.addCharacterAppearance(Character.TESSA, ch);
        dbHelper.addCharacterAppearance(Character.ROSE, ch);
        dbHelper.addCharacterAppearance(Character.USHAR, ch);
//        dbHelper.addCharacterAppearance(Character.PICKER, ch); // Goblin
        dbHelper.addCharacterMention(Character.SILVERMOP, ch);
//        dbHelper.addCharacterAppearance(Character.SILVERBOOTS, ch); // Antinium
//        dbHelper.addCharacterAppearance(Character.DOTS, ch); // Antinium
//        dbHelper.addCharacterAppearance(Character.HANDYFELLOW, ch); // Antinium
        dbHelper.addCharacterAppearance(Character.MONTRESSA, ch);
        dbHelper.addCharacterMention(Character.ULVAMA, ch);
        dbHelper.addCharacterMention(Character.ELDAVIN, ch);
        dbHelper.addCharacterMention(Character.MERISH, ch);
        dbHelper.addCharacterAppearance(Character.APISTA, ch);
        dbHelper.addCharacterMention(Character.OCTAVIA, ch);
        dbHelper.addCharacterMention(Character.SALISS, ch);
        dbHelper.addCharacterAppearance(Character.PALT, ch);
        dbHelper.addCharacterMention(Character.KASIGNA, ch);
        dbHelper.addCharacterMention(Character.AZKERASH, ch);
        dbHelper.addCharacterMention(Character.IMOR, ch);
        dbHelper.addCharacterMention(Character.SOLTON, ch);
        dbHelper.addSpeciesAppearance(Species.DRAGON, ch);
        dbHelper.addCharacterAppearance(Character.TERIARCH, ch);
        dbHelper.addCharacterAppearance(Character.SAMMIAL, ch);
        dbHelper.addCharacterAppearance(Character.HETHON, ch);
        dbHelper.addCharacterMention(Character.BETHAL, ch);
        dbHelper.addCharacterMention(Character.PRYDE, ch);
        dbHelper.addCharacterAppearance(Character.COLFA, ch);
        dbHelper.addCharacterAppearance(Character.HIMILT, ch);
        dbHelper.addCharacterMention(Character.ZEVARA, ch);
        dbHelper.addCharacterMention(Character.BAMER, ch);
        dbHelper.addCharacterAppearance(Character.DALIMONT, ch);
        dbHelper.addCharacterMention(Character.WAILANT, ch);
        dbHelper.addCharacterAppearance(Character.CHALDION, ch);
        dbHelper.addSpeciesAppearance(Species.MINOTAUR, ch);
        dbHelper.addCharacterAppearance(Character.BEZALE, ch);
        dbHelper.addCharacterAppearance(Character.JEWEL, ch);
        dbHelper.addCharacterAppearance(Character.DURENE, ch);
        dbHelper.addCharacterAppearance(Character.VESS, ch);
        dbHelper.addCharacterAppearance(Character.ANTHERR, ch);
        dbHelper.addCharacterAppearance(Character.TEMILE, ch);
        dbHelper.addCharacterAppearance(Character.TIMBOR, ch);
        dbHelper.addCharacterAppearance(Character.RAEKEA, ch);
        dbHelper.addCharacterAppearance(Character.ELIRR, ch);
        dbHelper.addCharacterAppearance(Character.LISM, ch);
        dbHelper.addCharacterAppearance(Character.ALONNA, ch);
        dbHelper.addCharacterMention(Character.JEISS, ch);
        dbHelper.addCharacterAppearance(Character.TISMEL, ch);
        dbHelper.addCharacterAppearance(Character.ZALAISS, ch);
        dbHelper.addCharacterMention(Character.TEKSHIA, ch);
        dbHelper.addCharacterMention(Character.TALETEVIRION, ch);
        dbHelper.addCharacterMention(Character.ALBER, ch);
        dbHelper.addCharacterMention(Character.MILAW, ch);
        dbHelper.addCharacterMention(Character.XHERW, ch);
        dbHelper.addCharacterAppearance(Character.NERUL, ch);
        dbHelper.addCharacterMention(Character.GARIA, ch);
        dbHelper.addCharacterMention(Character.DESKIE, ch);
        dbHelper.addCharacterMention(Character.ADETR, ch);
        // Itelvaunhz (Name revealed?), Xalls (Name revealed?),
        // Conec the [Fence], Rittane, her parents: Dorkel and Leiithe
        // Rheirgest, Oswen, Reizmelt, Riverfarm, Yolta, Barehoof Kitchens, Drath, Invrisil, Ottopren (city state), Samal (Kingdom of keys)
    }

    private static void ch02y(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 10.02 Y");
        // events
        // misc
        dbHelper.addClassUpgrade(Class.MERCHANT, Class.ORE_MERCHANT, ch);
        dbHelper.addClassUpgrade(Class.FARMER, Class.FAST_GROWER, ch);
        dbHelper.addClassUpgrade(Class.GEOMANCER, Class.VOICE_OF_THE_EARTH, ch);
        // appearances/mentions
        dbHelper.addWorldAppearance(World.INNWORLD, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.IZRIL, ch);
        dbHelper.addLandmassOceanMention(LandmassOcean.NORTHERN_IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.SOUTHERN_IZRIL, ch);
        dbHelper.addLandmassOceanAppearance(LandmassOcean.NEW_LANDS_OF_IZRIL, ch);
        dbHelper.addNationMention(Nation.FIVE_FAMILIES_TERRITORY, ch);
        dbHelper.addNationMention(Nation.PALLASS, ch);
        dbHelper.addSettlementMention(Settlement.PALLASS, ch);
        // mention of House Byres lands
        dbHelper.addNationAppearance(Nation.DRAKE_TERRITORY, ch);
        dbHelper.addNationMention(Nation.HIVELANDS, ch);
        dbHelper.addSettlementMention(Settlement.INVRISIL, ch);
        dbHelper.addNationMention(Nation.MANUS, ch);
        dbHelper.addSettlementMention(Settlement.MANUS, ch);
        dbHelper.addNationMention(Nation.GAIIL_DROME, ch);
        dbHelper.addNationMention(Nation.CLAIVEN_EARTH, ch);
        dbHelper.addNationMention(Nation.NOMBERNAUGHT, ch);
        dbHelper.addSettlementMention(Settlement.NOMBERNAUGHT, ch);
        dbHelper.addSettlementAppearance(Settlement.GOISEDALL, ch);
        dbHelper.addNationAppearance(Nation.INSERELADREANUM, ch);
        dbHelper.addSettlementAppearance(Settlement.INSERELADREANUM, ch);
        dbHelper.addClassMention(Class.GUARD, ch);
        dbHelper.addClassMention(Class.MERCHANT, ch);
        dbHelper.addClassMention(Class.ORE_MERCHANT, ch);
        dbHelper.addClassMention(Class.PATROL_LEADER, ch);
        dbHelper.addClassMention(Class.ROGUE, ch);
        dbHelper.addClassMention(Class.CHEF, ch);
        dbHelper.addClassMention(Class.COOK, ch);
        dbHelper.addClassMention(Class.ADVENTURER, ch);
        dbHelper.addClassMention(Class.TAILOR, ch);
        dbHelper.addClassMention(Class.HUNTER, ch);
        dbHelper.addClassMention(Class.PROSPECTOR, ch);
        dbHelper.addClassMention(Class.AXE_CHAMPION, ch);
        dbHelper.addClassMention(Class.COOK_HELPER, ch);
        dbHelper.addClassMention(Class.MAGE, ch);
        dbHelper.addClassMention(Class.GATHERER, ch);
        dbHelper.addClassMention(Class.RANGER, ch);
        dbHelper.addClassMention(Class.FARMER, ch);
        dbHelper.addClassMention(Class.SCOUT, ch);
        dbHelper.addClassMention(Class.TRACKER, ch);
        dbHelper.addClassMention(Class.FAST_GROWER, ch);
        dbHelper.addClassMention(Class.LADY, ch);
        dbHelper.addClassMention(Class.HORSETHIEF, ch);
        dbHelper.addClassMention(Class.COURT_MAGE, ch);
        dbHelper.addClassMention(Class.WARRIOR, ch);
        dbHelper.addClassMention(Class.LADY_OF_THE_WOODS, ch);
        dbHelper.addClassMention(Class.VOICE_OF_THE_EARTH, ch);
        dbHelper.addClassMention(Class.DRUID, ch);
        dbHelper.addClassMention(Class.EARTH_MAGE, ch);
        dbHelper.addClassMention(Class.TREETENDER, ch);
        dbHelper.addClassMention(Class.DUELIST, ch);
        dbHelper.addClassMention(Class.WARDEN, ch);
        dbHelper.addClassMention(Class.GARDENER, ch);
        dbHelper.addClassMention(Class.GEOMANCER, ch);

        dbHelper.addSkillMention(Skill.BLINDNESS, ch);
        dbHelper.addSkillMention(Skill.MANA_BARRIER, ch);
        dbHelper.addSkillMention(Skill.STABILIZED_GROUND, ch);
        dbHelper.addSkillMention(Skill.STONE_FLOOR, ch);
        dbHelper.addSkillMention(Skill.MAGIC_PICTURE, ch);
        dbHelper.addSkillMention(Skill.MESSAGE, ch);
        dbHelper.addSkillMention(Skill.DETECT_LIFE, ch);
        dbHelper.addSkillMention(Skill.EAGLE_EYES, ch);
        dbHelper.addSkillMention(Skill.EARTH_WALL, ch);
        // Caravan Unslowed (Mild Terrain), Fast Growth, Detect Flaw: Extended Range, Area: Fleet of Foot, Meet the Deadline: Rush Work, Ignite Creation, Read Between the Lines, [Exquisite Insight]. [Survey the Competition]. [Resource Locator: The Passphrase of Imlerith]
        // Drakes, Waisrabbit, Lamprey Shufflers, Kelpie, Featherballs, landsharks, Bushpig, Sword Crabs
        // Ylawes, Dawil, Falene’s, Yorrned, Infinitypear, Rasktooth, Insill, Dasha, Larr, Pekona, and Anith, Dulc, Halrac’s, Tivete (fem Merchant), Chef Votto, Crossbow Stan, Zedalien, Maviola El, Deilan El, Irurx, Hareithion, Warden Jespeire and one of his people, Iturtexi, Ruveden, Voice Ikeiret, King of Duels, [Lady of the Woods] herself, Ruveden
        // Byres Family, Yorrned Caravan, Vuliel Drae, Silver Swords, Poke Duo, House El
    }

}
