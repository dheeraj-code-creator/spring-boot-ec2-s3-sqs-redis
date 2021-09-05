package com.example.aws.sqs.repository;


import com.example.aws.sqs.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private RedisTemplate<String, User> redisTemplate;
  private HashOperations hashOperations;

  public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate){
    this.redisTemplate = redisTemplate;
    this.hashOperations = redisTemplate.opsForHash;
  }

  @Override
  public Map<Integer, User> findAll() {
    return hashOperations.entries("first-redis-cache-for-ec2");
  }

  @Override
  public User findById(Integer id) {
    return hashOperations.get("first-redis-cache-for-ec2", id);
  }

  @Override
  public void save(User user) {
    hashOperations.put("first-redis-cache-for-ec2", user.getId(), user);
  }

  @Override
  public void update(User user) {
    save(user);
  }

  @Override
  public void delete(Integer id) {
    hashOperations.delete("first-redis-cache-for-ec2", id);
  }
}