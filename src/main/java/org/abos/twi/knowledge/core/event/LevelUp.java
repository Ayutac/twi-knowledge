package org.abos.twi.knowledge.core.event;

import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record LevelUp(Character character, Chapter chapter, Integer newLevel, Class clazz, boolean capstone, boolean canceled) {

    public LevelUp(final Character character, final Chapter chapter, final Integer newLevel, final Class clazz, final boolean capstone, final boolean canceled) {
        this.character = Objects.requireNonNull(character);
        this.chapter = Objects.requireNonNull(chapter);
        this.newLevel = newLevel;
        this.clazz = clazz;
        this.capstone = capstone;
        this.canceled = canceled;
    }

}
