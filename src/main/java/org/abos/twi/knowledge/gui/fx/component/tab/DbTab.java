package org.abos.twi.knowledge.gui.fx.component.tab;

import javafx.scene.control.Tab;
import org.abos.twi.knowledge.db.DbHelper;

import java.util.Objects;

public abstract class DbTab extends Tab {

    protected final DbHelper dbHelper;

    protected DbTab(final DbHelper dbHelper) {
        this.dbHelper = Objects.requireNonNull(dbHelper);
    }

}
