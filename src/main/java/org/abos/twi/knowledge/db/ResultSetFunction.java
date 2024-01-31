package org.abos.twi.knowledge.db;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetFunction<T> {

    T apply(final ResultSet rs) throws SQLException;

}
