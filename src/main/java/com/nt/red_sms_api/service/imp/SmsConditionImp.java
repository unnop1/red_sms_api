package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.enitiy.SmsConditionEntity;
import com.nt.red_sms_api.repo.SmsConditionRepo;
import com.nt.red_sms_api.service.SmsConditionService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsConditionImp implements SmsConditionService{

    @Autowired
    private SmsConditionRepo smsConditionRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<SmsConditionEntity> ListAllSmsCondition() {
        List<SmsConditionEntity> smsConditionEntities = smsConditionRepo.findAll();
        // List<SmsConditionResponseDto> userResponseDtoList = smsConditionEntities.stream().map(smsCondition->this.smsConditionEntityToSmsConditionRespDto(smsCondition)).collect(Collectors.toList());
        return smsConditionEntities;
    }
}
