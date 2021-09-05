package com.example.aws.sqs.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.example.aws.sqs.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SqsSender {

    private QueueMessagingTemplate queueMessagingTemplate;

    public SqsSender(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    public void send(User user) {
        System.out.println("Sending user to SQS..." + user.getName());
        queueMessagingTemplate.convertAndSend("ec2-s3-sqs-redis-queue", user);
    }
}
