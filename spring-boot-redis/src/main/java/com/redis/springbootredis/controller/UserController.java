package com.redis.springbootredis.controller;

import com.redis.springbootredis.entity.UserEntity;
import com.redis.springbootredis.model.User;
import com.redis.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody User user)
    {
        boolean result = userService.saveUser(user);
        if (result)
            return ResponseEntity.ok("User Created Successfully");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/user")
    public ResponseEntity<List<UserEntity>> fetchAllUsers()
    {
        List<UserEntity> users;
        users =userService.fetchAllUser();
        return ResponseEntity.ok(users);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable("id") Long id)
    {
        User user;
        user = userService.fetchUserById(id);
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id)
    {
        boolean result = userService.deleteUser(id);
        if (result)
            return ResponseEntity.ok("User Delete Successfully");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/users_cache/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
