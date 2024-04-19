package com.nt.red_sms_api.service;

import java.util.List;
import java.util.Map;

import com.nt.red_sms_api.entity.ViewOrderTypeEntity;



public interface OrderTypeService  {
    
    public List<ViewOrderTypeEntity> ListAllOrderType(Integer page, Integer limit);
    public void UpdateOrderTypeById(Long orderTypeId, Map<String, Object> updates);
}
