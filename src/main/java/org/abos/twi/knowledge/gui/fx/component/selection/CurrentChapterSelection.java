package org.abos.twi.knowledge.gui.fx.component.selection;

import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.SQLFunction;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;

import java.util.List;

public final class CurrentChapterSelection extends NamedSelection<Chapter> {
    public CurrentChapterSelection(final List<Chapter> selection, final SQLFunction<String, Chapter> fetcher) {
        super("chapter", "Your current chapter:", selection, fetcher);
    }

    @Override
    public void setSelected(final Chapter selected) {
        super.setSelected(selected);
        this.fireEvent(new ChapterSelectionEvent(selected));
    }
}
