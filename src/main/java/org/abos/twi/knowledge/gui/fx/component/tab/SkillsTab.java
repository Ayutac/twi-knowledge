package org.abos.twi.knowledge.gui.fx.component.tab;

import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import org.abos.twi.knowledge.core.Skill;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.SQLFunction;
import org.abos.twi.knowledge.gui.fx.component.NamedSelection;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class SkillsTab extends Tab implements EventHandler<ChapterSelectionEvent> {

    private final SQLFunction<Chapter, Collection<Skill>> fetchByChapter;
    private final NamedSelection<Skill> skillsSelection;

    public SkillsTab(final List<Skill> initialSkills, SQLFunction<String, Skill> fetchByName, SQLFunction<Chapter, Collection<Skill>> fetchByChapter) {
        this.fetchByChapter = Objects.requireNonNull(fetchByChapter);
        setText("Skills");
        setClosable(false);
        skillsSelection = new NamedSelection<>("skill", null, initialSkills, fetchByName);
        setContent(skillsSelection);
    }

    @Override
    public void handle(ChapterSelectionEvent chapterSelectionEvent) {
        try {
            skillsSelection.setCollection(fetchByChapter.apply(chapterSelectionEvent.getChapter()));
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
