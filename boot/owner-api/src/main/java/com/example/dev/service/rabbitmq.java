package com.example.dev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class rabbitmq {

    @RabbitListener(queues = "order")
    public void consume(Message message){
        log.info("consumer consumes message: {}",message);
    }
}
