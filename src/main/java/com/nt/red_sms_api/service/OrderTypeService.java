package com.nt.red_sms_api.service;

import java.util.Map;

import com.nt.red_sms_api.dto.resp.PaginationDataResp;



public interface OrderTypeService  {
    
    public PaginationDataResp ListAllOrderType(Integer page, Integer limit);
    public void UpdateOrderTypeById(Long orderTypeId, Map<String, Object> updates);
}
