package com.redis.springbootredis.service;

import com.redis.springbootredis.entity.UserEntity;
import com.redis.springbootredis.model.User;
import com.redis.springbootredis.repository.UserDao;
import com.redis.springbootredis.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean saveUser(User user) {
        UserEntity userEntity = new UserEntity().builder()
                .firstName(user.getFirstName())
                .age(user.getAge())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .build();
        userRepository.save(userEntity);
        Query query = entityManager.createNamedQuery("UserEntity.findByFirstNameLikeIgnoreCase");
        query.setParameter("firstName","kira");
        query.getResultList();
        query.getResultList();
        String a = null;
        log.info(a,query.getResultList());
        return userDao.saveUser(user);
    }

//    @Override
//    @Cacheable(value = "users", key = "'allUsers'")
//    public List<UserEntity> fetchAllUser() {
//
//
//        return userRepository.findAll();
//    }

    @Override
        @Cacheable(value = "users", key = "'allUsers_' + #root.methodName")
    public List<UserEntity> fetchAllUser() {
        return userRepository.findAll();
    }


    @Override
    public User fetchUserById(Long id) {
        return userDao.fetchUserById(id);
    }

    @Override
    public boolean deleteUser(Long id) {

        return userDao.deleteUser(id);
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
