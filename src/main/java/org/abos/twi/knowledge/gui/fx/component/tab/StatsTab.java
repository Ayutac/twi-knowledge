package org.abos.twi.knowledge.gui.fx.component.tab;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.gui.fx.Gui;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;

import java.sql.SQLException;

public class StatsTab extends DbTab implements EventHandler<ChapterSelectionEvent> {

    private final Label currentChapterLabel = new Label(Gui.UNAVAILABLE);
    private final Label wordsLabel = new Label(Gui.UNAVAILABLE);
    private final Label wordsAvgLabel = new Label(Gui.UNAVAILABLE);
    private final Label wordsSumLabel = new Label(Gui.UNAVAILABLE);

    public StatsTab(DbHelper dbHelper) {
        super(dbHelper);
        setText("Stats");
        setClosable(false);
        final GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
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
        }
        else {
            try {
                currentChapterLabel.setText(currentChapter.name());
                wordsLabel.setText(Integer.toString(currentChapter.words()));
                wordsAvgLabel.setText(Integer.toString(dbHelper.fetchWordAvg(currentChapter)));
                wordsSumLabel.setText(Integer.toString(dbHelper.fetchWordCount(currentChapter)));
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        }
    }

}
