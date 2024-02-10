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
import org.abos.twi.knowledge.gui.fx.component.selection.CurrentSkillSelection;
import org.abos.twi.knowledge.gui.fx.component.LabelledList;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;
import org.abos.twi.knowledge.gui.fx.event.SkillSelectionEvent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SkillsTab extends DbTab implements EventHandler<ChapterSelectionEvent> {

    private final List<Character> characters = new ArrayList<>();

    private final CurrentSkillSelection skillsSelection;
    private final LabelledList charactersList;
    private final LabelledList classesList;
    private final LabelledList mentionsList;
    private final LabelledList basesList;
    private final LabelledList upgradesList;

    public SkillsTab(final List<Skill> initialSkills, final DbHelper dbHelper) {
        super(dbHelper);
        setText("Skills");
        setClosable(false);
        final BorderPane borderPane = new BorderPane();
        skillsSelection = new CurrentSkillSelection(initialSkills, dbHelper::fetchSkill);
        borderPane.setTop(skillsSelection);
        charactersList = new LabelledList("Characters");
        classesList = new LabelledList("Classes");
        mentionsList = new LabelledList("mentioned in");
        final HBox main1 = new HBox(charactersList, classesList, mentionsList);
        basesList = new LabelledList("comes from");
        upgradesList = new LabelledList("can become");
        skillsSelection.addEventHandler(SkillSelectionEvent.TYPE, this::updateListViews);
        final HBox main2 = new HBox(basesList, upgradesList);
        borderPane.setCenter(new VBox(main1, main2));
        setContent(borderPane);
    }

    @Override
    public void handle(ChapterSelectionEvent chapterSelectionEvent) {
        currentChapter = chapterSelectionEvent.getChapter();
        try {
            skillsSelection.setCollection(dbHelper.fetchSkills(currentChapter));
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private void updateListViews(final SkillSelectionEvent skillSelectionEvent) {
        final Skill newSkill = skillSelectionEvent.getSkill();
        characters.clear();
        if (newSkill == null) {
            final ObservableList<String> emptyList = FXCollections.emptyObservableList();
            charactersList.getListView().setItems(emptyList);
            classesList.getListView().setItems(emptyList);
            mentionsList.getListView().setItems(emptyList);
            basesList.getListView().setItems(emptyList);
            upgradesList.getListView().setItems(emptyList);
            return;
        }
        try {
            // TODO add characters that have this class
            classesList.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchClassSkillsBySkill(newSkill, currentChapter).stream()
                            .map(Class::name)
                            .toList()
            ));
            mentionsList.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchSkillMentions(newSkill, currentChapter).stream()
                            .map(Chapter::name)
                            .toList()
            ));
            basesList.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchSkillUpgradesByUpgrade(newSkill, currentChapter).stream()
                            .map(Skill::name)
                            .toList()
            ));
            upgradesList.getListView().setItems(FXCollections.observableList(
                    dbHelper.fetchSkillUpgradesByBase(newSkill, currentChapter).stream()
                            .map(Skill::name)
                            .toList()
            ));
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
