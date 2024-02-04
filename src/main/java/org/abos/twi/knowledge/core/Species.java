package org.abos.twi.knowledge.core;

import org.abos.common.Named;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record Species(String name, boolean canLevel, String wikiLink) implements Named, Comparable<Species> {

    public static final String ACID_FLY = "Acid Fly";

    public static final String ANTINIUM = "Antinium";

    public static final String DJINN = "Djinn";

    public static final String DEMON = "Demon";

    public static final String DRAGON = "Dragon";

    public static final String FLATFISH = "Flatfish";

    public static final String GOBLIN = "Goblin";

    public static final String HOLLOWSTONE_DECEIVER = "Hollowstone Deceiver";

    public static final String HUMAN = "Human";

    public static final String RAZORBEAK = "Razorbeak";

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
