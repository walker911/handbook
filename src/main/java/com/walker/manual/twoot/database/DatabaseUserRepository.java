package com.walker.manual.twoot.database;

import com.walker.manual.twoot.FollowStatus;
import com.walker.manual.twoot.Position;
import com.walker.manual.twoot.User;
import com.walker.manual.twoot.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/13
 */
public class DatabaseUserRepository implements UserRepository {

    private static final int ID = 1;
    private static final int PASSWORD = 2;
    private static final int SALT = 3;
    private static final int POSITION = 4;

    private static final int FOLLOWER = 1;
    private static final int USER_TO_FOLLOW = 2;

    private final Connection conn;
    private final StatementRunner statementRunner;

    private final Map<String, User> userIdToUser;

    public DatabaseUserRepository() {
        try {
            conn = DatabaseConnection.get();
            createTables();
            statementRunner = new StatementRunner(conn);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        userIdToUser = loadFromDatabase();
    }

    private void createTables() throws SQLException {
        conn.createStatement().executeUpdate("create table if not exists users(" +
                "id varchar(15) not null," +
                "password varbinary(20) not null," +
                "salt varbinary(16) not null," +
                "position int not null" +
                ")");
        conn.createStatement().executeUpdate("create table if not exists followers(" +
                "follower varchar(15) not null," +
                "userToFollow varchar(15) not null" +
                ")");
    }

    private Map<String, User> loadFromDatabase() {
        Map<String, User> users = new HashMap<>();
        statementRunner.query("select id, password, salt, position from users", resultSet -> {
            String id = resultSet.getString(ID);
            byte[] password = resultSet.getBytes(PASSWORD);
            byte[] salt = resultSet.getBytes(SALT);
            Position position = new Position(resultSet.getInt(POSITION));
            User user = new User(id, password, salt, position);
            users.put(id, user);
        });

        statementRunner.query("select follower, userToFollow from followers", resultSet -> {
            String followerId = resultSet.getString(FOLLOWER);
            String userToFollowerId = resultSet.getString(USER_TO_FOLLOW);
            User follower = users.get(followerId);
            User userToFollow = users.get(userToFollowerId);
            userToFollow.addFollower(follower);
        });

        return users;
    }

    @Override
    public boolean add(User user) {
        final String userId = user.getId();

        boolean success = userIdToUser.putIfAbsent(userId, user) == null;

        if (success) {
            statementRunner.withStatement("insert into users (id, password, salt, position) values (?, ?, ?, ?)", stmt -> {
                stmt.setString(ID, userId);
                stmt.setBytes(PASSWORD, user.getPassword());
                stmt.setBytes(SALT, user.getSalt());
                stmt.setInt(POSITION, user.getLastSeenPosition().getValue());
                stmt.executeUpdate();
            });
        }

        return success;
    }

    @Override
    public Optional<User> get(String userId) {
        return Optional.ofNullable(userIdToUser.get(userId));
    }

    @Override
    public void update(User user) {
        statementRunner.withStatement("update users set position = ? where id = ?", stmt -> {
            stmt.setInt(1, user.getLastSeenPosition().getValue());
            stmt.setString(2, user.getId());
            stmt.executeUpdate();
        });
    }

    @Override
    public void clear() {
        statementRunner.update("delete from users");
        statementRunner.update("delete from followers");
        userIdToUser.clear();
    }

    @Override
    public FollowStatus follow(User follower, User userToFollow) {
        FollowStatus followStatus = userToFollow.addFollower(follower);
        if (followStatus == FollowStatus.SUCCESS) {
            statementRunner.withStatement("insert into followers (follower, userToFollow) values (?, ?)", stmt -> {
                stmt.setString(FOLLOWER, follower.getId());
                stmt.setString(USER_TO_FOLLOW, userToFollow.getId());
                stmt.executeUpdate();
            });
        }

        return followStatus;
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }
}
