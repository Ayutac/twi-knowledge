package org.abos.twi.knowledge.db.datafill;

import org.abos.common.LogUtil;
import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.core.Skill;
import org.abos.twi.knowledge.core.Species;
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
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Volume 10 data filling", time.toMinutes(), time.toSecondsPart());
    }

    private static void ch00l(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 10.00 L");
        // misc
        dbHelper.addClassSkill(Class.SCRIBBLER, Skill.FAST_SCRAWL, ch);
        dbHelper.addCharacterAge(Character.NANETTE, 13, ch);
        // (Hethon is mentioned to be 14)
        // (Sammiel is mentioned to be 10)
        dbHelper.addCharacterLastName(Character.ROSE, ch, "Cinevoy");
        dbHelper.addClassUpgrade(Class.LORD, Class.SUAVE_LORD, ch);
        dbHelper.addClassSkill(Class.QUEEN, Skill.PERFECTION_IS_OVERRATED, ch);
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

}
