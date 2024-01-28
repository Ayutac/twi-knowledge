package org.abos.twi.knowledge.core;

import java.util.Objects;

public record Book(String name, Volume volume, String wikiLink) implements Comparable<Book> {

    public Book(final String name, final Volume volume, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.volume = Objects.requireNonNull(volume);
        this.wikiLink = Objects.requireNonNull(wikiLink);
    }

    @Override
    public int compareTo(final Book other) {
        return name.compareTo(other.name);
    }
}
