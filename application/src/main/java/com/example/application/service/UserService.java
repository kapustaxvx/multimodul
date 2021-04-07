package com.example.application.service;

import com.example.api.UserClient;
import com.example.api.beans.User;
import com.example.api.beans.UserBuilder;
import com.example.application.dao.UserDAO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService implements UserClient {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User createUser(User user) {
        return defaultUser("Ilia");
    }

    @Override
    public User getUserById(Long userId) {
        return defaultUser("Gleb");
    }

    @Override
    public void deleteUser(Long userId) {
    }

    @Override
    public User updateUser(Long userId, User user) {
        return defaultUser("Alima");
    }

    @Override
    public List<User> getAllUsers() {
        final ArrayList<User> users = new ArrayList<>();
        users.add(defaultUser("Alima"));
        users.add(defaultUser("Gleb"));
        users.add(defaultUser("Ilia"));
        return users;
    }


    private User defaultUser(String name){
        return UserBuilder.create()
                .withUserId(new Random().nextLong())
                .withName(name)
                .withBirthDate(Instant.now())
                .build();
    }
}
