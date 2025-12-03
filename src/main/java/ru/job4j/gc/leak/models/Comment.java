package ru.job4j.gc.leak.models;

import java.util.Objects;

public class Comment {
    public Comment (String text, User user) {
        this.text = text;
        this.user = user;
    }
    private final String text;

    private final User user;

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(text, comment.text) && Objects.equals(user, comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, user);
    }
}
