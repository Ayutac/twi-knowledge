package org.abos.twi.knowledge.gui.fx.dialog;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.abos.twi.knowledge.gui.CharacterNamed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharacterSelector extends Dialog<CharacterNamed> {

    private final List<CharacterNamed> selection = new ArrayList<>();
    private final ComboBox<CharacterNamed> input;

    public CharacterSelector(final List<CharacterNamed> selection) {
        this.selection.addAll(selection);
        setTitle("Select Character");
        final Label label = new Label("Select Character:");
        input = new ComboBox<>(FXCollections.observableList(this.selection));
        getDialogPane().getButtonTypes().add(ButtonType.OK);
        final Button confirmBtn = (Button)getDialogPane().lookupButton(ButtonType.OK);
        confirmBtn.setOnAction(event -> resultProperty().setValue(input.getValue()));
        final HBox hbox = new HBox(label, input);
        hbox.setSpacing(10d);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));
        getDialogPane().setContent(hbox);
    }

    public void setCollection(Collection<CharacterNamed> collection) {
        selection.clear();
        selection.addAll(collection);
        input.setItems(FXCollections.observableList(selection));
    }

}
