package com.walker.manual.twoot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/12
 */
public class TwootQuery {

    private Set<String> inUsers;

    private Position lastSeenPosition;

    public Set<String> getInUsers() {
        return inUsers;
    }

    public Position getLastSeenPosition() {
        return lastSeenPosition;
    }

    public TwootQuery inUsers(final Set<String> inUsers) {
        this.inUsers = inUsers;
        return this;
    }

    public TwootQuery inUsers(String... inUsers) {
        return inUsers(new HashSet<>(Arrays.asList(inUsers)));
    }

    public TwootQuery lastSeenPosition(final Position lastSeenPosition) {
        this.lastSeenPosition = lastSeenPosition;
        return this;
    }

    public boolean hasUsers() {
        return inUsers != null && !inUsers.isEmpty();
    }
}
