package org.abos.twi.knowledge.gui.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.gui.fx.component.CurrentChapterSelection;
import org.abos.twi.knowledge.gui.fx.component.tab.ClassesTab;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;

import java.util.ArrayList;
import java.util.List;

public class Gui extends Application {

    private static DbHelper dbHelper;

    private final List<Chapter> chapters = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        chapters.addAll(dbHelper.fetchChapters());
        stage.setTitle("TWI Knowledge Base");
        final BorderPane root = new BorderPane();
        stage.setScene(new Scene(root, 640, 480));
        final CurrentChapterSelection chapterSelection = new CurrentChapterSelection(chapters, dbHelper::fetchChapter);
        final TabPane tabPane = new TabPane();
        final ClassesTab classesTab = new ClassesTab(dbHelper.fetchClasses(), dbHelper::fetchClass, dbHelper::fetchClasses);
        tabPane.getTabs().add(classesTab);
        chapterSelection.addEventHandler(ChapterSelectionEvent.TYPE, classesTab);
        root.setTop(chapterSelection);
        root.setCenter(tabPane);
        stage.show();
        chapterSelection.forceSelection();
    }

    public static void main(String[] args) {
        System.setProperty(DbHelper.PROPERTY_URL, "localhost:5432/test");
        System.setProperty(DbHelper.PROPERTY_SU_NAME, "visitor");
        System.setProperty(DbHelper.PROPERTY_SU_PW, "twi");
        dbHelper = new DbHelper();
        Application.launch(args);
    }
}
