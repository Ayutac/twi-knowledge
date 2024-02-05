package org.abos.twi.knowledge.core;

import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record ClassUpgrade(Class baseClass, Class upgradeClass, Chapter chapter) implements Comparable<ClassUpgrade> {

    public ClassUpgrade(final Class baseClass, final Class upgradeClass, final Chapter chapter) {
        this.baseClass = Objects.requireNonNull(baseClass);
        this.upgradeClass = Objects.requireNonNull(upgradeClass);
        this.chapter = Objects.requireNonNull(chapter);
    }

    @Override
    public int compareTo(final ClassUpgrade other) {
        final int cmpBase = baseClass.compareTo(other.baseClass);
        if (cmpBase != 0) {
            return cmpBase;
        }
        return upgradeClass.compareTo(other.upgradeClass);
    }
}
