package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeStatusReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.SmsGatewayDetail;



public interface SmsGatewayService  {
    public SmsGatewayEntity findSmsGatewayByID(Long id);
    public PaginationDataResp ListSendSmsGateWays(SmsGwListReq req);
    public PaginationDataResp findSmsGatewayOrderTypeAndStatus(SmsGwOrderTypeStatusReq req);
    public PaginationDataResp findSmsGatewaySendAndUnSend(SmsGwListReq req);
    public PaginationDataResp findSmsGatewayMatchAndUnMatch(SmsGwListReq req);
    public PaginationDataResp findSmsGatewayResponseTime(SmsGwListReq req);
}
