package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.dto.req.smscondition.AddSmsConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.ListConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.SmsConditionMoreDetailReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.ConfigConditionsEntity;
import com.nt.red_sms_api.entity.OrderTypeEntity;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.smscondition.ListSmsCondition;
import com.nt.red_sms_api.repo.OrderTypeRepo;
import com.nt.red_sms_api.repo.SmsConditionRepo;
import com.nt.red_sms_api.repo.view.smscondition.ListSmsConditionRepo;
import com.nt.red_sms_api.service.SmsConditionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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
        Timestamp startTime = null;
        if (!req.getStartTime().isEmpty()){
            startTime = Timestamp.valueOf(req.getStartTime());
        }
        Timestamp endTime = null;
        if (!req.getEndTime().isEmpty()){
            endTime = Timestamp.valueOf(req.getEndTime());
        }
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = offset / limit;
        String sortName = req.getSortName();
        String sortBy = req.getSortBy();
        String search = req.getSearch();
        String searchField = req.getSearchField().toLowerCase();

        if ( search.isEmpty()){
            if (req.getStartTime().isEmpty() || req.getEndTime().isEmpty()){
                List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsCondition(PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
                Integer count = listSmsConditionRepo.getListSmsConditionTotalCount();
                resp.setCount(count);
                resp.setData(smsConditionEntities);
                return resp;
            }
            List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionWithTime(startTime, endTime, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = listSmsConditionRepo.getListSmsConditionWithTimeTotalCount(startTime, endTime);
            resp.setCount(count);
            resp.setData(smsConditionEntities);
            return resp;
        }else {
            if( !req.getSearchField().isEmpty()){
                if (searchField.equals("ordertype")){
                    if (req.getStartTime().isEmpty() || req.getEndTime().isEmpty()){
                        List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListOrderTypeLike(search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                        Integer count = listSmsConditionRepo.getListOrderTypeLikeTotalCount(search);
                        resp.setCount(count);
                        resp.setData(smsConditionEntities);
                        return resp;
                    }
                    List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListOrderTypeWithTimeLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsConditionRepo.getListOrderTypeLikeWithTimeTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("conditions_id")){
                    if (req.getStartTime().isEmpty() || req.getEndTime().isEmpty()){
                        List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListConditionIdLike(search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                        Integer count = listSmsConditionRepo.getListConditionIdLikeTotalCount(search);
                        resp.setCount(count);
                        resp.setData(smsConditionEntities);
                        return resp;
                    }
                    List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListConditionIdWithTimeLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsConditionRepo.getListConditionIdWithTimeLikeTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("message")){
                    if (req.getStartTime().isEmpty() || req.getEndTime().isEmpty()){
                        List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListMessageLike(search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                        Integer count = listSmsConditionRepo.getListMessageLikeTotalCount(search);
                        resp.setCount(count);
                        resp.setData(smsConditionEntities);
                        return resp;
                    }
                    List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListMessageWithTimeLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsConditionRepo.getListMessageWithTimeLikeTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }
            }

            
            if (req.getStartTime().isEmpty() || req.getEndTime().isEmpty()){
                List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionAllLike(search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                Integer count = listSmsConditionRepo.getListSmsConditionAllLikeTotalCount(search);
                resp.setCount(count);
                resp.setData(smsConditionEntities);
                return resp;
            }
            List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionWithTimeAllLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
            Integer count = listSmsConditionRepo.getListSmsConditionWithTimeAllLikeTotalCount(startTime, endTime, search);
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
    public Long addSmsCondition(AddSmsConditionReq req, String createdBy) {
        Timestamp timeNow = DateTime.getTimeStampNow();

        // Find order type
        OrderTypeEntity orderType = orderTypeRepo.findByMainId(req.getOrder_type_main_id());
        ConfigConditionsEntity lastRow = smsConditionRepo.getLastID();
        String orderTypeName = orderType.getOrderTypeName();
        Timestamp dateStart = Timestamp.valueOf(req.getDate_start());
        Timestamp dateEnd = Timestamp.valueOf(req.getDate_end());
        String paddedNumber = String.format("%05d", lastRow.getConditionsID()+1);
        String refID = orderTypeName.toUpperCase() + paddedNumber;
        
        ConfigConditionsEntity newCondition = new ConfigConditionsEntity();
        newCondition.setConditions_and(req.getConditions_and());
        newCondition.setOrder_type_MainID(orderType.getMainID());
        newCondition.setConditions_and_select(req.getCONDITIONS_AND_SELECT());
        newCondition.setConditions_or(req.getConditions_or());
        newCondition.setConditions_or_select(req.getCONDITIONS_OR_SELECT());
        newCondition.setConditions_or(req.getConditions_or());
        newCondition.setCreated_By(createdBy);
        newCondition.setCreated_Date(timeNow);
        newCondition.setDate_Start(dateStart);
        newCondition.setDate_End(dateEnd);
        newCondition.setMessage(req.getMessage());
        newCondition.setOrderType(orderTypeName);
        newCondition.setRefID(refID);
        ConfigConditionsEntity created = smsConditionRepo.save(newCondition);

        return created.getConditionsID();
    }

    @Override
    public void removeSmsCondition(Long conditionID) {
        smsConditionRepo.deleteById(conditionID);
        return;
    }

    @Override
    public void updateSmsConditionById(Long conditionID, AddSmsConditionReq updates, String updatedBy) {
        ConfigConditionsEntity existingEntity = smsConditionRepo.findById(conditionID).orElse(null);
        
        if (existingEntity != null) {

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

            if (updates.getIs_enable() != null ){
                existingEntity.setIs_enable(updates.getIs_enable());
            }

            if (updates.getMessage() != null ){
                existingEntity.setMessage(updates.getMessage());
            }

            if (updates.getCONDITIONS_OR_SELECT() != null ){
                existingEntity.setConditions_or_select(updates.getCONDITIONS_OR_SELECT());
            }

            if (updates.getCONDITIONS_AND_SELECT() != null ){
                existingEntity.setConditions_and_select(updates.getCONDITIONS_AND_SELECT());
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

    @Override
    public Long duplicateSmsCondition(Long conditionID, String createdBy) {
        ConfigConditionsEntity exist = smsConditionRepo.findById(conditionID).orElse(null);

        if (exist != null) {
            Timestamp timeNow = DateTime.getTimeStampNow();

            // Find order type
            OrderTypeEntity orderType = orderTypeRepo.findByMainId(exist.getOrder_type_MainID());
            
            ConfigConditionsEntity newCondition = new ConfigConditionsEntity();
            newCondition.setConditions_and(exist.getConditions_and());
            newCondition.setConditions_or(exist.getConditions_or());
            newCondition.setCreated_By(createdBy);
            newCondition.setCreated_Date(timeNow);
            newCondition.setDate_Start(exist.getDate_Start());
            newCondition.setOrder_type_MainID(orderType.getMainID());
            newCondition.setDate_End(exist.getDate_End());
            newCondition.setMessage(exist.getMessage());
            newCondition.setOrderType(orderType.getOrderTypeName());
            newCondition.setRefID(exist.getRefID());
            newCondition.setIs_enable(0);
            ConfigConditionsEntity created = smsConditionRepo.save(newCondition);
            return created.getConditionsID();
        }

        return null;

    }
}
