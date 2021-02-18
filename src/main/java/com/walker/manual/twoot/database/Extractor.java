package com.walker.manual.twoot.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/13
 */
public interface Extractor<R> {
    R run(PreparedStatement statement) throws SQLException;
}
