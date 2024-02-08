package org.abos.twi.knowledge.gui.fx.component.tab;

import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.gui.fx.component.CurrentCharacterSelection;
import org.abos.twi.knowledge.gui.fx.event.ChapterSelectionEvent;

import java.sql.SQLException;
import java.util.List;

public class CharacterTab extends DbTab implements EventHandler<ChapterSelectionEvent> {

    private final CurrentCharacterSelection characterSelection;

    public CharacterTab(DbHelper dbHelper) {
        super(dbHelper);
        setText("Characters");
        setClosable(false);
        final BorderPane borderPane = new BorderPane();
        characterSelection = new CurrentCharacterSelection(List.of());
        borderPane.setTop(characterSelection);
        setContent(borderPane);
    }

    @Override
    public void handle(final ChapterSelectionEvent chapterSelectionEvent) {
        currentChapter = chapterSelectionEvent.getChapter();
        try {
            System.out.println("Updating the characters");
            characterSelection.setCollection(dbHelper.fetchCharacters(currentChapter));
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
