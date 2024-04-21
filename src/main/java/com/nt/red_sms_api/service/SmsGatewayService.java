package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;



public interface SmsGatewayService  {
    public PaginationDataResp findSmsGatewayMatchAndUnMatch(SmsGwListReq req);
    public PaginationDataResp findSmsGatewaySendAndUnSend(SmsGwListReq req);
    public PaginationDataResp findSmsGatewayResponseTime(SmsGwListReq req);
}
