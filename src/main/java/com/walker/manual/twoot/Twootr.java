package com.walker.manual.twoot;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/10
 */
public class Twootr {

    private final UserRepository userRepository;
    private final TwootRepository twootRepository;

    public Twootr(UserRepository userRepository, TwootRepository twootRepository) {
        this.userRepository = userRepository;
        this.twootRepository = twootRepository;
    }

    FollowStatus onFollow(final User follow, final String userIdToFollow) {
        return userRepository.get(userIdToFollow).map(userToFollow -> userRepository.follow(follow, userToFollow)).orElse(FollowStatus.INVALID_USER);
    }

    public RegistrationStatus onRegisterUser(final String userId, final String password) {
        byte[] salt = KeyGenerator.newSalt();
        byte[] hashedPassword = KeyGenerator.hash(password, salt);
        User user = new User(userId, hashedPassword, salt, Position.INITIAL_POSITION);
        return userRepository.add(user) ? RegistrationStatus.SUCCESS : RegistrationStatus.DUPLICATE;
    }

    public Optional<SenderEndPoint> onLogon(final String userId, final String password, final ReceiverEndPoint receiverEndPoint) {
        Objects.requireNonNull(userId, "userId");
        Objects.requireNonNull(password, "password");

        Optional<User> authenticatedUser = userRepository.get(userId).filter(userOfSameId -> {
            byte[] hashedPassword = KeyGenerator.hash(password, userOfSameId.getSalt());
            return Arrays.equals(userOfSameId.getPassword(), hashedPassword);
        });

        authenticatedUser.ifPresent(user -> {
            user.onLogon(receiverEndPoint);
            twootRepository.query(new TwootQuery().inUsers(user.getFollowing()).lastSeenPosition(user.getLastSeenPosition()), user::receiveTwoot);
            userRepository.update(user);
        });

        return authenticatedUser.map(user -> new SenderEndPoint(user, this));
    }

    Position onSendTwoot(final String id, final User user, final String content) {
        String userId = user.getId();
        Twoot twoot = twootRepository.add(id, userId, content);

        user.followers().filter(User::isLoggedOn).forEach(follower -> {
            follower.receiveTwoot(twoot);
            userRepository.update(follower);
        });

        return twoot.getPosition();
    }

    DeleteStatus onDeleteTwoot(final String userId, final String id) {
        return twootRepository.get(id).map(twoot -> {
            boolean canDeleteTwoot = twoot.getSenderId().equals(userId);
            if (canDeleteTwoot) {
                twootRepository.delete(twoot);
            }
            return canDeleteTwoot ? DeleteStatus.SUCCESS : DeleteStatus.NOT_YOUR_TWOOT;
        }).orElse(DeleteStatus.UNKNOWN_TWOOT);
    }
}
