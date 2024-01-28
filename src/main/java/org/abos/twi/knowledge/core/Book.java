package org.abos.twi.knowledge.core;

import java.util.Objects;

public record Book(String name, Integer volumeOrd, Volume volume, String wikiLink) implements Comparable<Book> {

    public Book(final String name, final Integer volumeOrd, final Volume volume, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        if (volume == null ^ volumeOrd == null) {
            throw new IllegalArgumentException("Volume implies volume number and vice versa!");
        }
        this.volumeOrd = volumeOrd;
        this.volume = volume;
        this.wikiLink = Objects.requireNonNull(wikiLink);
    }

    @Override
    public int compareTo(final Book other) {
        return name.compareTo(other.name);
    }
}
