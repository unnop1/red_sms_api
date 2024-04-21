package com.nt.red_sms_api.service;

import java.util.List;
import java.util.Map;

import com.nt.red_sms_api.dto.req.smscondition.ListConditionReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.ConfigConditionsEntity;



public interface SmsConditionService  {
    public PaginationDataResp ListAllSmsCondition(ListConditionReq req);
    public void updateSmsConditionById(Long smsId, Map<String, Object> updates);
    public ConfigConditionsEntity getSmsConditionMoreDetail(Long smsID);
}
