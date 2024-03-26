package com.redis.springbootredis.service;

import com.redis.springbootredis.entity.UserEntity;
import com.redis.springbootredis.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    boolean saveUser(User user);

    List<UserEntity> fetchAllUser();

    User fetchUserById(Long id);

    boolean deleteUser(Long id);

    Optional<UserEntity> getUserById(Long id);
}
