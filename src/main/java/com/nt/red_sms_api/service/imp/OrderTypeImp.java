package com.nt.red_sms_api.service.imp;

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
        Integer offset = req.getPage();
        Integer limit = req.getLimit();
        Integer page = offset / limit;
        String sortName = req.getSortName();
        String sortBy = req.getSortBy();
        String search = req.getSearch();
        String searchField = req.getSearchField().toLowerCase();
        System.out.println("offset"+offset+" limit"+limit);
        List<ListOrderType> orderTypeEntities = viewOrderTypeRepo.ListOrderType(startTime,endTime, PageRequest.of(page, limit,Sort.Direction.fromString(sortBy), sortName));
        Integer count = viewOrderTypeRepo.getTotalCount(startTime,endTime);
        resp.setData(orderTypeEntities);
        resp.setCount(count);
        return resp;
    }

    @Override
    public void UpdateOrderTypeById(Long orderTypeId, Map<String, Object> updates) {
        OrderTypeEntity existingEntity = orderTypeRepo.findById(orderTypeId).orElse(null);
        // System.out.println("existingEntity ID: " + existingEntity.getTYPEID());
        // If the entity exists
        if (existingEntity != null) {
            // Iterate over the entries of the updates map
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();

                try {
                    // Get the field from the entity class
                    Field field = OrderTypeEntity.class.getDeclaredField(fieldName);
                    // Set the accessibility of the field to true if it's not already accessible
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    // Set the value of the field in the entity
                    field.set(existingEntity, value);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // Handle any exceptions (e.g., field not found, access violation)
                    e.printStackTrace();
                }
            }

            // Save the updated entity back to the database
            orderTypeRepo.save(existingEntity);
        }
    }




}
