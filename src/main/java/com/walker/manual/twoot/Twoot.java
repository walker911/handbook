package com.walker.manual.twoot;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/5
 */
public class Twoot {

    private final String id;
    private final String senderId;
    private final String content;
    private final Position position;

    public Twoot(String id, String senderId, String content, Position position) {
        this.id = id;
        this.senderId = senderId;
        this.content = content;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Twoot twoot = (Twoot) o;

        return Objects.equals(id, twoot.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Twoot{" +
                "id='" + id + '\'' +
                ", senderId='" + senderId + '\'' +
                ", content='" + content + '\'' +
                ", position=" + position +
                '}';
    }

    public boolean isAfter(final Position otherPosition) {
        return position.getValue() > otherPosition.getValue();
    }
}
