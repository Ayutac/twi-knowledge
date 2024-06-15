package org.abos.twi.knowledge.core;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Species(String name, boolean canLevel, String wikiLink) implements Named, Comparable<Species> {

    public static final Species ACID_FLY = new Species("Acid Fly", false, WikiHelper.WIKI_URL + "Acid_Flies");
    public static final Species ANTINIUM = new Species("Antinium", true, WikiHelper.WIKI_URL + "Antinium");
    public static final Species BEASTKIN = new Species("Beastkin", true, WikiHelper.WIKI_URL + "Beastkin");
    public static final Species BUSHPIG = new Species("Bushpig", false, WikiHelper.WIKI_URL + "Bushpig");
    public static final Species CARN_WOLF = new Species("Carn Wolf", false, WikiHelper.WIKI_URL + "Carn_Wolves");
    public static final Species CAVE_GOBLIN = new Species("Cave Goblin", true, WikiHelper.WIKI_URL + "Cave_Goblins");
    public static final Species CENTAUR = new Species("Centaur", true, WikiHelper.WIKI_URL + "Centaur");
    public static final Species COCKROACH = new Species("Cockroach", false, null);
    public static final Species CORUSDEER = new Species("Corusdeer", false, WikiHelper.WIKI_URL + "Corusdeer");
    public static final Species CRELER = new Species("Creler", false, WikiHelper.WIKI_URL + "Crelers");
    public static final Species DEMON = new Species("Demon", true, WikiHelper.WIKI_URL + "Demons");
    public static final Species DJINNI = new Species("Djinni", false, WikiHelper.WIKI_URL + "Djinni");
    public static final Species DRAKE = new Species("Drake", true, WikiHelper.WIKI_URL + "Drakes");
    public static final Species DRAGON = new Species("Dragon", false, WikiHelper.WIKI_URL + "Dragons");
    public static final Species DULLAHAN = new Species("Dullahan", true, WikiHelper.WIKI_URL + "Dullahans");
    public static final Species DWARF = new Species("Dwarf", true, WikiHelper.WIKI_URL + "Dwarves");
    public static final Species FEATHERBALL = new Species("Featherball", false, WikiHelper.WIKI_URL + "Featherballs");
    public static final Species FLATFISH = new Species("Flatfish", false, null);
    public static final Species FRAERLING = new Species("Fraerling", true, WikiHelper.WIKI_URL + "Fraerlings");
    public static final Species GARUDA = new Species("Garuda", true, WikiHelper.WIKI_URL + "Garuda");
    public static final Species GNOLL = new Species("Gnoll", true, WikiHelper.WIKI_URL + "Gnolls");
    public static final Species GOAT = new Species("Goat", false, null);
    public static final Species GOBLIN = new Species("Goblin", true, WikiHelper.WIKI_URL + "Goblins");
    public static final Species HALF_ELF = new Species("Half-Elf", true, WikiHelper.WIKI_URL + "Half-Elves");
    public static final Species HALF_DWARF = new Species("Half-Dwarf", true, WikiHelper.WIKI_URL + "Half-Dwarves");
    public static final Species HALF_GIANT = new Species("Half-Giant", true, WikiHelper.WIKI_URL + "Half-Giants");
    public static final Species HOLLOWSTONE_DECEIVER = new Species("Hollowstone Deceiver", false, WikiHelper.WIKI_URL + "Rock_Crabs");
    public static final Species HORSE = new Species("Horse", false, null);
    public static final Species HUMAN = new Species("Human", true, WikiHelper.WIKI_URL + "Humans");
    public static final Species KELPIE = new Species("Kelpie", false, WikiHelper.WIKI_URL + "Kelpies");
    public static final Species LAMPREY_SHUFFLER = new Species("Lamprey Shuffler", false, WikiHelper.WIKI_URL + "Lamprey_Shufflers");
    public static final Species LANDSHARK = new Species("Landshark", false, WikiHelper.WIKI_URL + "Landsharks");
    public static final Species MINOTAUR = new Species("Minotaur", true, WikiHelper.WIKI_URL + "Minotaurs");
    public static final Species MOTHBEAR = new Species("Mothbear", false, WikiHelper.WIKI_URL + "Mothbears");
    public static final Species ORANGUTAN = new Species("Orangutan", false, WikiHelper.WIKI_URL + "Orangutans");
    public static final Species RAZORBEAK = new Species("Razorbeak", false, WikiHelper.WIKI_URL + "Razorbeaks");
    public static final Species SELPHID = new Species("Selphid", true, WikiHelper.WIKI_URL + "Selphids");
    public static final Species SHIELD_SPIDER = new Species("Shield Spider", false, WikiHelper.WIKI_URL + "Shield_Spiders");
    public static final Species STITCH_FOLK = new Species("Stitch-Folk", true, WikiHelper.WIKI_URL + "String_People");
    public static final Species SWORD_CRAB = new Species("Sword Crab", false, WikiHelper.WIKI_URL + "Sword_Crabs");
    public static final Species UNDEAD = new Species("Undead", false, WikiHelper.WIKI_URL + "Undead");
    public static final Species UNICORN = new Species("Unicorn", false, WikiHelper.WIKI_URL + "Unicorns");
    public static final Species VAMPIRE = new Species("Vampire", false, WikiHelper.WIKI_URL + "Vampires");
    public static final Species WAISRABBIT = new Species("Waisrabbit", false, WikiHelper.WIKI_URL + "Waisrabbits");

    public Species(final String name, final boolean canLevel, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.canLevel = canLevel;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(final Species other) {
        return name.compareTo(other.name);
    }
}
