package org.abos.twi.knowledge.core.event;

import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public record FirstMeeting(Character character1, Character character2, Chapter chapter) {

    public FirstMeeting(final Character character1, final Character character2, final Chapter chapter) {
        this.character1 = Objects.requireNonNull(character1);
        this.character2 = Objects.requireNonNull(character2);
        if (character1.equals(character2)) {
            throw new IllegalArgumentException("First Meeting can't be between oneself!");
        }
        this.chapter = Objects.requireNonNull(chapter);
    }

    public static List<FirstMeeting> generateMeetings(final Character[] characters, final Chapter chapter) {
        if (characters.length <= 1) {
            return List.of();
        }
        final List<FirstMeeting> result = new LinkedList<>();
        for (int i = 0; i < characters.length-1; i++) {
            for (int j = i+1; j < characters.length; j++) {
                result.add(new FirstMeeting(characters[i], characters[j], chapter));
            }
        }
        return result;
    }

    public static List<FirstMeeting> generateMeetings(final Character[] characterGroup1, final Character[] characterGroup2, final Chapter chapter) {
        if (characterGroup1.length == 0 || characterGroup2.length == 0) {
            return List.of();
        }
        final List<FirstMeeting> result = new LinkedList<>();
        for (int i = 0; i < characterGroup1.length; i++) {
            for (int j = 0; j < characterGroup2.length; j++) {
                result.add(new FirstMeeting(characterGroup1[i], characterGroup2[j], chapter));
            }
        }
        return result;
    }

}
