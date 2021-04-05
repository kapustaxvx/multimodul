package com.example.api.beans;

import com.google.common.base.Preconditions;

import java.time.Instant;

public class UserBuilder {
    private final User user = new User();

    public static UserBuilder create(){
        return new UserBuilder();
    }

    public UserBuilder withUserId(Long userId){
        user.userId = userId;
        return this;
    }

    public UserBuilder withName(String name){
        user.name = name;
        return this;
    }

    public UserBuilder withBirthDate(Instant birthDate){
        user.birthDate = birthDate;
        return this;
    }

    public User build(){
        Preconditions.checkArgument(user.userId != null);
        Preconditions.checkArgument(user.name != null);
        Preconditions.checkArgument(user.birthDate != null);
        return user;
    }
}
