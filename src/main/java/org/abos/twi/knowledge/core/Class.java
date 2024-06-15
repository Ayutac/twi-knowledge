package org.abos.twi.knowledge.core;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Class(String name, String wikiLink) implements Named, Comparable<Class> {

    public static final Class ADMIRAL = new Class("Admiral", WikiHelper.WIKI_URL + "Admirals");
    public static final Class ADVENTURER = new Class("Adventurer", WikiHelper.WIKI_URL + "Adventurers");
    public static final Class AEGISCASTER = new Class("Aegiscaster", WikiHelper.WIKI_URL + "Aegiscasters");
    public static final Class ALCHEMIST = new Class("Alchemist", WikiHelper.WIKI_URL + "Alchemists");
    public static final Class AMBASSADOR = new Class("Ambassador", WikiHelper.WIKI_URL + "Ambassadors");
    public static final Class ANGLER = new Class("Angler", WikiHelper.WIKI_URL + "Anglers");
    public static final Class APPRENTICE = new Class("Apprentice", WikiHelper.WIKI_URL + "Apprentices");
    public static final Class ARCHER = new Class("Archer", WikiHelper.WIKI_URL + "Archers");
    public static final Class ARCHITECT = new Class("Architect", WikiHelper.WIKI_URL + "Architects");
    public static final Class ARCHMAGE = new Class("Archmage", WikiHelper.WIKI_URL + "Archmages");
    public static final Class AXE_CHAMPION = new Class("Axe Champion", WikiHelper.WIKI_URL + "Axe_Champions");
    public static final Class BANDIT = new Class("Bandit", WikiHelper.WIKI_URL + "Bandits");
    public static final Class BARD = new Class("Bard", WikiHelper.WIKI_URL + "Bards");
    public static final Class BARMAID = new Class("Barmaid", WikiHelper.WIKI_URL + "Barmaids");
    public static final Class BATTLEMAGE = new Class("Battlemage", WikiHelper.WIKI_URL + "Battlemages");
    public static final Class BLACKMAILER = new Class("Blackmailer", null);
    public static final Class BLOODEARTH_MAGE = new Class("Bloodearth Mage", WikiHelper.WIKI_URL + "Bloodearth Mages");
    public static final Class BODYGUARD = new Class("Bodyguard", WikiHelper.WIKI_URL + "Bodyguards");
    public static final Class BUTCHER = new Class("Butcher", WikiHelper.WIKI_URL + "Butchers");
    public static final Class CAPTAIN = new Class("Captain", WikiHelper.WIKI_URL + "Captains");
    public static final Class CARVER = new Class("Carver", WikiHelper.WIKI_URL + "Carvers");
    public static final Class CHEF = new Class("Chef", WikiHelper.WIKI_URL + "Chefs");
    public static final Class CHIEFTAIN = new Class("Chieftain", WikiHelper.WIKI_URL + "Chieftains");
    public static final Class COLLECTOR = new Class("Collector", WikiHelper.WIKI_URL + "Collectors");
    public static final Class COOK = new Class("Cook", WikiHelper.WIKI_URL + "Cooks");
    public static final Class COOK_HELPER = new Class("Cook Helper", WikiHelper.WIKI_URL + "Cook Helpers");
    public static final Class COUNCILWOMAN = new Class("Councilwoman", WikiHelper.WIKI_URL + "Councilwomen");
    public static final Class COURIER = new Class("Courier", WikiHelper.WIKI_URL + "Couriers");
    public static final Class COURT_MAGE = new Class("Court Mage", WikiHelper.WIKI_URL + "Court_Mages");
    public static final Class CRIMINAL_MASTERMIND = new Class("Criminal Mastermind", WikiHelper.WIKI_URL + "Criminal Masterminds");
    public static final Class DIPLOMAT = new Class("Diplomat", WikiHelper.WIKI_URL + "Diplomats");
    public static final Class DOWSER = new Class("Dowser", WikiHelper.WIKI_URL + "Dowsers");
    public static final Class DRIVER = new Class("Driver", WikiHelper.WIKI_URL + "Drivers");
    public static final Class DRUID = new Class("Druid", WikiHelper.WIKI_URL + "Druids");
    public static final Class DUELIST = new Class("Duelist", WikiHelper.WIKI_URL + "Duelists");
    public static final Class EARTH_MAGE = new Class("Earth Mage", WikiHelper.WIKI_URL + "Earth_Mages");
    public static final Class ELITE_GUARD = new Class("Elite Guard", WikiHelper.WIKI_URL + "Elite_Guards");
    public static final Class EMISSARY = new Class("Emissary", WikiHelper.WIKI_URL + "Emissaries");
    public static final Class ENCHANTER = new Class("Enchanter", WikiHelper.WIKI_URL + "Enchanters");
    public static final Class EXPENDABLE_GUARD = new Class("Expendable Guard", WikiHelper.WIKI_URL + "Expendable_Guards");
    public static final Class FARMER = new Class("Farmer", WikiHelper.WIKI_URL + "Farmers");
    public static final Class FARMHAND = new Class("Farmhand", WikiHelper.WIKI_URL + "Farmhands");
    public static final Class FAST_GROWER = new Class("Fast Grower", WikiHelper.WIKI_URL + "Fast_Growers");
    public static final Class FINANCIER = new Class("Financier", WikiHelper.WIKI_URL + "Financiers");
    public static final Class FISHER = new Class("Fisher", WikiHelper.WIKI_URL + "Fishers");
    public static final Class FLOOR_BOSS = new Class("Floor Boss", WikiHelper.WIKI_URL + "Floor_Bosses");
    public static final Class FOREMAN = new Class("Foreman", WikiHelper.WIKI_URL + "Foremen");
    public static final Class FORGER = new Class("Forger", WikiHelper.WIKI_URL + "Forgers");
    public static final Class GARDENER = new Class("Gardener", WikiHelper.WIKI_URL + "Gardeners");
    public static final Class GATHERER = new Class("Gatherer", WikiHelper.WIKI_URL + "Gatherers");
    public static final Class GEMSTONE_SMELTER = new Class("Gemstone Smelter", WikiHelper.WIKI_URL + "Gemstone Smelters");
    public static final Class GEOMANCER = new Class("Geomancer", WikiHelper.WIKI_URL + "Geomancers");
    public static final Class GOOD_PERSON = new Class("Good Person", null);
    public static final Class GRANDMASTER = new Class("Grandmaster", WikiHelper.WIKI_URL + "Grandmasters");
    public static final Class GRAVEKEEPER = new Class("Gravekeeper", WikiHelper.WIKI_URL + "Gravekeepers");
    public static final Class GUARD = new Class("Guard", WikiHelper.WIKI_URL + "Watch");
    public static final Class GUARDSMAN = new Class("Guardsman", WikiHelper.WIKI_URL + "Watch");
    public static final Class GUARDSWOMAN = new Class("Guardwoman", WikiHelper.WIKI_URL + "Watch");
    public static final Class GUILDMASTER = new Class("Guildmaster", WikiHelper.WIKI_URL + "Guildmasters");
    public static final Class HANDLER = new Class("Handler", WikiHelper.WIKI_URL + "Handlers");
    public static final Class HANDY_WORKER = new Class("Handy Worker", WikiHelper.WIKI_URL + "Handy_Workers");
    public static final Class HEAD_SERVER = new Class("Head Server", WikiHelper.WIKI_URL + "Head_Servers");
    public static final Class HEAD_SERVER_OF_TALES_AND_FABLES = new Class("Head Server of Tales and Fables", WikiHelper.WIKI_URL + "Head_Servers_of_Tales_and_Fables");
    public static final Class HEALER = new Class("Healer", WikiHelper.WIKI_URL + "Healers");
    public static final Class HEIRESS = new Class("Heiress", WikiHelper.WIKI_URL + "Heiresses");
    public static final Class HERO = new Class("Hero", WikiHelper.WIKI_URL + "Heroes");
    public static final Class HIGH_MAGE = new Class("High Mage", WikiHelper.WIKI_URL + "High_Mages");
    public static final Class HORRORBANE_ADVENTURER = new Class("Horrorbane Adventurer", WikiHelper.WIKI_URL + "Horrorbane_Adventurers");
    public static final Class HORSETHIEF = new Class("Horsethief", WikiHelper.WIKI_URL + "Horsethieves");
    public static final Class HUNTER = new Class("Hunter", WikiHelper.WIKI_URL + "Hunters");
    public static final Class INNKEEPER = new Class("Innkeeper", WikiHelper.WIKI_URL + "Innkeepers");
    public static final Class JEWELER = new Class("Jeweler", WikiHelper.WIKI_URL + "Jewelers");
    public static final Class KING = new Class("King", WikiHelper.WIKI_URL + "Kings");
    public static final Class KNIGHT = new Class("Knight", WikiHelper.WIKI_URL + "Knights");
    public static final Class KNIGHT_SEEKER_OF_THE_SILVER_DRAGON = new Class("Knight-Seeker of the Silver Dragon", WikiHelper.WIKI_URL + "Knight-Seekers_of_the_Silver_Dragon");
    public static final Class LADY = new Class("Lady", WikiHelper.WIKI_URL + "Ladies");
    public static final Class LADY_OF_THE_WOODS = new Class("Lady of the Woods", WikiHelper.WIKI_URL + "Ladies_of_the_Woods");
    public static final Class LANDLORD = new Class("Landlord", WikiHelper.WIKI_URL + "Landlords");
    public static final Class LORD = new Class("Lord", WikiHelper.WIKI_URL + "Lords");
    public static final Class MAGE = new Class("Mage", WikiHelper.WIKI_URL + "Mages");
    public static final Class MAGE_SCRIBE = new Class("Mage Scribe", WikiHelper.WIKI_URL + "Mage Scribes");
    public static final Class MAGIC_MERCHANT = new Class("Magic Merchant", WikiHelper.WIKI_URL + "Magic_Merchants");
    public static final Class MAID = new Class("Maid", WikiHelper.WIKI_URL + "Maids");
    public static final Class MAJORDOMO = new Class("Majordomo", WikiHelper.WIKI_URL + "Majordomos");
    public static final Class MATHEMATICIAN = new Class("Mathematician", WikiHelper.WIKI_URL + "Mathematicians");
    public static final Class MERCHANT = new Class("Merchant", WikiHelper.WIKI_URL + "Merchants");
    public static final Class MINER = new Class("Miner", WikiHelper.WIKI_URL + "Miners");
    public static final Class MINING_FOREMAN = new Class("Mining Foreman", WikiHelper.WIKI_URL + "Mining Foremen");
    public static final Class MOTHER = new Class("Mother", WikiHelper.WIKI_URL + "Mothers");
    public static final Class MUSICIAN = new Class("Musician", WikiHelper.WIKI_URL + "Mothers");
    public static final Class NECROMANCER = new Class("Necromancer", WikiHelper.WIKI_URL + "Necromancers");
    public static final Class NEGOTIATOR = new Class("Negotiator", WikiHelper.WIKI_URL + "Negotiators");
    public static final Class ORE_MERCHANT = new Class("Ore Merchant", WikiHelper.WIKI_URL + "Ore_Merchants");
    public static final Class OVERLANDS_ADVENTURER = new Class("Overlands Adventurer", WikiHelper.WIKI_URL + "Overlands_Adventurers");
    public static final Class PAINTER = new Class("Painter", WikiHelper.WIKI_URL + "Painters");
    public static final Class PATROL_LEADER = new Class("Patrol Leader", WikiHelper.WIKI_URL + "Patrol_Leaders");
    public static final Class PIRATE = new Class("Pirate", WikiHelper.WIKI_URL + "Pirates");
    public static final Class POET = new Class("Poet", WikiHelper.WIKI_URL + "Poets");
    public static final Class PRINCE = new Class("Prince", WikiHelper.WIKI_URL + "Princes");
    public static final Class PRINCE_OF_MEN = new Class("Prince of Men", WikiHelper.WIKI_URL + "Princes_of_Men");
    public static final Class PRINCESS = new Class("Princess", WikiHelper.WIKI_URL + "Princesses");
    public static final Class PRINCESS_OF_THE_INN = new Class("Princess of the Inn", WikiHelper.WIKI_URL + "Princesses_of_the_Inn");
    public static final Class PROSPECTOR = new Class("Prospector", WikiHelper.WIKI_URL + "Prospectors");
    public static final Class QUEEN = new Class("Queen", WikiHelper.WIKI_URL + "Queens");
    public static final Class RANGER = new Class("Ranger", WikiHelper.WIKI_URL + "Rangers");
    public static final Class RECEPTIONIST = new Class("Receptionist", WikiHelper.WIKI_URL + "Receptionists");
    public static final Class RECRUITER = new Class("Recruiter", WikiHelper.WIKI_URL + "Recruiters");
    public static final Class ROGUE = new Class("Rogue", WikiHelper.WIKI_URL + "Rogues");
    public static final Class ROOKIE_GUARD = new Class("Rookie Guard", WikiHelper.WIKI_URL + "Rookie_Guards");
    public static final Class RULER = new Class("Ruler", WikiHelper.WIKI_URL + "Rookie_Guards");
    public static final Class SCHOLAR = new Class("Scholar", WikiHelper.WIKI_URL + "Scholars");
    public static final Class SCOUT = new Class("Scout", WikiHelper.WIKI_URL + "Scouts");
    public static final Class SCRIBBLER = new Class("Scribbler", WikiHelper.WIKI_URL + "Scribblers");
    public static final Class SERGEANT = new Class("Sergeant", WikiHelper.WIKI_URL + "Sergeants");
    public static final Class SERVANT = new Class("Servant", WikiHelper.WIKI_URL + "Servants");
    public static final Class SHAMAN = new Class("Shaman", WikiHelper.WIKI_URL + "Shamans");
    public static final Class SHOPKEEPER = new Class("Shopkeeper", WikiHelper.WIKI_URL + "Shamans");
    public static final Class SHEPHERD = new Class("Shepherd", WikiHelper.WIKI_URL + "Shepherds");
    public static final Class SNEAK_THIEF = new Class("Sneak Thief", WikiHelper.WIKI_URL + "Thieves");
    public static final Class SOLDIER = new Class("Soldier", WikiHelper.WIKI_URL + "Soldiers");
    public static final Class SORCERER = new Class("Sorcerer", WikiHelper.WIKI_URL + "Sorcerers");
    public static final Class SPEARMASTER = new Class("Spearmaster", WikiHelper.WIKI_URL + "Spearmasters");
    public static final Class SPICE_CHEF = new Class("Spice Chef", WikiHelper.WIKI_URL + "Spice_Chefs");
    public static final Class SPY = new Class("Spy", WikiHelper.WIKI_URL + "Spies");
    public static final Class SQUIRE = new Class("Squire", WikiHelper.WIKI_URL + "Squires");
    public static final Class STRATEGIST = new Class("Strategist", WikiHelper.WIKI_URL + "Strategists");
    public static final Class SUAVE_LORD = new Class("Suave Lord", WikiHelper.WIKI_URL + "Suave_Lords");
    public static final Class SURVIVOR = new Class("Survivor", WikiHelper.WIKI_URL + "Survivors");
    public static final Class SWORDMASTER = new Class("Swordmaster", WikiHelper.WIKI_URL + "Swordmasters");
    public static final Class SWORDSMAN = new Class("Swordsman", WikiHelper.WIKI_URL + "Swordsmen");
    public static final Class SWORDSLAYER = new Class("Swordslayer", WikiHelper.WIKI_URL + "Swordslayers");
    public static final Class TACTICIAN = new Class("Tactician", WikiHelper.WIKI_URL + "Tailors");
    public static final Class TAILOR = new Class("Tailor", WikiHelper.WIKI_URL + "Tailors");
    public static final Class TANTRIC_DRUID = new Class("Tantric Druid", WikiHelper.WIKI_URL + "Tantric Druids");
    public static final Class THIEF = new Class("Thief", WikiHelper.WIKI_URL + "Thieves");
    public static final Class THUG = new Class("Thug", WikiHelper.WIKI_URL + "Thugs");
    public static final Class TRACKER = new Class("Tracker", WikiHelper.WIKI_URL + "Trackers");
    public static final Class TRADER = new Class("Trader", WikiHelper.WIKI_URL + "Traders");
    public static final Class TRAINEE = new Class("Trainee", WikiHelper.WIKI_URL + "Trainees");
    public static final Class TREETENDER = new Class("Treetender", WikiHelper.WIKI_URL + "Treetenders");
    public static final Class TUTOR = new Class("Tutor", WikiHelper.WIKI_URL + "Tutors");
    public static final Class VILLAGER = new Class("Villager", WikiHelper.WIKI_URL + "Villagers");
    public static final Class VOICE_OF_THE_EARTH = new Class("Voice of the Earth", WikiHelper.WIKI_URL + "Voices_of_the_Earth");
    public static final Class WARDEN = new Class("Warden", WikiHelper.WIKI_URL + "Wardens");
    public static final Class WARLOCK = new Class("Warlock", WikiHelper.WIKI_URL + "Warlocks");
    public static final Class WARLORD = new Class("Warlord", WikiHelper.WIKI_URL + "Warlords");
    public static final Class WARRIOR = new Class("Warrior", WikiHelper.WIKI_URL + "Warriors");
    public static final Class WELL_FINDER = new Class("Well Finder", WikiHelper.WIKI_URL + "Well_Finders");
    public static final Class WITCH = new Class("Witch", WikiHelper.WIKI_URL + "Witches");
    public static final Class WIZARD = new Class("Wizard", WikiHelper.WIKI_URL + "Wizards");
    public static final Class WOODCUTTER = new Class("Woodcutter", WikiHelper.WIKI_URL + "Woodcutters");
    public static final Class WORLDLY_PRINCESS = new Class("Worldly Princess", WikiHelper.WIKI_URL + "Worldly_Princesses");

    public Class(final String name, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(final Class other) {
        return name.compareTo(other.name);
    }
}
