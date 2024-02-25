package com.nt.red_sms_api.service;

import com.nt.red_sms_api.enitiy.SmsConditionEntity;

import java.util.List;
import java.util.Map;



public interface SmsConditionService  {
    public List<SmsConditionEntity> ListAllSmsCondition(Integer page, Integer limit);
    public void updateSmsConditionById(Long smsId, Map<String, Object> updates);
    public SmsConditionEntity getSmsConditionMoreDetail(Long smsID);
}
