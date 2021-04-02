package com.example.api.beans;

import java.util.Objects;

public class User {

    private final Long userId;
    private final String name;
    private final String nickName;

    public User(Long userId, String name, String nickName) {
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(name, user.name) && Objects.equals(nickName, user.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, nickName);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
