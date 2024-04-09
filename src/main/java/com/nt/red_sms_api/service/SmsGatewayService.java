package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.resp.DefaultServiceResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;

import java.util.List;



public interface SmsGatewayService  {
    public List<SmsGatewayEntity> findSmsGatewayMatchAndUnMatch(Integer page, Integer limit, String orderTypeID, String isStatus);
    public List<SmsGatewayEntity> findSmsGatewaySendAndUnSend(Integer page,Integer limit);
    public DefaultServiceResp getSmsGatewaysAndCondition(Long gID, Long orderTypeID);
}
