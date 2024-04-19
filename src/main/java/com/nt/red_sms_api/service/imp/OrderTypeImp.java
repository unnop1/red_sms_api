package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.dto.resp.OrderTypeResponseDto;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.OrderTypeEntity;
import com.nt.red_sms_api.entity.ViewOrderTypeEntity;
import com.nt.red_sms_api.repo.OrderTypeRepo;
import com.nt.red_sms_api.repo.ViewOrderTypeRepo;
import com.nt.red_sms_api.service.OrderTypeService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderTypeImp implements OrderTypeService{

    @Autowired
    private ViewOrderTypeRepo viewOrderTypeRepo;

    @Autowired
    private OrderTypeRepo orderTypeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaginationDataResp ListAllOrderType(Integer page, Integer limit) {
        PaginationDataResp resp = new PaginationDataResp();
        Integer offset = (page - 1 ) * limit;
        System.out.println("offset"+offset+" limit"+limit);
        List<ViewOrderTypeEntity> orderTypeEntities = viewOrderTypeRepo.findAll(offset, limit);
        Integer count = viewOrderTypeRepo.getTotalCount(offset, limit);
        resp.setData(orderTypeEntities);
        resp.setCount(count);
        return resp;
    }

    @Override
    public void UpdateOrderTypeById(Long orderTypeId, Map<String, Object> updates) {
        OrderTypeEntity existingEntity = orderTypeRepo.findById(orderTypeId).orElse(null);
        System.out.println("existingEntity ID: " + existingEntity.getTYPEID());
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
