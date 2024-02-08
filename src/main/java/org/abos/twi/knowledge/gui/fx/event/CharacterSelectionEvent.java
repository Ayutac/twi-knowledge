package org.abos.twi.knowledge.gui.fx.event;

import javafx.event.Event;
import javafx.event.EventType;
import org.abos.twi.knowledge.gui.CharacterNamed;

public class CharacterSelectionEvent extends Event {

    public static final EventType<CharacterSelectionEvent> TYPE = new EventType<>(Event.ANY, CharacterSelectionEvent.class.getSimpleName() + "Type");

    protected final CharacterNamed character;

    public CharacterSelectionEvent(final CharacterNamed character) {
        super(TYPE);
        this.character = character;
    }

    public CharacterNamed getCharacter() {
        return character;
    }
}
