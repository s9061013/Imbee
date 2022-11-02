package com.example.imbee.controller;

import com.example.imbee.entity.DeviceEntity;
import com.example.imbee.pojo.ResponseObj;
import com.example.imbee.service.DeviceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "device")
public class DeviceController extends BaseController{
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @ApiOperation("add device")
    @PostMapping(value = "addDevice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj<DeviceEntity>> addDevice(@RequestBody DeviceEntity device){
        try{
            return super.sendSuccessRsp(deviceService.addDevice(device));
        } catch (Exception e){
            log.error("device/addDevice: " + e.getMessage());
            return super.sendFailRsp(e);
        }
    }
}
