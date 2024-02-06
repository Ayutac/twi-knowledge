package org.abos.twi.knowledge.gui.fx.component;

import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.db.SQLFunction;
import org.abos.twi.knowledge.gui.fx.event.ClassSelectionEvent;

import java.util.List;

public final class CurrentClassSelection extends NamedSelection<Class> {
    public CurrentClassSelection(List<Class> selection, SQLFunction<String, Class> fetcher) {
        super("class", null, selection, fetcher);
    }

    @Override
    public void setSelected(final Class selected) {
        super.setSelected(selected);
        this.fireEvent(new ClassSelectionEvent(selected));
    }
}
