package org.abos.twi.knowledge.gui.fx.component.tab;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.gui.fx.Gui;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;

import java.sql.SQLException;

public class StatsTab extends DbTab implements EventHandler<ChapterSelectionEvent> {

    // first two columns
    private final Label currentChapterLabel = new Label(Gui.UNAVAILABLE);
    private final Label wordsLabel = new Label(Gui.UNAVAILABLE);
    private final Label wordsAvgLabel = new Label(Gui.UNAVAILABLE);
    private final Label wordsSumLabel = new Label(Gui.UNAVAILABLE);

    // second two columns
    private final Label characterAppearanceLabel = new Label(Gui.UNAVAILABLE);
    private final Label characterMentionLabel = new Label(Gui.UNAVAILABLE);
    private final Label classMentionLabel = new Label(Gui.UNAVAILABLE);
    private final Label skillMentionLabel = new Label(Gui.UNAVAILABLE);

    public StatsTab(DbHelper dbHelper) {
        super(dbHelper);
        setText("Stats");
        setClosable(false);
        final GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        final int columnCount = 4;
        for (int i = 0; i < columnCount; i++) {
            final ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100d/columnCount);
            gridPane.getColumnConstraints().add(col);
        }

        // first two columns
        final Label ccLabel = new Label("Current chapter: ");
        GridPane.setHalignment(ccLabel, HPos.RIGHT);
        gridPane.add(ccLabel, 0, 0);
        gridPane.add(currentChapterLabel, 1, 0);
        final Label wicLabel = new Label("Words in Chapter: ");
        GridPane.setHalignment(wicLabel, HPos.RIGHT);
        gridPane.add(wicLabel, 0, 1);
        gridPane.add(wordsLabel, 1, 1);
        final Label waunLabel = new Label("Word average until now: ");
        GridPane.setHalignment(waunLabel, HPos.RIGHT);
        gridPane.add(waunLabel, 0, 2);
        gridPane.add(wordsAvgLabel, 1, 2);
        final Label wunLabel = new Label("Words until now: ");
        GridPane.setHalignment(wunLabel, HPos.RIGHT);
        gridPane.add(wunLabel, 0, 3);
        gridPane.add(wordsSumLabel, 1, 3);

        // second two columns
        final Label caLabel = new Label("Characters appeared: ");
        GridPane.setHalignment(caLabel, HPos.RIGHT);
        gridPane.add(caLabel, 2, 0);
        gridPane.add(characterAppearanceLabel, 3, 0);
        final Label cmLabel = new Label("Characters mentioned: ");
        GridPane.setHalignment(cmLabel, HPos.RIGHT);
        gridPane.add(cmLabel, 2, 1);
        gridPane.add(characterMentionLabel, 3, 1);
        final Label clmLabel = new Label("Classes mentioned: ");
        GridPane.setHalignment(clmLabel, HPos.RIGHT);
        gridPane.add(clmLabel, 2, 2);
        gridPane.add(classMentionLabel, 3, 2);
        final Label skmLabel = new Label("Skills mentioned: ");
        GridPane.setHalignment(skmLabel, HPos.RIGHT);
        gridPane.add(skmLabel, 2, 3);
        gridPane.add(skillMentionLabel, 3, 3);

        setContent(gridPane);
    }

    @Override
    public void handle(final ChapterSelectionEvent chapterSelectionEvent) {
        currentChapter = chapterSelectionEvent.getChapter();
        if (currentChapter == null) {
            currentChapterLabel.setText(Gui.UNAVAILABLE);
            wordsLabel.setText(Gui.UNAVAILABLE);
            wordsAvgLabel.setText(Gui.UNAVAILABLE);
            wordsSumLabel.setText(Gui.UNAVAILABLE);
            characterAppearanceLabel.setText(Gui.UNAVAILABLE);
            characterMentionLabel.setText(Gui.UNAVAILABLE);
            classMentionLabel.setText(Gui.UNAVAILABLE);
            skillMentionLabel.setText(Gui.UNAVAILABLE);
        }
        else {
            try {
                currentChapterLabel.setText(currentChapter.name());
                wordsLabel.setText(Integer.toString(currentChapter.words()));
                wordsAvgLabel.setText(Integer.toString(dbHelper.fetchWordAvg(currentChapter)));
                wordsSumLabel.setText(Integer.toString(dbHelper.fetchWordCount(currentChapter)));
                characterAppearanceLabel.setText(Integer.toString(dbHelper.fetchCharacterAppearanceCount(currentChapter)));
                characterMentionLabel.setText(Integer.toString(dbHelper.fetchCharacterMentionCount(currentChapter)));
                classMentionLabel.setText(Integer.toString(dbHelper.fetchClassMentionCount(currentChapter)));
                skillMentionLabel.setText(Integer.toString(dbHelper.fetchSkillMentionCount(currentChapter)));
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        }
    }

}
