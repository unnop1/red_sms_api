package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.dto.req.ordertype.AddOrderTypeReq;
import com.nt.red_sms_api.dto.req.ordertype.ListOrderTypeReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.OrderTypeEntity;
import com.nt.red_sms_api.entity.view.order_type.ListOrderType;
import com.nt.red_sms_api.repo.OrderTypeRepo;
import com.nt.red_sms_api.repo.view.order_type.ListOrderTypeRepo;
import com.nt.red_sms_api.service.OrderTypeService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.sql.Timestamp;
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
    public PaginationDataResp ListAllOrderType(ListOrderTypeReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        Timestamp startTime = Timestamp.valueOf(req.getStart_time());
        Timestamp endTime = Timestamp.valueOf(req.getEnd_time());
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = 0;
        if(limit > 0){
            page = offset / limit;
        }
        String sortName = req.getSortName();
        String sortBy = req.getSortBy();
        String search = req.getSearch();
        String searchField = req.getSearchField().toLowerCase();
        System.out.println("page:" + page + " limit:" + limit);
        System.out.println("startTime:" + startTime + " endTime:" + endTime);
        System.out.println("sortName:" + sortName + " sortBy:" + sortBy);
        List<ListOrderType> orderTypeEntities = viewOrderTypeRepo.ListAllOrderType(startTime, endTime, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
        Integer count = viewOrderTypeRepo.getTotalCount();
        resp.setData(orderTypeEntities);
        resp.setCount(count);
        return resp;
    }

    @Override
    public void UpdateOrderTypeById(Long orderTypeId, AddOrderTypeReq updates, String updatedBy) {
        OrderTypeEntity existingEntity = orderTypeRepo.findById(orderTypeId).orElse(null);
        // If the entity exists
        if (existingEntity != null) {
            Timestamp timeNow = DateTime.getTimeStampNow();
            if (updates.getIsEnable() != null ){
                existingEntity.setIsEnable(updates.getIsEnable());
            }

            existingEntity.setUpdatedDate(timeNow);
            existingEntity.setUpdated_By(updatedBy);

            // Save the updated entity back to the database
            orderTypeRepo.save(existingEntity);
        }
    }

    @Override
    public List<OrderTypeEntity> getAllOrderType() {
        List<OrderTypeEntity> existingEntity = orderTypeRepo.findAll();
        return existingEntity;
    }




}
