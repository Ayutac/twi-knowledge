package org.abos.twi.knowledge.gui.fx.event;

import javafx.event.Event;
import javafx.event.EventType;
import org.abos.twi.knowledge.core.Class;

public class ClassSelectionEvent extends Event {

    public static final EventType<ClassSelectionEvent> TYPE = new EventType<>(Event.ANY, ClassSelectionEvent.class.getSimpleName() + "Type");

    protected final Class clazz;

    public ClassSelectionEvent(final Class clazz) {
        super(TYPE);
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
