package com.example.imbee.service;

import com.example.imbee.dto.Note;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    public String sendNotification(Note note, String topic) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(note.getTitle())
                .setBody(note.getBody())
                .setImage(note.getImage())
                .build();

        Message message = Message
                .builder()
                .setTopic(topic)
                .setNotification(notification)
                .build();

        return firebaseMessaging.send(message);
    }

}
