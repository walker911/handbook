package com.walker.manual.twoot.database;

import java.sql.SQLException;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/13
 */
public interface With<P> {
    void run(P stmt) throws SQLException;
}
