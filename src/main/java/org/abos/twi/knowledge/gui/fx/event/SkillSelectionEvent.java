package org.abos.twi.knowledge.gui.fx.event;

import javafx.event.Event;
import javafx.event.EventType;
import org.abos.twi.knowledge.core.Skill;

public class SkillSelectionEvent extends Event {

    public static final EventType<SkillSelectionEvent> TYPE = new EventType<>(Event.ANY, SkillSelectionEvent.class.getSimpleName() + "Type");

    protected final Skill skill;

    public SkillSelectionEvent(final Skill skill) {
        super(TYPE);
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }
}
