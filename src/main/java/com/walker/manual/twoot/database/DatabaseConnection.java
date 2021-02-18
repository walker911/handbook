package com.walker.manual.twoot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/13
 */
public class DatabaseConnection {
    static {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            throw new Error(e);
        }
    }

    static Connection get() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:db/mydatabase", "SA", "");
    }
}
