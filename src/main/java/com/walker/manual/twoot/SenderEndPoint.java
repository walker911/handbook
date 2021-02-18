package com.walker.manual.twoot;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/9
 */
public class SenderEndPoint {

    private final User user;
    private final Twootr twootr;

    public SenderEndPoint(User user, Twootr twootr) {
        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(twootr, "twootr");

        this.user = user;
        this.twootr = twootr;
    }

    public FollowStatus onFollow(final String userIdToFollow) {
        Objects.requireNonNull(userIdToFollow, "userIdToFollow");

        return twootr.onFollow(user, userIdToFollow);
    }

    public Position onSendTwoot(final String id, final String content) {
        Objects.requireNonNull(content, "content");

        return twootr.onSendTwoot(id, user, content);
    }

    public DeleteStatus onDeleteTwoot(final String id) {
        return twootr.onDeleteTwoot(user.getId(), id);
    }

    public void onLogoff() {
        user.onLogoff();
    }
}
