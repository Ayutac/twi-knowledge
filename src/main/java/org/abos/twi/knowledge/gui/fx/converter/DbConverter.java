package org.abos.twi.knowledge.gui.fx.converter;

import javafx.util.StringConverter;
import org.abos.twi.knowledge.db.SQLFunction;

import java.sql.SQLException;
import java.util.Objects;

public abstract class DbConverter<T> extends StringConverter<T> {

    private final SQLFunction<String, T> fetcher;

    protected DbConverter(final SQLFunction<String, T> fetcher) {
        this.fetcher = Objects.requireNonNull(fetcher);
    }

    @Override
    public T fromString(String s) {
        try {
            return fetcher.apply(s);
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
