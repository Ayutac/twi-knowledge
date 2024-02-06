package org.abos.twi.knowledge.gui.fx.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public final class LabelledList extends VBox {

    private final ListView<String> listView;

    public LabelledList(final String header) {
        getChildren().add(new Label(header));
        listView = new ListView<>();
        getChildren().add(listView);
        setSpacing(10d);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5));
    }

    public ListView<String> getListView() {
        return listView;
    }
}
