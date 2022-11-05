package com.example.imbee.component;

import com.example.imbee.config.NotificationConfig;
import com.example.imbee.dto.DoneMessageReq;
import com.example.imbee.dto.FcmMessageReq;
import com.example.imbee.dto.Note;
import com.example.imbee.entity.FcmJobEntity;
import com.example.imbee.service.FcmJobService;
import com.example.imbee.service.FirebaseMessagingService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class NotificationConsumer {
    private final RabbitMqSender rabbitMqSender;
    private final FcmJobService fcmJobService;

    private final FirebaseMessagingService firebaseMessagingService;

    @Autowired
    public NotificationConsumer(RabbitMqSender rabbitMqSender,
                                FcmJobService fcmJobService,
                                FirebaseMessagingService firebaseMessagingService){
        this.rabbitMqSender = rabbitMqSender;
        this.fcmJobService = fcmJobService;
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @Value(value = "${FirebaseName}")
    String firebaseName;

    @RabbitListener(queues = NotificationConfig.NOTIFICATION_FCM)
    public void fcmHandle(FcmMessageReq req, Message message, Channel channel) throws IOException {
        log.info("Receiver : " + req);
        Date now = new Date();
        try {
            // send FCM
            firebaseMessagingService.sendNotification(
                    Note.builder()
                            .title("Incoming message")
                            .body(req.getText())
                            .build(),
                    firebaseName);
            // Notify the result
                // insert data
            fcmJobService.addFcmJob(
                    FcmJobEntity.builder()
                            .identifier(req.getIdentifier())
                            .deliverAt(now.toString())
                            .build()
            );
                // send message
            rabbitMqSender.sendDoneMsg(
                    DoneMessageReq.builder()
                            .identifier(req.getIdentifier())
                            .deliverAt(now.toString())
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
