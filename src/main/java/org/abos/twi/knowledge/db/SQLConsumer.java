package org.abos.twi.knowledge.db;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLConsumer<T> {

    void consume(T obj) throws SQLException;

}
