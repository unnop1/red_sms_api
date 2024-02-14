package com.nt.red_sms_api.service;

import com.nt.red_sms_api.enitiy.SmsGatewayEntity;

import java.util.List;



public interface SmsGatewayService  {
    public List<SmsGatewayEntity> findSmsGatewayMatchAndUnMatch(String orderTypeID, String isStatus);
}
