package com.example.application.service;

import com.example.api.UserClient;
import com.example.api.beans.User;

import java.util.List;

public class UserService implements UserClient {

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public User updateUser(Long userId, User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
