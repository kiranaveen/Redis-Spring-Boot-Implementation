package com.redis.springbootredis.repository;

import com.redis.springbootredis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao
{
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "USER";

    @Override
    public boolean saveUser(User user) {
        try {
            // Retrieve the HashOperations for the RedisTemplate with appropriate types.
            HashOperations<String, String, User> hashOperations = redisTemplate.opsForHash();

// Assuming user is an instance of User and KEY is a String representing the key.
            hashOperations.put(KEY, user.getId().toString(), user);

//            redisTemplate.opsForHash().put(KEY, user.getId().toString(), user);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public List<User> fetchAllUser() {
        List<User> users;
        users = redisTemplate.opsForHash().values(KEY);
        return users;
    }

    @Override
    public User fetchUserById(Long id) {
        User user;
        user = (User) redisTemplate.opsForHash().get(KEY,id.toString());
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        try{
            redisTemplate.opsForHash().delete(KEY,id.toString());
            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
