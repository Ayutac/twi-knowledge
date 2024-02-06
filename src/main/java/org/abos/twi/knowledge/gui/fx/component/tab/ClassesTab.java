package org.abos.twi.knowledge.gui.fx.component.tab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.gui.fx.component.CurrentClassSelection;
import org.abos.twi.knowledge.gui.fx.component.LabelledList;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;
import org.abos.twi.knowledge.gui.fx.event.ClassSelectionEvent;

import java.sql.SQLException;
import java.util.List;

public final class ClassesTab extends DbTab implements EventHandler<ChapterSelectionEvent> {

    private final CurrentClassSelection classesSelection;
    private final LabelledList characters;
    private final LabelledList skills;
    private final LabelledList mentions;
    private final LabelledList bases;
    private final LabelledList upgrades;

    public ClassesTab(final List<Class> initialClasses, final DbHelper dbHelper) {
        super(dbHelper);
        setText("Classes");
        setClosable(false);
        final BorderPane borderPane = new BorderPane();
        classesSelection = new CurrentClassSelection(initialClasses, dbHelper::fetchClass);
        borderPane.setTop(classesSelection);
        characters = new LabelledList("Characters");
        skills = new LabelledList("Skills");
        mentions = new LabelledList("mentioned in");
        final HBox main1 = new HBox(characters, skills, mentions);
        bases = new LabelledList("comes from");
        upgrades = new LabelledList("can become");
        classesSelection.addEventHandler(ClassSelectionEvent.TYPE, this::updateListViews);
        final HBox main2 = new HBox(bases, upgrades);
        borderPane.setCenter(new VBox(main1, main2));
        setContent(borderPane);
    }

    @Override
    public void handle(final ChapterSelectionEvent chapterSelectionEvent) {
        final Chapter chapter = chapterSelectionEvent.getChapter();
        currentChapter = chapter;
        try {
            classesSelection.setCollection(dbHelper.fetchClasses(chapter));
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private void updateListViews(final ClassSelectionEvent classSelectionEvent) {
        final Class newClass = classSelectionEvent.getClazz();
        if (newClass == null) {
            final ObservableList<String> emptyList = FXCollections.emptyObservableList();
            characters.getListView().setItems(emptyList);
            skills.getListView().setItems(emptyList);
            mentions.getListView().setItems(emptyList);
            bases.getListView().setItems(emptyList);
            upgrades.getListView().setItems(emptyList);
            return;
        }
        try {
            mentions.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchClassMentions(newClass, currentChapter).stream()
                            .map(Chapter::name)
                            .toList()
            ));
            bases.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchClassUpgradesByUpgrade(newClass, currentChapter).stream()
                            .map(Class::name)
                            .toList()
            ));
            upgrades.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchClassUpgradesByBase(newClass, currentChapter).stream()
                            .map(Class::name)
                            .toList()
            ));
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
