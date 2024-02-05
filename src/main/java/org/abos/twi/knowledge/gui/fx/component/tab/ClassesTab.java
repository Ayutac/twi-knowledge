package org.abos.twi.knowledge.gui.fx.component.tab;

import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.SQLFunction;
import org.abos.twi.knowledge.gui.fx.component.NamedSelection;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class ClassesTab extends Tab implements EventHandler<ChapterSelectionEvent> {

    private final SQLFunction<Chapter, Collection<Class>> fetchByChapter;
    private final NamedSelection<Class> classesSelection;

    public ClassesTab(final List<Class> initialClasses, SQLFunction<String, Class> fetchByName, SQLFunction<Chapter, Collection<Class>> fetchByChapter) {
        this.fetchByChapter = Objects.requireNonNull(fetchByChapter);
        setText("Classes");
        setClosable(false);
        classesSelection = new NamedSelection<>("class", null, initialClasses, fetchByName);
        setContent(classesSelection);
    }

    @Override
    public void handle(ChapterSelectionEvent chapterSelectionEvent) {
        try {
            classesSelection.setCollection(fetchByChapter.apply(chapterSelectionEvent.getChapter()));
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
