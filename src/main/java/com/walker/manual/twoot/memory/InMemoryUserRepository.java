package com.walker.manual.twoot.memory;

import com.walker.manual.twoot.FollowStatus;
import com.walker.manual.twoot.User;
import com.walker.manual.twoot.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/15
 */
public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> userIdToUser = new HashMap<>();

    @Override
    public boolean add(User user) {
        return userIdToUser.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public Optional<User> get(String userId) {
        return Optional.ofNullable(userIdToUser.get(userId));
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void clear() {
        userIdToUser.clear();
    }

    @Override
    public FollowStatus follow(User follower, User userToFollow) {
        return userToFollow.addFollower(follower);
    }

    @Override
    public void close() throws Exception {

    }
}
