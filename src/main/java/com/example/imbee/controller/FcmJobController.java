package com.example.imbee.controller;

import com.example.imbee.component.RabbitMqSender;
import com.example.imbee.dto.FcmMessageReq;
import com.example.imbee.dto.Note;
import com.example.imbee.entity.FcmJobEntity;
import com.example.imbee.pojo.ResponseObj;
import com.example.imbee.service.FcmJobService;
import com.example.imbee.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "fcm")
public class FcmJobController extends BaseController{
    private final FcmJobService deviceService;
    private final RabbitMqSender rabbitMqSender;
    private final FirebaseMessagingService firebaseMessagingService;

    @Autowired
    public FcmJobController(FcmJobService deviceService,
                            RabbitMqSender rabbitMqSender,
                            FirebaseMessagingService firebaseMessagingService){
        this.deviceService = deviceService;
        this.rabbitMqSender = rabbitMqSender;
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @ApiOperation("add device")
    @PostMapping(value = "addFcmJob", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj<FcmJobEntity>> addFcmJob(@RequestBody FcmJobEntity device){
        try{
            return super.sendSuccessRsp(deviceService.addFcmJob(device));
        } catch (Exception e){
            log.error("device/addFcmJob: " + e.getMessage());
            return super.sendFailRsp(e);
        }
    }

    @ApiOperation("test mq")
    @GetMapping(value = "/send")
    public String send(FcmMessageReq req){
        rabbitMqSender.sendFcmMsg(req, "notification.exchange", "notification.fcm");
        return "message send";
    }

    @PostMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note,
                                   @RequestParam String topic) throws FirebaseMessagingException {
        return firebaseMessagingService.sendNotification(note, topic);
    }

}
