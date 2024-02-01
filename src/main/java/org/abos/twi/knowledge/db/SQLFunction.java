package org.abos.twi.knowledge.db;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLFunction<S, T> {

    T apply(final S input) throws SQLException;

}
