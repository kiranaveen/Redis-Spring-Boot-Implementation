package com.redis.springbootredis.repository;

import com.redis.springbootredis.entity.UserEntity;
import com.redis.springbootredis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long>
{

}
