package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.time.LocalDate;
import java.util.Objects;

public record Book(String name, Integer volumeOrd, Volume volume, String wikiLink, String publicationLink, LocalDate publicationDate, String audibleLink, LocalDate audibleDate) implements Named, Comparable<Book> {

    public Book(final String name, final Integer volumeOrd, final Volume volume, final String wikiLink, final String publicationLink, final LocalDate publicationDate, final String audibleLink, final LocalDate audibleDate) {
        this.name = Objects.requireNonNull(name);
        if (volume == null ^ volumeOrd == null) {
            throw new IllegalArgumentException("Volume implies volume order and vice versa!");
        }
        this.volumeOrd = volumeOrd;
        this.volume = volume;
        this.wikiLink = Objects.requireNonNull(wikiLink);
        this.publicationLink = publicationLink;
        this.publicationDate = publicationDate;
        this.audibleLink = audibleLink;
        this.audibleDate = audibleDate;
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

    @Override
    public String getName() {
        return name;
    }
}
