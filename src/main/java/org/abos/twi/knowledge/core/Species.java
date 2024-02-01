package org.abos.twi.knowledge.core;

import org.abos.common.Named;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record Species(String name, boolean canLevel, String wikiLink) implements Named, Comparable<Species> {

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
