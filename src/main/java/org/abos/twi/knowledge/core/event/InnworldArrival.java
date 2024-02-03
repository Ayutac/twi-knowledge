package org.abos.twi.knowledge.core.event;

import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record InnworldArrival(Character character, Chapter chapter) {

    public InnworldArrival(final Character character, final Chapter chapter) {
        this.character = Objects.requireNonNull(character);
        this.chapter = Objects.requireNonNull(chapter);
    }

}
