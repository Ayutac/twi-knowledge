package org.abos.twi.knowledge.gui.fx.component.tab;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.gui.fx.component.LabelledList;
import org.abos.twi.knowledge.gui.fx.component.NamedSelection;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;

import java.sql.SQLException;
import java.util.List;

public final class ClassesTab extends DbTab implements EventHandler<ChapterSelectionEvent> {

    private final NamedSelection<Class> classesSelection;

    public ClassesTab(final List<Class> initialClasses, final DbHelper dbHelper) {
        super(dbHelper);
        setText("Classes");
        setClosable(false);
        final BorderPane borderPane = new BorderPane();
        classesSelection = new NamedSelection<>("class", null, initialClasses, dbHelper::fetchClass);
        borderPane.setTop(classesSelection);
        final ListView<String> characters = new ListView<>();
        final ListView<String> skills = new ListView<>();
        final ListView<String> mentions = new ListView<>();
        final HBox main1 = new HBox(new LabelledList("Characters", characters), new LabelledList("Skills", skills), new LabelledList("mentioned in", mentions));
        final ListView<String> bases = new ListView<>();
        final ListView<String> upgrades = new ListView<>();
        final HBox main2 = new HBox(new LabelledList("comes from", bases), new LabelledList("can become", upgrades));
        borderPane.setCenter(new VBox(main1, main2));
        setContent(borderPane);
    }

    @Override
    public void handle(ChapterSelectionEvent chapterSelectionEvent) {
        try {
            classesSelection.setCollection(dbHelper.fetchClasses(chapterSelectionEvent.getChapter()));
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
