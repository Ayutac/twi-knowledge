package org.abos.twi.knowledge.gui.fx.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.abos.common.Named;
import org.abos.twi.knowledge.db.SQLFunction;
import org.abos.twi.knowledge.gui.fx.Gui;
import org.abos.twi.knowledge.gui.fx.dialog.NamedSelector;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class NamedSelection<T extends Named> extends Pane {

    private T selected;

    private final Label label = new Label(Gui.UNAVAILABLE);

    private final NamedSelector<T> selector;

    public NamedSelection(final String name, final String description, final List<T> selection, final SQLFunction<String, T> fetcher) {
        selector = new NamedSelector<>(name, selection, fetcher);
        final Button button = new Button("Select");
        button.setOnAction(event -> forceSelection());
        final HBox hbox = description == null ? new HBox(label, button) : new HBox(new Label(description), label, button);
        hbox.setSpacing(10d);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));
        getChildren().add(hbox);
    }

    public void forceSelection() {
        Optional<T> newSelected = Optional.empty();
        while (newSelected.isEmpty()) {
            newSelected = selector.showAndWait();
        }
        setSelected(newSelected.get());
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
        if (selected == null) {
            label.setText(Gui.UNAVAILABLE);
        }
        else {
            label.setText(selected.getName());
        }
    }

    public void setCollection(Collection<T> collection) {
        if (!collection.contains(getSelected())) {
            setSelected(null);
        }
        else {
            // triggers the update
            setSelected(getSelected());
        }
        selector.setCollection(collection);
    }

}
