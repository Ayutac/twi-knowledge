package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Class(String name, String wikiLink) implements Named {

    public static final String CHEF = "Chef";

    public static final String CHIEFTAIN = "Chieftain";

    public static final String GOOD_PERSON = "Good Person";

    public static final String GUARDSMAN = "Guardsman";

    public static final String GUARDWOMAN = "Guardwoman";

    public static final String HERO = "Hero";

    public static final String INNKEEPER = "Innkeeper";

    public static final String KNIGHT = "Knight";

    public static final String LADY = "Lady";

    public static final String LORD = "Lord";

    public static final String SHAMAN = "Shaman";

    public static final String SPEARMASTER = "Spearmaster";

    public static final String SURVIVOR = "Survivor";

    public static final String SWORDSLAYER = "Swordslayer";

    public static final String WARRIOR = "Warrior";

    public Class(final String name, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
