package com.styleguide;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitDispatch {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitDispatch(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void dispatchTest() {
        rabbitTemplate.convertAndSend("hello", "yuh");
    }

}
