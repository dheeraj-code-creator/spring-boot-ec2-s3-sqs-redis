package com.example.aws.sqs.service;


import com.example.aws.sqs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class SqsReceiver {

  @Autowired
  RedisService redisService;

  @SqsListener("https://sqs.us-east-1.amazonaws.com/365609404781/ec2-s3-sqs-redis-queue")
  public void userCacheListener(User user) {
    System.out.println("Received Message for user..." + user.getName());
    redisService.save(user);
    System.out.println("Save Message in Cache");
  }
}
