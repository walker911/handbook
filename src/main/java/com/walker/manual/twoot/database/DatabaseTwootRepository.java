package com.walker.manual.twoot.database;

import com.walker.manual.twoot.Position;
import com.walker.manual.twoot.Twoot;
import com.walker.manual.twoot.TwootQuery;
import com.walker.manual.twoot.TwootRepository;
import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/13
 */
public class DatabaseTwootRepository implements TwootRepository {

    private final StatementRunner statementRunner;
    private Position position = Position.INITIAL_POSITION;

    public DatabaseTwootRepository() {
        try {
            Connection conn = DatabaseConnection.get();
            conn.createStatement().executeUpdate("create table if not exists twoots (" +
                    "position int identity," +
                    "id varchar(36) unique not null," +
                    "senderId varchar(15) not null" +
                    "content varchar(140) not null" +
                    ")");
            statementRunner = new StatementRunner(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Twoot add(String id, String userId, String content) {
        statementRunner.withStatement("insert into twoots (id, senderId, content) values (?, ?, ?)", stmt -> {
            stmt.setString(1, id);
            stmt.setString(2, userId);
            stmt.setString(3, content);
            stmt.executeUpdate();
            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                position = new Position(rs.getInt(1));
            }
        });

        return new Twoot(id, userId, content, position);
    }

    @Override
    public Optional<Twoot> get(String id) {
        return statementRunner.extract("select * from twoots where id = ?", stmt -> {
            stmt.setString(1, id);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next() ? Optional.of(extractTwoot(resultSet)) : Optional.empty();
        });
    }

    private Twoot extractTwoot(final ResultSet rs) throws SQLException {
        Position position = new Position(rs.getInt(1));
        String id = rs.getString(2);
        String senderId = rs.getString(3);
        String content = rs.getString(4);
        return new Twoot(id, senderId, content, position);
    }

    @Override
    public void delete(Twoot twoot) {
        statementRunner.withStatement("delete from twoots where position = ?", stmt -> {
            stmt.setInt(1, position.getValue());
            stmt.executeUpdate();
        });
    }

    @Override
    public void query(TwootQuery twootQuery, Consumer<Twoot> callback) {
        if (!twootQuery.hasUsers()) {
            return;
        }

        Position lastSeenPosition = twootQuery.getLastSeenPosition();
        Set<String> inUsers = twootQuery.getInUsers();

        statementRunner.query("select * from twoots where senderId in " + usersTuple(inUsers) +
                "and position > " + lastSeenPosition.getValue(), rs -> callback.accept(extractTwoot(rs)));
    }

    private String usersTupleLoop(final Set<String> following) {
        List<String> quotedIds = new ArrayList<>();
        for (String id : following) {
            quotedIds.add("'" + id + "'");
        }
        return '(' + String.join(",", quotedIds) + ')';
    }

    private String usersTuple(final Set<String> following) {
        return following.stream().map(id -> "'" + id + "'").collect(Collectors.joining(",", "(", ")"));
    }

    @Override
    public void clear() {
        statementRunner.update("delete from twoots");
        statementRunner.update("drop schema public cascade");
    }
}
