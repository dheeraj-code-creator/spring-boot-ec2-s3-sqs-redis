package com.example.aws.sqs.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    Logger logger= LoggerFactory.getLogger(MessageReceiver.class);

    // provide urs Queue url or queue name
    @SqsListener("https://sqs.us-east-1.amazonaws.com/365609404781/ec2-s3-sqs-redis-queue")
    public void loadMessageFromSQS(String message)  {
        logger.info("message from SQS Queue {}",message);
    }
}
