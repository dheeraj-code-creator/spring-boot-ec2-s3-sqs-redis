package com.example.aws.sqs.service;


import com.example.aws.sqs.entity.User;
import com.example.aws.sqs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisService {

  @Autowired
  UserRepository userRepository;

  public Map<Integer, User> findAll() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    return userRepository.findById(id);
  }

  public void save(User user) {
    userRepository.save(user);
  }
}