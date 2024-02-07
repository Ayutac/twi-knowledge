package org.abos.twi.knowledge.core;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Species(String name, boolean canLevel, String wikiLink) implements Named, Comparable<Species> {

    public static final Species ACID_FLY = new Species("Acid Fly", false, WikiHelper.WIKI_URL + "Acid_Flies");
    public static final Species ANTINIUM = new Species("Antinium", true, WikiHelper.WIKI_URL + "Antinium");
    public static final Species BEASTKIN = new Species("Beastkin", false, WikiHelper.WIKI_URL + "Beastkin");
    public static final Species COCKROACH = new Species("Cockroach", false, null);
    public static final Species CORUSDEER = new Species("Corusdeer", false, WikiHelper.WIKI_URL + "Corusdeer");
    public static final Species DEMON = new Species("Demon", true, WikiHelper.WIKI_URL + "Demons");
    public static final Species DJINNI = new Species("Djinni", false, WikiHelper.WIKI_URL + "Djinni");
    public static final Species DRAKE = new Species("Drake", true, WikiHelper.WIKI_URL + "Drakes");
    public static final Species DRAGON = new Species("Dragon", false, WikiHelper.WIKI_URL + "Dragons");
    public static final Species DULLAHAN = new Species("Dullahan", true, WikiHelper.WIKI_URL + "Dullahans");
    public static final Species FLATFISH = new Species("Flatfish", false, null);
    public static final Species GARUDA = new Species("Garuda", true, WikiHelper.WIKI_URL + "Garuda");
    public static final Species GNOLL = new Species("Gnoll", true, WikiHelper.WIKI_URL + "Gnolls");
    public static final Species GOBLIN = new Species("Goblin", true, WikiHelper.WIKI_URL + "Goblins");
    public static final Species HALF_ELF = new Species("Half-Elf", true, WikiHelper.WIKI_URL + "Half-Elves");
    public static final Species HOLLOWSTONE_DECEIVER = new Species("Hollowstone Deceiver", false, WikiHelper.WIKI_URL + "Rock_Crabs");
    public static final Species HORSE = new Species("Horse", false, null);
    public static final Species HUMAN = new Species("Human", true, WikiHelper.WIKI_URL + "Humans");
    public static final Species RAZORBEAK = new Species("Razorbeak", false, WikiHelper.WIKI_URL + "Razorbeaks");
    public static final Species SHIELD_SPIDER = new Species("Shield Spider", false, WikiHelper.WIKI_URL + "Shield_Spiders");

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
