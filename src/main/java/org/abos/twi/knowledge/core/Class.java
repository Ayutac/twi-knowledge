package org.abos.twi.knowledge.core;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Class(String name, String wikiLink) implements Named {

    public static final Class CHEF = new Class("Chef", WikiHelper.WIKI_URL + "Chefs");
    public static final Class CHIEFTAIN = new Class("Chieftain", WikiHelper.WIKI_URL + "Chieftains");
    public static final Class GATHERER = new Class("Gatherer", WikiHelper.WIKI_URL + "Gatherers");
    public static final Class GOOD_PERSON = new Class("Good Person", null);
    public static final Class HERO = new Class("Hero", WikiHelper.WIKI_URL + "Heroes");
    public static final Class INNKEEPER = new Class("Innkeeper", WikiHelper.WIKI_URL + "Innkeepers");
    public static final Class KNIGHT = new Class("Knight", WikiHelper.WIKI_URL + "Knights");
    public static final Class LADY = new Class("Lady", WikiHelper.WIKI_URL + "Ladies");
    public static final Class LORD = new Class("Lord", WikiHelper.WIKI_URL + "Lords");
    public static final Class GUARD = new Class("Guard", WikiHelper.WIKI_URL + "Watch");
    public static final Class GUARDSMAN = new Class("Guardsman", WikiHelper.WIKI_URL + "Watch");
    public static final Class GUARDSWOMAN = new Class("Guardwoman", WikiHelper.WIKI_URL + "Watch");
    public static final Class SHAMAN = new Class("Shaman", WikiHelper.WIKI_URL + "Shamans");
    public static final Class SPEARMASTER = new Class("Spearmaster", WikiHelper.WIKI_URL + "Spearmasters");
    public static final Class SURVIVOR = new Class("Survivor", WikiHelper.WIKI_URL + "Survivors");
    public static final Class SWORDSLAYER = new Class("Swordslayer", WikiHelper.WIKI_URL + "Swordslayers");
    public static final Class WARRIOR = new Class("Warrior", WikiHelper.WIKI_URL + "Warriors");

    public Class(final String name, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
