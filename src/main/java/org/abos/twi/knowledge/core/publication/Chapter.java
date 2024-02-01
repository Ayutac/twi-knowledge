package org.abos.twi.knowledge.core.publication;

import org.abos.common.Named;

import java.time.LocalDate;
import java.util.Objects;

public record Chapter(String name, Integer volumeOrd, Integer bookOrd, LocalDate release, int words, Book book, Volume volume, String link, String wikiLink) implements Named, Comparable<Chapter> {

    public Chapter(final String name, final Integer volumeOrd, final Integer bookOrd, final LocalDate release, final int words, final Book book, final Volume volume, final String link, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        if (volume == null ^ volumeOrd == null) {
            throw new IllegalArgumentException("Volume implies volume order and vice versa!");
        }
        if (volumeOrd != null && volumeOrd <= 0) {
            throw new IllegalArgumentException("Numbering within the volume must be positive!");
        }
        this.volumeOrd = volumeOrd;
        if (book == null ^ bookOrd == null) {
            throw new IllegalArgumentException("Book implies book order and vice versa!");
        }
        if (bookOrd != null && bookOrd <= 0) {
            throw new IllegalArgumentException("Numbering within the book must be positive!");
        }
        this.bookOrd = bookOrd;
        this.release = Objects.requireNonNull(release);
        if (words <= 0) {
            throw new IllegalArgumentException("Words must be positive!");
        }
        this.words = words;
        this.book = book;
        this.volume = volume;
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
        return volumeOrd - other.volumeOrd;
    }

    @Override
    public String getName() {
        return name;
    }
}
