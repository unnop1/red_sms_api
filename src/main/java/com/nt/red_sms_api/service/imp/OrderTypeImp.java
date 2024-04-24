package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.OrderTypeEntity;
import com.nt.red_sms_api.entity.view.order_type.ListOrderType;
import com.nt.red_sms_api.repo.OrderTypeRepo;
import com.nt.red_sms_api.repo.view.order_type.ListOrderTypeRepo;
import com.nt.red_sms_api.service.OrderTypeService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class OrderTypeImp implements OrderTypeService{

    @Autowired
    private ListOrderTypeRepo viewOrderTypeRepo;

    @Autowired
    private OrderTypeRepo orderTypeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaginationDataResp ListAllOrderType(Integer page, Integer limit) {
        PaginationDataResp resp = new PaginationDataResp();
        Integer offset = (page - 1 ) * limit;
        System.out.println("offset"+offset+" limit"+limit);
        List<ListOrderType> orderTypeEntities = viewOrderTypeRepo.findAll(offset, limit);
        Integer count = viewOrderTypeRepo.getTotalCount(offset, limit);
        resp.setData(orderTypeEntities);
        resp.setCount(count);
        return resp;
    }

}
