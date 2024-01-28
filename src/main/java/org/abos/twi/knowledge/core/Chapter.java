package org.abos.twi.knowledge.core;

import java.time.LocalDate;
import java.util.Objects;

public record Chapter(String name, int volumeNo, Integer bookNo, LocalDate release, int words, Book book, Volume volume, String link, String wikiLink) implements Comparable<Chapter> {

    public Chapter(final String name, final int volumeNo, final Integer bookNo, final LocalDate release, final int words, final Book book, final Volume volume, final String link, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        if (volumeNo <= 0) {
            throw new IllegalArgumentException("Numbering within the volume must be positive!");
        }
        this.volumeNo = volumeNo;
        if (bookNo != null && bookNo <= 0) {
            throw new IllegalArgumentException("Numbering within the book must be positive!");
        }
        this.bookNo = bookNo;
        this.release = Objects.requireNonNull(release);
        if (words <= 0) {
            throw new IllegalArgumentException("Words must be positive!");
        }
        this.words = words;
        this.book = book;
        this.volume = Objects.requireNonNull(volume);
        this.link = Objects.requireNonNull(link);
        this.wikiLink = Objects.requireNonNull(wikiLink);
    }

    public boolean lettered() {
        return name.matches(".*\\d\\.\\d\\d\\s[A-Z].*");
    }

    public boolean interlude() {
        return name.startsWith("Interlude");
    }

    public boolean inParts() {
        return name.contains("Pt.");
    }

    @Override
    public int compareTo(final Chapter other) {
        final int cmpVol = volume.compareTo(other.volume);
        if (cmpVol != 0) {
            return cmpVol;
        }
        return volumeNo - other.volumeNo;
    }
}
