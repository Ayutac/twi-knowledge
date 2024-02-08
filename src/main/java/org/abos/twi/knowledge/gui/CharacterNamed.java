package org.abos.twi.knowledge.gui;

import org.abos.common.Named;
import org.abos.twi.knowledge.core.Character;

import java.util.Objects;

public record CharacterNamed(Character character, String name) implements Named, Comparable<CharacterNamed> {

    public CharacterNamed(final Character character, final String name) {
        this.character = Objects.requireNonNull(character);
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(final CharacterNamed other) {
        final int nameCmp = name.compareTo(other.name);
        if (nameCmp != 0) {
            return nameCmp;
        }
        return character.wikiLink().compareTo(other.character.wikiLink());
    }

    @Override
    public String toString() {
        return name;
    }
}
