package org.abos.twi.knowledge.gui.fx.event;

import javafx.event.Event;
import javafx.event.EventType;
import org.abos.twi.knowledge.core.publication.Chapter;

public class ChapterSelectionEvent extends Event {

    public static final EventType<ChapterSelectionEvent> TYPE = new EventType<>(Event.ANY, ChapterSelectionEvent.class.getSimpleName() + "Type");

    protected final Chapter chapter;

    public ChapterSelectionEvent(final Chapter chapter) {
        super(TYPE);
        this.chapter = chapter;
    }

    public Chapter getChapter() {
        return chapter;
    }
}
