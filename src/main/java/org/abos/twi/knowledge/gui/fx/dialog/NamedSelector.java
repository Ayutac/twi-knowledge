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
import org.abos.common.Named;
import org.abos.common.StringUtil;
import org.abos.twi.knowledge.db.SQLFunction;
import org.abos.twi.knowledge.gui.fx.converter.NamedConverter;
import org.abos.twi.knowledge.gui.fx.suggestion.NamedSuggestion;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class NamedSelector<T extends Named> extends Dialog<T> {

    private final List<T> selection = new ArrayList<>();

    private final NamedConverter<T> converter;
    private final NamedSuggestion<T> suggestionProvider;

    public NamedSelector(final String name, final List<T> selection, final SQLFunction<String, T> fetcher) {
        this.selection.addAll(selection);
        setTitle("Select " + StringUtil.toCapitalized(name));
        final Label label = new Label("Select " + StringUtil.toCapitalized(name) + ":");
        final TextField input = new TextField();
        converter = new NamedConverter<>(fetcher);
        suggestionProvider = new NamedSuggestion<>(selection);
        TextFields.bindAutoCompletion(input, suggestionProvider, converter);
        final Label errorLabel = new Label("Please select a valid " + name + "!");
        errorLabel.setStyle("-fx-font-weight:bold;-fx-font-family:sans-serif");
        errorLabel.setVisible(false);
        getDialogPane().getButtonTypes().add(ButtonType.OK);
        final Button confirmBtn = (Button)getDialogPane().lookupButton(ButtonType.OK);
        confirmBtn.setOnAction(event -> {
            T selected = converter.fromString(input.getText());
            if (!this.selection.contains(selected)) {
                selected = null;
            }
            errorLabel.setVisible(selected == null);
            NamedSelector.this.resultProperty().setValue(selected);
        });
        final HBox hbox = new HBox(label, input);
        hbox.setSpacing(10d);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));
        final VBox vbox = new VBox(hbox, errorLabel);
        getDialogPane().setContent(vbox);
        vbox.setAlignment(Pos.CENTER);
    }

    public void setCollection(Collection<T> collection) {
        this.selection.clear();
        this.selection.addAll(collection);
        suggestionProvider.setCollection(collection);
    }

}
