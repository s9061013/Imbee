package com.example.imbee.service.impl;

import com.example.imbee.entity.FcmJobEntity;
import com.example.imbee.repository.FcmJobRepository;
import com.example.imbee.service.FcmJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class FcmJobServiceImpl implements FcmJobService {
    private final FcmJobRepository deviceRepository;

    @Autowired
    public FcmJobServiceImpl(FcmJobRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }

    @Override
    public FcmJobEntity addFcmJob(FcmJobEntity device) throws Exception {
        return deviceRepository.save(device);
    }
}
