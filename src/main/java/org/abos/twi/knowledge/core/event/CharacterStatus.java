package org.abos.twi.knowledge.core.event;

import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.Status;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record CharacterStatus(Status status, Character character, Chapter since) {

    public CharacterStatus(final Status status, final Character character, final Chapter since) {
        this.status = Objects.requireNonNull(status);
        this.character = Objects.requireNonNull(character);
        this.since = Objects.requireNonNull(since);
    }

}
