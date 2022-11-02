package com.example.imbee.service.impl;

import com.example.imbee.entity.DeviceEntity;
import com.example.imbee.repository.DeviceRepository;
import com.example.imbee.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }

    @Override
    public DeviceEntity addDevice(DeviceEntity device) throws Exception {
        return deviceRepository.save(device);
    }
}
