package org.abos.twi.knowledge.gui.fx.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.SQLFunction;
import org.abos.twi.knowledge.gui.fx.dialog.ChapterSelector;

import java.util.List;
import java.util.Optional;

public final class ChapterSelection extends Pane {

    private static final String NO_CHAPTER = "-unavailable-";

    private Chapter chapter;

    private final Label label = new Label(NO_CHAPTER);

    private final ChapterSelector chapterSelector;

    public ChapterSelection(final List<Chapter> chapters, final SQLFunction<String, Chapter> fetcher) {
        chapterSelector = new ChapterSelector(chapters, fetcher);
        final Button button = new Button("Select");
        button.setOnAction(event -> forceChapterSelection());
        final HBox hbox = new HBox(label, button);
        hbox.setSpacing(10d);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));
        getChildren().add(hbox);
    }

    public void forceChapterSelection() {
        Optional<Chapter> selectedChapter = Optional.empty();
        while (selectedChapter.isEmpty()) {
            selectedChapter = chapterSelector.showAndWait();
        }
        setChapter(selectedChapter.get());
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
        if (chapter == null) {
            label.setText(NO_CHAPTER);
        }
        else {
            label.setText(chapter.name());
        }
    }

}
