package com.example.api;

import com.example.api.beans.User;

import java.util.List;

public interface UserClient {

    User createUser(User user);

    User getUserById(Long userId);

    User updateUser(Long userId, User user);

    List<User> getAllUsers();
}
