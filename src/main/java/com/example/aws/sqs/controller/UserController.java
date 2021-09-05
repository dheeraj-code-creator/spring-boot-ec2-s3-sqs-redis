package com.example.aws.sqs.controller;

import com.example.aws.sqs.entity.User;
import com.example.aws.sqs.service.RedisService;
import com.example.aws.sqs.service.SqsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    SqsSender sqsSender;

    @Autowired
    RedisService redisService;

    @GetMapping("/all")
    public Map<Integer, User> findAll() {
        return redisService.findAll();
    }

    @GetMapping
    public User getById(@PathParam("id") Integer id) {
        return redisService.getById(id);
    }

    @PostMapping
    public boolean create(@RequestBody User user) {
        System.out.println("Creating User...");
        sqsSender.send(user);
        return true;
    }

}