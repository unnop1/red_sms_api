package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.dto.req.smscondition.ListConditionReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.ConfigConditionsEntity;
import com.nt.red_sms_api.entity.OrderTypeEntity;
import com.nt.red_sms_api.entity.view.smscondition.ListSmsCondition;
import com.nt.red_sms_api.repo.SmsConditionRepo;
import com.nt.red_sms_api.repo.view.smscondition.ListSmsConditionRepo;
import com.nt.red_sms_api.service.SmsConditionService;

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
public class SmsConditionImp implements SmsConditionService{

    @Autowired
    private SmsConditionRepo smsConditionRepo;
    
    @Autowired
    private ListSmsConditionRepo listSmsConditionRepo;

    public PaginationDataResp ListAllSmsCondition(ListConditionReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = offset / limit;
        String sortName = req.getSortName();
        String sortBy = req.getSortBy();
        String search = req.getSearch();
        String searchField = req.getSearchField();

        if ( search.isEmpty()){
            List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsCondition(startTime, endTime, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = listSmsConditionRepo.getListSmsConditionTotalCount(startTime, endTime);
            resp.setCount(count);
            resp.setData(smsConditionEntities);
            return resp;
        }else {
            if( !req.getSearchField().isEmpty()){
                List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionLike(startTime, endTime, searchField, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy)));
                Integer count = listSmsConditionRepo.getListSmsConditionLikeTotalCount(startTime, endTime, searchField, search);
                resp.setCount(count);
                resp.setData(smsConditionEntities);
                return resp;
            }

            List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionAllLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy)));
            Integer count = listSmsConditionRepo.getListSmsConditionAllLikeTotalCount(startTime, endTime, search);
            resp.setCount(count);
            resp.setData(smsConditionEntities);
            return resp;
        }
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
