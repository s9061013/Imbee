package com.example.imbee.service;

import com.example.imbee.dto.MessageReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Transactional
@Service
public class RabbitMqSenderService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMsg(MessageReq massageReq){
        log.info("當前時間：{},收到请求，msg:{},請求類型:{},delayTime:{},", new Date(), massageReq,massageReq.getType(), 0 );
        this.sendDelayMsg(massageReq ,"test.topic", "test.c1", 0 ); // DELAYED_EXCHANGE_NAME // DELAYED_ROUTING_KEY_NFT_SHELF_BID
    }

    public void sendDelayMsg(MessageReq massageReq, String exchangName, String routingKey, long delayTime ){
        log.info("當前時間：{},收到请求，msg:{},請求類型:{},delayTime:{},", new Date(), massageReq,massageReq.getType(), delayTime);
        rabbitTemplate.convertAndSend(exchangName, routingKey, massageReq);
    }
}
