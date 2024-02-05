package org.abos.twi.knowledge.gui.fx.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class LabelledList extends VBox {

    public LabelledList(String header, ListView<String> listView) {
        super(new Label(header), listView);
        setSpacing(10d);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5));
    }

}
