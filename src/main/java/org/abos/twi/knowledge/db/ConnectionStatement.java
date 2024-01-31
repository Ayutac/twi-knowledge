package org.abos.twi.knowledge.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public record ConnectionStatement(Connection connection, PreparedStatement preparedStatement) implements AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionStatement.class);

    @Override
    public void close() {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.warn("Prepared statement couldn't be closed!");
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn("Connection couldn't be closed!");
            }
        }
    }
}
