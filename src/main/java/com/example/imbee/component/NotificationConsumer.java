package com.example.imbee.component;

import com.example.imbee.config.NotificationConfig;
import com.example.imbee.dto.DoneMessageReq;
import com.example.imbee.dto.FcmMessageReq;
import com.example.imbee.entity.FcmJobEntity;
import com.example.imbee.service.FcmJobService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class NotificationConsumer {
    private final RabbitMqSender rabbitMqSender;
    private final FcmJobService fcmJobService;

    @Autowired
    public NotificationConsumer(RabbitMqSender rabbitMqSender,
                                FcmJobService fcmJobService){
        this.rabbitMqSender = rabbitMqSender;
        this.fcmJobService = fcmJobService;
    }

    @RabbitListener(queues = "notification.fcm")
    public void fcmHandle(FcmMessageReq req, Message message, Channel channel) throws IOException {
        log.info("Receiver : " + req);
        try {
            // send FCM
            // Notify the result
                // insert data
            fcmJobService.addFcmJob(
                    FcmJobEntity.builder()
                            .identifier("fcm-msg-a1beff5ac")
                            .deliverAt("2021-01-31T12:34:56Z")
                            .build()
            );
                // send message
            rabbitMqSender.sendDoneMsg(
                    DoneMessageReq.builder()
                            .identifier("fcm-msg-a1beff5ac")
                            .deliverAt("2021-01-31T12:34:56Z")
                            .build(),
                    NotificationConfig.EXCHANGE,
                    NotificationConfig.NOTIFICATION_DONE
            );
        }catch (Exception e){
            log.error("error:", e);
        }
    }

//    @RabbitListener(queues = "notification.done")
//    public void doneHandle(DoneMessageReq req, Message message, Channel channel) throws IOException {
//        log.info("Receiver done : " + req);
//    }
}
