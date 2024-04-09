package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.entity.ConfigConditionsEntity;
import com.nt.red_sms_api.entity.OrderTypeEntity;
import com.nt.red_sms_api.repo.SmsConditionRepo;
import com.nt.red_sms_api.service.SmsConditionService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class SmsConditionImp implements SmsConditionService{

    @Autowired
    private SmsConditionRepo smsConditionRepo;
    // @Autowired
    // private ModelMapper modelMapper;

    public List<ConfigConditionsEntity> ListAllSmsCondition(Integer page, Integer limit) {
        List<ConfigConditionsEntity> smsConditionEntities = smsConditionRepo.findAll(page, limit);
        // List<SmsConditionResponseDto> userResponseDtoList = smsConditionEntities.stream().map(smsCondition->this.smsConditionEntityToSmsConditionRespDto(smsCondition)).collect(Collectors.toList());
        return smsConditionEntities;
    }

    @Override
    public void updateSmsConditionById(Long smsID, Map<String, Object> updates) {
        ConfigConditionsEntity existingEntity = smsConditionRepo.findById(smsID).orElse(null);
        System.out.println("existingEntity ID: " + existingEntity.getConditionsID());
        // If the entity exists
        if (existingEntity != null) {
            // Iterate over the entries of the updates map
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();

                try {
                    // Get the field from the entity class
                    Field field = ConfigConditionsEntity.class.getDeclaredField(fieldName);
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
            smsConditionRepo.save(existingEntity);
        }
    }

    @Override
    public ConfigConditionsEntity getSmsConditionMoreDetail(Long smsID) {
        System.out.println("smsID: " + smsID);
        ConfigConditionsEntity existingEntity = smsConditionRepo.findById(smsID).orElse(null);
        // System.out.println("existingEntity ID: " + existingEntity.getConditionsID());
        return existingEntity;
    }
}
