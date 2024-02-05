package org.abos.twi.knowledge.gui.converter;

import org.abos.common.Named;
import org.abos.twi.knowledge.db.SQLFunction;

public final class NamedConverter<T extends Named> extends DbConverter<T> {

    public NamedConverter(final SQLFunction<String, T> fetcher) {
        super(fetcher);
    }

    @Override
    public String toString(final T named) {
       return named.getName();
    }
}
