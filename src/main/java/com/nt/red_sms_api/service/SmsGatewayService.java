package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.req.Vue.SmsGwListReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;



public interface SmsGatewayService  {
    public PaginationDataResp findSmsGatewayMatchAndUnMatch(Integer page, Integer limit, Long orderTypeID, Integer isStatus);
    public PaginationDataResp findSmsGatewaySendAndUnSend(SmsGwListReq req);
}
