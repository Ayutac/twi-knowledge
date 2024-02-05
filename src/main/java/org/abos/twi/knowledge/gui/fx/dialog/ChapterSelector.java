package org.abos.twi.knowledge.gui.fx.dialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.SQLFunction;
import org.abos.twi.knowledge.gui.fx.converter.NamedConverter;
import org.abos.twi.knowledge.gui.fx.suggestion.NamedSuggestion;
import org.controlsfx.control.textfield.TextFields;

import java.util.List;

public class ChapterSelector extends Dialog<Chapter> {

    public ChapterSelector(final List<Chapter> selection, final SQLFunction<String, Chapter> fetcher) {
        setTitle("Select Chapter");
        final Label label = new Label("Select Chapter:");
        final TextField input = new javafx.scene.control.TextField();
        final NamedConverter<Chapter> converter = new NamedConverter<>(fetcher);
        TextFields.bindAutoCompletion(input, new NamedSuggestion<>(selection), converter);
        final Label errorLabel = new Label("Please select a valid chapter!");
        errorLabel.setStyle("-fx-font-weight:bold;-fx-font-family:sans-serif");
        errorLabel.setVisible(false);
        getDialogPane().getButtonTypes().add(ButtonType.OK);
        final Button confirmBtn = (Button)getDialogPane().lookupButton(ButtonType.OK);
        confirmBtn.setOnAction(event -> {
            final Chapter chapter = converter.fromString(input.getText());
            errorLabel.setVisible(chapter == null);
            ChapterSelector.this.resultProperty().setValue(chapter);
        });
        final HBox hbox = new HBox(label, input);
        hbox.setSpacing(10d);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));
        final VBox vbox = new VBox(hbox, errorLabel);
        getDialogPane().setContent(vbox);
        vbox.setAlignment(Pos.CENTER);
    }

}
