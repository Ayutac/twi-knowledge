package org.abos.twi.knowledge.gui.fx.component;

import org.abos.twi.knowledge.core.Skill;
import org.abos.twi.knowledge.db.SQLFunction;
import org.abos.twi.knowledge.gui.fx.event.SkillSelectionEvent;

import java.util.List;

public final class CurrentSkillSelection extends NamedSelection<Skill> {
    public CurrentSkillSelection(List<Skill> selection, SQLFunction<String, Skill> fetcher) {
        super("skill", null, selection, fetcher);
    }

    @Override
    public void setSelected(final Skill selected) {
        super.setSelected(selected);
        this.fireEvent(new SkillSelectionEvent(selected));
    }
}
