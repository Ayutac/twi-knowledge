package org.abos.twi.knowledge.core;

import java.util.Objects;

public record Book(String name, Integer volumeOrd, Volume volume, String wikiLink, String sellLink, String audibleLink) implements Comparable<Book> {

    public Book(final String name, final Integer volumeOrd, final Volume volume, final String wikiLink, final String sellLink, final String audibleLink) {
        this.name = Objects.requireNonNull(name);
        if (volume == null ^ volumeOrd == null) {
            throw new IllegalArgumentException("Volume implies volume order and vice versa!");
        }
        this.volumeOrd = volumeOrd;
        this.volume = volume;
        this.wikiLink = Objects.requireNonNull(wikiLink);
        this.sellLink = Objects.requireNonNull(sellLink);
        this.audibleLink = audibleLink;
    }

    @Override
    public int compareTo(final Book other) {
        if (volume != null) {
            if (other.volume == null) {
                return -1;
            }
            final int volCmp = volume.compareTo(other.volume);
            if (volCmp != 0) {
                return volCmp;
            }
        }
        else {
            if (other.volume != null) {
                return 1;
            }
        }
        return name.compareTo(other.name);
    }
}
