package org.abos.twi.knowledge.gui.fx.component.tab;

import javafx.event.EventHandler;
import org.abos.twi.knowledge.core.Skill;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.gui.fx.component.NamedSelection;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;

import java.sql.SQLException;
import java.util.List;

public final class SkillsTab extends DbTab implements EventHandler<ChapterSelectionEvent> {

    private final NamedSelection<Skill> skillsSelection;

    public SkillsTab(final List<Skill> initialSkills, final DbHelper dbHelper) {
        super(dbHelper);
        setText("Skills");
        setClosable(false);
        skillsSelection = new NamedSelection<>("skill", null, initialSkills, dbHelper::fetchSkill);
        setContent(skillsSelection);
    }

    @Override
    public void handle(ChapterSelectionEvent chapterSelectionEvent) {
        try {
            skillsSelection.setCollection(dbHelper.fetchSkills(chapterSelectionEvent.getChapter()));
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
