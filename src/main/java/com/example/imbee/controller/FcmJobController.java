package com.example.imbee.controller;

import com.example.imbee.component.RabbitMqSender;
import com.example.imbee.dto.FcmMessageReq;
import com.example.imbee.dto.NotificationReq;
import com.example.imbee.entity.FcmJobEntity;
import com.example.imbee.pojo.ResponseObj;
import com.example.imbee.service.FcmJobService;
import com.example.imbee.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "device")
public class FcmJobController extends BaseController{
    private final FcmJobService deviceService;
    private final NotificationService notificationService;
    private final RabbitMqSender rabbitMqSender;

    @Autowired
    public FcmJobController(FcmJobService deviceService,
                            NotificationService notificationService,
                            RabbitMqSender rabbitMqSender){
        this.deviceService = deviceService;
        this.notificationService = notificationService;
        this.rabbitMqSender = rabbitMqSender;
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

    @PostMapping("/topic")
    public String sendPnsToTopic(@RequestBody NotificationReq notificationRequestDto) {
        return notificationService.sendPnsToDevice(notificationRequestDto);
    }
//    @ApiOperation("add device")
//    @GetMapping(value = "aaa", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void aaa(){
//        try{
//            HttpURLConnection httpUrlConn = null;
//            HttpReqExcuteResult result = new HttpReqExcuteResult();
//            try {
//
//                httpUrlConn = sendRequest(urlString, inputString, method);
//
////            logger.info("{} >>> {}", urlString, httpUrlConn.getResponseCode());
//
//                result.setExcuteResultCode(httpUrlConn.getResponseCode());
//                result.setBody(getResponse(httpUrlConn));
//
//                System.out.println(result);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                //斷開連線
//                httpUrlConn.disconnect();
//            }
////            return super.sendSuccessRsp();
//        } catch (Exception e){
//            log.error("device/addFcmJob: " + e.getMessage());
//            return super.sendFailRsp(e);
//        }
//    }
//    private HttpURLConnection sendRequest(String urlString, String parameter, String method, String authorization) throws IOException {
//        URL url = new URL(urlString);
//
//        HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
//        httpUrlConn.setRequestMethod(method);
//        httpUrlConn.setRequestProperty("Content-Type", "application/json; uf-8");
//        httpUrlConn.setRequestProperty("Accept", "application/json");
//        authorization = "Bearer " + authorization;
//        httpUrlConn.setRequestProperty("Authorization", authorization);
//        httpUrlConn.setDoInput(true);
//        httpUrlConn.setDoOutput(true);//TODO settimeout
//
//        if(!parameter.isEmpty()) {
//            OutputStream os = httpUrlConn.getOutputStream();
//            byte[] input = parameter.getBytes(StandardCharsets.UTF_8);
//            os.write(input, 0, input.length);
//        }
//
//        return httpUrlConn;
//    }
//    private String getResponse(HttpURLConnection httpUrlConn) throws IOException {
//        StringBuilder buffer = new StringBuilder();
//        InputStream inputStream;
//
//        //獲得輸入
//        try {
//            inputStream = httpUrlConn.getInputStream();
//        } catch (Exception e) {
//            inputStream = httpUrlConn.getErrorStream();
//        }
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//        //將bufferReader的值給放到buffer裡
//        String str = null;
//        while ((str = bufferedReader.readLine()) != null) {
//            buffer.append(str);
//        }
//        //關閉bufferReader和輸入流
//        bufferedReader.close();
//        inputStreamReader.close();
//        inputStream.close();
//
//        return buffer.toString();
//    }

    @ApiOperation("test mq")
    @GetMapping(value = "/send")
    public String send(FcmMessageReq req){
        rabbitMqSender.sendFcmMsg(req, "notification.exchange", "notification.fcm");
        return "message send";
    }
}
