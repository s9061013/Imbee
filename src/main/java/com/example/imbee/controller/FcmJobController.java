package com.example.imbee.controller;

import com.example.imbee.entity.FcmJobEntity;
import com.example.imbee.pojo.ResponseObj;
import com.example.imbee.service.FcmJobService;
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

    @Autowired
    public FcmJobController(FcmJobService deviceService){
        this.deviceService = deviceService;
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
}
