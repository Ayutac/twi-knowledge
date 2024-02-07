package org.abos.twi.knowledge.core;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Character(String wikiLink) implements Named {

    public static final Character AZKERASH = new Character(WikiHelper.WIKI_URL + "Az’kerash");
    public static final Character BEILMARK = new Character(WikiHelper.WIKI_URL + "Beilmark");
    public static final Character ERIN = new Character(WikiHelper.WIKI_URL + "Erin_Solstice");
    public static final Character FLOODED_WATERS_CHIEFTAIN = new Character(WikiHelper.WIKI_URL + "Goblin_Chieftain");
    public static final Character KLBKCH = new Character(WikiHelper.WIKI_URL + "Klbkch");
    public static final Character MAGNOLIA = new Character(WikiHelper.WIKI_URL + "Magnolia_Reinhart");
    public static final Character NANETTE = new Character(WikiHelper.WIKI_URL + "Nanette_Weishart");
    public static final Character PISCES = new Character(WikiHelper.WIKI_URL + "Pisces_Jealnet");
    public static final Character QUARASS = new Character(WikiHelper.WIKI_URL + "Quarass");
    public static final Character RAGS = new Character(WikiHelper.WIKI_URL + "Rags");
    public static final Character RELC = new Character(WikiHelper.WIKI_URL + "Relc_Grasstongue");
    public static final Character TERIARCH = new Character(WikiHelper.WIKI_URL + "Teriarch");
    public static final Character ZEVARA = new Character(WikiHelper.WIKI_URL + "Zevara_Sunderscale");

    public Character(final String wikiLink) {
        this.wikiLink = Objects.requireNonNull(wikiLink);
    }

    @Override
    public String getName() {
        final int index = wikiLink.substring(0, wikiLink.length()-1).lastIndexOf('/');
        if (index == -1) {
            return wikiLink.replace('_', ' ').replace("/", "");
        }
        return wikiLink.substring(index+1).replace('_', ' ').replace("/", "");
    }
}
