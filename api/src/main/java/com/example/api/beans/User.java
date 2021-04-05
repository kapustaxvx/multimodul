package com.example.api.beans;

import java.time.Instant;
import java.util.Objects;

public class User {

     Long userId;
     String name;
     Instant birthDate;

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthDate, user.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, birthDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
