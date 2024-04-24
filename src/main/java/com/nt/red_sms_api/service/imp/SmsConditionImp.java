package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.dto.req.smscondition.AddSmsConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.ListConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.SmsConditionMoreDetailReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.ConfigConditionsEntity;
import com.nt.red_sms_api.entity.OrderTypeEntity;
import com.nt.red_sms_api.entity.view.smscondition.ListSmsCondition;
import com.nt.red_sms_api.repo.OrderTypeRepo;
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
    private OrderTypeRepo orderTypeRepo;
    
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
    public ConfigConditionsEntity getSmsConditionMoreDetail(SmsConditionMoreDetailReq req) {
        // System.out.println("smsID: " + smsID);
        if (req.getIsEnable() != null){
            ConfigConditionsEntity existingEntity = smsConditionRepo.findByIdAndEnable(req.getConditionsID(), req.getIsEnable());
            return existingEntity;
        }else{
            ConfigConditionsEntity existingEntity = smsConditionRepo.findSmsConditionByID(req.getConditionsID());
            return existingEntity;
        }
        // System.out.println("existingEntity ID: " + existingEntity.getConditionsID());
        
    }

    @Override
    public void addSmsCondition(AddSmsConditionReq req, String createdBy) {
        Timestamp timeNow = DateTime.getTimeStampNow();

        // Find order type
        OrderTypeEntity orderType = orderTypeRepo.findByMainId(req.getOrder_type_main_id());


        Timestamp dateStart = Timestamp.valueOf(req.getDate_start());
        Timestamp dateEnd = Timestamp.valueOf(req.getDate_end());

        
        ConfigConditionsEntity newCondition = new ConfigConditionsEntity();
        newCondition.setConditions_and(req.getConditions_and());
        newCondition.setConditions_or(req.getConditions_or());
        newCondition.setCreated_By(createdBy);
        newCondition.setCreated_Date(timeNow);
        newCondition.setDate_Start(dateStart);
        newCondition.setDate_End(dateEnd);
        newCondition.setMessage(req.getMessage());
        newCondition.setOrderType(orderType.getOrderTypeName());
        newCondition.setRefID(req.getRefID());
        smsConditionRepo.save(newCondition);

        return;
    }

    @Override
    public void removeSmsCondition(Long conditionID) {
        smsConditionRepo.deleteById(conditionID);
        return;
    }

    @Override
    public void updateSmsConditionById(Long conditionID, AddSmsConditionReq updates, String updatedBy) {
        ConfigConditionsEntity existingEntity = smsConditionRepo.findById(conditionID).orElse(null);
        // System.out.println("existingEntity ID: " + existingEntity.getConditionsID());
        // If the entity exists
        if (existingEntity != null) {
            // Iterate over the entries of the updates map
            Timestamp timeNow = DateTime.getTimeStampNow();
            if (updates.getConditions_and() != null ){
                existingEntity.setConditions_and(updates.getConditions_and());
            }
            if (updates.getConditions_or() != null ){
                existingEntity.setConditions_or(updates.getConditions_or());
            }
            if (updates.getDate_end() != null ){
                Timestamp dateStart = Timestamp.valueOf(updates.getDate_start());
                existingEntity.setDate_Start(dateStart);
            }
            if (updates.getDate_end() != null ){
                Timestamp dateEnd = Timestamp.valueOf(updates.getDate_end());
                existingEntity.setDate_End(dateEnd);
            }

            if (updates.getMessage() != null ){
                existingEntity.setMessage(updates.getMessage());
            }

            if (updates.getOrder_type_main_id() != null ){
                OrderTypeEntity orderType = orderTypeRepo.findByMainId(updates.getOrder_type_main_id());
                existingEntity.setOrderType(orderType.getOrderTypeName());
            }
            existingEntity.setUpdated_By(updatedBy);
            existingEntity.setUpdated_Date(timeNow);

            // Save the updated entity back to the database
            smsConditionRepo.save(existingEntity);
        }
    }
}
