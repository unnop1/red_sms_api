package com.nt.red_sms_api.service;

import com.nt.red_sms_api.enitiy.ViewOrderTypeEntity;

import java.util.List;
import java.util.Map;



public interface OrderTypeService  {
    
    public List<ViewOrderTypeEntity> ListAllOrderType(Integer page, Integer limit);
    public void updateOrderTypeById(Long orderTypeId, Map<String, Object> updates);
}
