package com.nt.red_sms_api.service;

import java.util.List;
import com.nt.red_sms_api.dto.req.ordertype.AddOrderTypeReq;
import com.nt.red_sms_api.dto.req.ordertype.ListOrderTypeReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.OrderTypeEntity;



public interface OrderTypeService  {
    
    public PaginationDataResp ListAllOrderType(ListOrderTypeReq req);
    public List<OrderTypeEntity> getAllOrderType();
    public void UpdateOrderTypeById(Long orderTypeId, AddOrderTypeReq updates, String updateBy);

}
