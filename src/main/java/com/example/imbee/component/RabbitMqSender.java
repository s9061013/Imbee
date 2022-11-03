package com.example.imbee.component;

import com.example.imbee.dto.DoneMessageReq;
import com.example.imbee.dto.FcmMessageReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Transactional
@Service
public class RabbitMqSender {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendFcmMsg(FcmMessageReq req, String exchangName, String routingKey){
        log.info("Sender : {}，{}", new Date(), req);
        rabbitTemplate.convertAndSend(exchangName, routingKey, req);
    }

    public void sendDoneMsg(DoneMessageReq req, String exchangName, String routingKey){
        log.info("Sender : {}，{}", new Date(), req);
        rabbitTemplate.convertAndSend(exchangName, routingKey, req);
    }

}
