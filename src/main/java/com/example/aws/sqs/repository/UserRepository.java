package com.example.aws.sqs.repository;

import com.example.aws.sqs.entity.User;

import java.util.Map;

public interface UserRepository {

    void save(User user);
    Map<Integer, User> findAll();
    User findById(Integer id);
    void update(User user);
    void delete(Integer id);
}
