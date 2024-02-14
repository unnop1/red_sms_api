package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.enitiy.SmsGatewayEntity;
import com.nt.red_sms_api.repo.SmsGatewayRepo;
import com.nt.red_sms_api.service.SmsGatewayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsGatewayImp implements SmsGatewayService{

    @Autowired
    private SmsGatewayRepo smsGatewayRepo;

    @Override
    public List<SmsGatewayEntity> findSmsGatewayMatchAndUnMatch(String orderTypeID, String isStatus) {
        List<SmsGatewayEntity> smsGatewayEntities = smsGatewayRepo.findSmsGatewayByOrderTypeAndIsStatus(orderTypeID, isStatus);
        // List<SmsConditionResponseDto> userResponseDtoList = smsConditionEntities.stream().map(smsCondition->this.smsConditionEntityToSmsConditionRespDto(smsCondition)).collect(Collectors.toList());
        return smsGatewayEntities;
    }
}
