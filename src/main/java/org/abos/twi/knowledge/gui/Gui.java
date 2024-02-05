package org.abos.twi.knowledge.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.gui.component.ChapterSelection;

import java.util.ArrayList;
import java.util.List;

public class Gui extends Application {

    private static DbHelper dbHelper;

    private final List<Chapter> chapters = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        chapters.addAll(dbHelper.fetchChapters());
        stage.setTitle("TWI Knowledge Base");
        final StackPane root = new StackPane();
        stage.setScene(new Scene(root, 640, 480));
        final ChapterSelection chapterSelection = new ChapterSelection(chapters, dbHelper::fetchChapter);
        root.getChildren().add(new VBox(chapterSelection));
        stage.show();
        chapterSelection.forceChapterSelection();
    }

    public static void main(String[] args) {
        System.setProperty(DbHelper.PROPERTY_URL, "localhost:5432/test");
        System.setProperty(DbHelper.PROPERTY_SU_NAME, "visitor");
        System.setProperty(DbHelper.PROPERTY_SU_PW, "twi");
        dbHelper = new DbHelper();
        Application.launch(args);
    }
}
