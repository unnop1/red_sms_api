package com.nt.red_sms_api.service;

import com.nt.red_sms_api.enitiy.ConfigConditionsEntity;

import java.util.List;
import java.util.Map;



public interface SmsConditionService  {
    public List<ConfigConditionsEntity> ListAllSmsCondition(Integer page, Integer limit);
    public void updateSmsConditionById(Long smsId, Map<String, Object> updates);
    public ConfigConditionsEntity getSmsConditionMoreDetail(Long smsID);
}
