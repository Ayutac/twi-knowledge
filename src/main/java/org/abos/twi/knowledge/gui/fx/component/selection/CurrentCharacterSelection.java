package org.abos.twi.knowledge.gui.fx.component.selection;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.abos.twi.knowledge.gui.CharacterNamed;
import org.abos.twi.knowledge.gui.fx.Gui;
import org.abos.twi.knowledge.gui.fx.dialog.CharacterSelector;
import org.abos.twi.knowledge.gui.fx.event.CharacterSelectionEvent;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public final class CurrentCharacterSelection extends Pane {

    private CharacterNamed selected;

    private final Label label = new Label(Gui.UNAVAILABLE);

    private final CharacterSelector selector;


    public CurrentCharacterSelection(List<CharacterNamed> selection) {
        selector = new CharacterSelector(selection);
        final Button button = new Button("Select");
        button.setOnAction(event -> forceSelection());
        final HBox hbox = new HBox(label, button);
        hbox.setSpacing(10d);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));
        getChildren().add(hbox);
    }

    public void forceSelection() {
        Optional<CharacterNamed> newSelected = Optional.empty();
        while (newSelected.isEmpty()) {
            newSelected = selector.showAndWait();
        }
        setSelected(newSelected.get());
    }

    public CharacterNamed getSelected() {
        return selected;
    }

    public void setSelected(final CharacterNamed selected) {
        this.selected = selected;
        if (selected == null) {
            label.setText(Gui.UNAVAILABLE);
        }
        else {
            label.setText(selected.getName());
        }
        this.fireEvent(new CharacterSelectionEvent(selected));
    }

    public void setCollection(Collection<CharacterNamed> collection) {
        if (!collection.contains(getSelected())) {
            setSelected(null);
        }
        else {
            // triggers the character update
            setSelected(getSelected());
        }
        selector.setCollection(collection);
    }
}
