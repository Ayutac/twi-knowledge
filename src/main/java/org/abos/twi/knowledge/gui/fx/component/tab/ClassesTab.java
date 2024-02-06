package org.abos.twi.knowledge.gui.fx.component.tab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.core.Skill;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.gui.fx.component.CurrentClassSelection;
import org.abos.twi.knowledge.gui.fx.component.LabelledList;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;
import org.abos.twi.knowledge.gui.fx.event.ClassSelectionEvent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ClassesTab extends DbTab implements EventHandler<ChapterSelectionEvent> {

    private final List<Character> characters = new ArrayList<>();

    private final CurrentClassSelection classesSelection;
    private final LabelledList charactersList;
    private final LabelledList skillsList;
    private final LabelledList mentionsList;
    private final LabelledList basesList;
    private final LabelledList upgradesList;

    public ClassesTab(final List<Class> initialClasses, final DbHelper dbHelper) {
        super(dbHelper);
        setText("Classes");
        setClosable(false);
        final BorderPane borderPane = new BorderPane();
        classesSelection = new CurrentClassSelection(initialClasses, dbHelper::fetchClass);
        borderPane.setTop(classesSelection);
        charactersList = new LabelledList("Characters");
        skillsList = new LabelledList("Skills");
        mentionsList = new LabelledList("mentioned in");
        final HBox main1 = new HBox(charactersList, skillsList, mentionsList);
        basesList = new LabelledList("comes from");
        upgradesList = new LabelledList("can become");
        classesSelection.addEventHandler(ClassSelectionEvent.TYPE, this::updateListViews);
        final HBox main2 = new HBox(basesList, upgradesList);
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
        characters.clear();
        if (newClass == null) {
            final ObservableList<String> emptyList = FXCollections.emptyObservableList();
            charactersList.getListView().setItems(emptyList);
            skillsList.getListView().setItems(emptyList);
            mentionsList.getListView().setItems(emptyList);
            basesList.getListView().setItems(emptyList);
            upgradesList.getListView().setItems(emptyList);
            return;
        }
        try {
            // TODO add characters that have this class
            skillsList.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchClassSkillsByClass(newClass, currentChapter).stream()
                            .map(Skill::name)
                            .toList()
            ));
            mentionsList.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchClassMentions(newClass, currentChapter).stream()
                            .map(Chapter::name)
                            .toList()
            ));
            basesList.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchClassUpgradesByUpgrade(newClass, currentChapter).stream()
                            .map(Class::name)
                            .toList()
            ));
            upgradesList.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchClassUpgradesByBase(newClass, currentChapter).stream()
                            .map(Class::name)
                            .toList()
            ));
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
