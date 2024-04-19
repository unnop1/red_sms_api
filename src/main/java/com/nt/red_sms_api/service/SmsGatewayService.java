package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.resp.DefaultServiceResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;



public interface SmsGatewayService  {
    public PaginationDataResp findSmsGatewayMatchAndUnMatch(Integer page, Integer limit, String orderTypeID, String isStatus);
    public PaginationDataResp findSmsGatewaySendAndUnSend(Integer page,Integer limit);
    public DefaultServiceResp getSmsGatewaysAndCondition(Long gID, Long orderTypeID);
}
