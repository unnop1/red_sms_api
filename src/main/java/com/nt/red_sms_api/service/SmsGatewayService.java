package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.req.smsgw.SmsGwConditionReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeRespTimeReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeStatusReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.SmsGatewayResponseTimeReportResp;
import com.nt.red_sms_api.dto.resp.SmsGatewayResponseTimeResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;



public interface SmsGatewayService  {
    public SmsGatewayEntity findSmsGatewayByID(Long id);
    public PaginationDataResp ListSendSmsGateWays(SmsGwListReq req);
    public PaginationDataResp findSmsGatewayOrderTypeAndStatus(SmsGwOrderTypeStatusReq req);
    public PaginationDataResp findSmsGatewaySendings(SmsGwListReq req);
    public PaginationDataResp findSmsGatewayConditions(SmsGwConditionReq req);
    public PaginationDataResp findSmsGatewayOrderTypeReportByID(SmsGwOrderTypeReq req);
    public PaginationDataResp findSmsGatewaySendAndUnSend(SmsGwListReq req);
    public PaginationDataResp findSmsGatewayCondition(SmsGwListReq req);
    public PaginationDataResp findSmsGatewayOrderType(SmsGwListReq req);
    public SmsGatewayResponseTimeResp findSmsGatewayResponseTime(SmsGwOrderTypeRespTimeReq req);
    public SmsGatewayResponseTimeReportResp findSmsGatewayResponseTimeReport(SmsGwListReq req);
}
