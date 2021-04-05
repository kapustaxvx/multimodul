package com.example.application.controller;

import com.example.api.beans.User;
import com.example.application.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    User createUser(@RequestBody User user){

        return userService.createUser(user);
    }

    @PutMapping(value = "/{userId}")
    User updateUser(@PathVariable(value = "userId") Long userId, @RequestBody User user){

        return userService.updateUser(userId, user);
    }

    @DeleteMapping(value = "/{userId}")
    void deleteUser(@PathVariable(value = "userId") Long userId){

        userService.deleteUser(userId);
    }

    @GetMapping(value = "/{userId}")
    User getUserById(@PathVariable(value = "userId") Long userId){

        return userService.getUserById(userId);
    }

    @GetMapping

    List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    
}
