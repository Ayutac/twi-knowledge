package org.abos.twi.knowledge.core;

import java.util.Objects;

public record Volume(String name, String wikiLink) implements Comparable<Volume> {

    public Volume(final String name, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.wikiLink = Objects.requireNonNull(wikiLink);
    }

    @Override
    public int compareTo(final Volume other) {
        return name.compareTo(other.name);
    }
}
