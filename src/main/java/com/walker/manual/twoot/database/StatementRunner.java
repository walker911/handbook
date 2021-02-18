package com.walker.manual.twoot.database;

import java.sql.*;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/13
 */
public class StatementRunner {

    private final Connection conn;

    public StatementRunner(final Connection conn) {
        this.conn = conn;
    }

    <R> R extract(final String sql, final Extractor<R> extractor) {
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.clearParameters();
            return extractor.run(stmt);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    void withStatement(final String sql, final With<PreparedStatement> withPreparedStatement) {
        extract(sql, stmt -> {
            withPreparedStatement.run(stmt);
            return null;
        });
    }

    void update(final String sql) {
        withStatement(sql, PreparedStatement::execute);
    }

    void query(final String sql, final With<ResultSet> withPreparedStatement) {
        withStatement(sql, stmt -> {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                withPreparedStatement.run(resultSet);
            }
        });
    }
}
