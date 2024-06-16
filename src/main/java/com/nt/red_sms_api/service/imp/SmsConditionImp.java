package com.nt.red_sms_api.service.imp;

import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.dto.req.smscondition.AddSmsConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.ListConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.SmsConditionMoreDetailReq;
import com.nt.red_sms_api.dto.resp.DataObjectResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.ConfigConditionsEntity;
import com.nt.red_sms_api.entity.OrderTypeEntity;
import com.nt.red_sms_api.entity.view.smscondition.ListSmsCondition;
import com.nt.red_sms_api.repo.OrderTypeRepo;
import com.nt.red_sms_api.repo.SmsConditionRepo;
import com.nt.red_sms_api.repo.view.smscondition.ListSmsConditionRepo;
import com.nt.red_sms_api.service.SmsConditionService;

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

        // if (startTime != null && endTime == null){
        //     return ListAllSmsConditionOnlyStartTime(req);
        // }

        // if (startTime == null && endTime != null){
        //     return ListAllSmsConditionOnlyEndTime(req);
        // }
            

        // if ( search.isEmpty()){
        //     if (req.getStartTime().isEmpty() || req.getEndTime().isEmpty()){
        //         List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsCondition(PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
        //         Integer count = listSmsConditionRepo.getListSmsConditionTotalCount();
        //         resp.setCount(count);
        //         resp.setData(smsConditionEntities);
        //         return resp;
        //     }
        //     List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionWithTime(startTime, endTime, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
        //     Integer count = listSmsConditionRepo.getListSmsConditionWithTimeTotalCount(startTime, endTime);
        //     resp.setCount(count);
        //     resp.setData(smsConditionEntities);
        //     return resp;
        // }else {
            if( !req.getSearchField().isEmpty()){
                if (searchField.equals("date_end")){
                    if (req.getStartTime().isEmpty() || req.getEndTime().isEmpty()){
                        List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionAllLike(search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                        Integer count = listSmsConditionRepo.getListSmsConditionAllLikeTotalCount(search);
                        resp.setCount(count);
                        resp.setData(smsConditionEntities);
                        return resp;
                    }
                    return ListAllSmsConditionOnlyEndTime(req);
                }else if (searchField.equals("date_start")){
                    if (req.getStartTime().isEmpty() || req.getEndTime().isEmpty()){
                        List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionAllLike(search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                        Integer count = listSmsConditionRepo.getListSmsConditionAllLikeTotalCount(search);
                        resp.setCount(count);
                        resp.setData(smsConditionEntities);
                        return resp;
                    }
                    return ListAllSmsConditionOnlyStartTime(req);
                }else if (searchField.equals("ordertype")){
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
        // }
    }

    public PaginationDataResp ListAllSmsConditionOnlyStartTime(ListConditionReq req) {
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
            List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionWithStartTime(startTime, endTime, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = listSmsConditionRepo.getListSmsConditionWithStartTimeTotalCount(startTime,endTime);
            resp.setCount(count);
            resp.setData(smsConditionEntities);
            return resp;
        }else {
            if( !req.getSearchField().isEmpty()){
                if (searchField.equals("ordertype")){
                    List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListOrderTypeWithStartTimeLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsConditionRepo.getListOrderTypeLikeWithStartTimeTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("conditions_id")){
                    List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListConditionIdWithStartTimeLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsConditionRepo.getListConditionIdWithStartTimeLikeTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("message")){
                    List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListMessageWithStartTimeLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsConditionRepo.getListMessageWithStartTimeLikeTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }
            }
            
            List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionWithStartTimeAllLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
            Integer count = listSmsConditionRepo.getListSmsConditionWithStartTimeAllLikeTotalCount(startTime, endTime, search);
            resp.setCount(count);
            resp.setData(smsConditionEntities);
            return resp;
        }
    }

    public PaginationDataResp ListAllSmsConditionOnlyEndTime(ListConditionReq req) {
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
            List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionWithEndTime(startTime, endTime, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = listSmsConditionRepo.getListSmsConditionWithEndTimeTotalCount(startTime, endTime);
            resp.setCount(count);
            resp.setData(smsConditionEntities);
            return resp;
        }else {
            if( !req.getSearchField().isEmpty()){
                if (searchField.equals("ordertype")){
                    List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListOrderTypeWithEndTimeLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsConditionRepo.getListOrderTypeLikeWithEndTimeTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("conditions_id")){
                    List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListConditionIdWithEndTimeLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsConditionRepo.getListConditionIdWithEndTimeLikeTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("message")){
                    List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListMessageWithEndTimeLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsConditionRepo.getListMessageWithEndTimeLikeTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }
            }
            
            List<ListSmsCondition> smsConditionEntities = listSmsConditionRepo.ListSmsConditionWithEndTimeAllLike(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
            Integer count = listSmsConditionRepo.getListSmsConditionWithEndTimeAllLikeTotalCount(startTime, endTime, search);
            resp.setCount(count);
            resp.setData(smsConditionEntities);
            return resp;
        }
    }

    @Override
    public ConfigConditionsEntity getSmsConditionMoreDetail(SmsConditionMoreDetailReq req) {
        ConfigConditionsEntity existingEntity = smsConditionRepo.findSmsConditionByID(req.getConditionsID());
        return existingEntity;
        
    }

    
    @Override
    public String getPaddingShiftRefID() {
        ConfigConditionsEntity lastRow = smsConditionRepo.getLastID();
        String paddedNumber = String.format("%05d", lastRow.getConditionsID()+2);
        return paddedNumber;
    }

    @Override
    public Long addSmsCondition(AddSmsConditionReq req, String createdBy) {
        Timestamp timeNow = DateTime.getTimeStampNow();

        // Find order type
        OrderTypeEntity orderType = orderTypeRepo.findByMainId(req.getOrder_type_main_id());
        String orderTypeName = orderType.getOrderTypeName();
        Timestamp dateStart = Timestamp.valueOf(req.getDate_start());
        Timestamp dateEnd = Timestamp.valueOf(req.getDate_end());
        String paddedNumber = getPaddingShiftRefID();
        
        String refID = orderTypeName.toUpperCase() + paddedNumber;
        
        ConfigConditionsEntity newCondition = new ConfigConditionsEntity();
        Clob conditions_andClob;
        
        try {
            // conditions_andClob = new javax.sql.rowset.serial.SerialClob(req.getConditions_and().toCharArray());
            newCondition.setConditions_and(req.getConditions_and());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

        Clob conditions_orClob;
        try {
            // conditions_orClob = new javax.sql.rowset.serial.SerialClob(req.getConditions_or().toCharArray());
            newCondition.setConditions_or(req.getConditions_or());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

        Clob conditions_and_selectClob;
        try {
            // conditions_and_selectClob = new javax.sql.rowset.serial.SerialClob(req.getCONDITIONS_AND_SELECT().toCharArray());
            newCondition.setConditions_and_select(req.getCONDITIONS_AND_SELECT());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

        Clob conditions_or_selectClob;
        try {
            // conditions_or_selectClob = new javax.sql.rowset.serial.SerialClob(req.getCONDITIONS_OR_SELECT().toCharArray());
            newCondition.setConditions_or_select(req.getCONDITIONS_OR_SELECT());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        newCondition.setOrder_type_MainID(orderType.getMainID());
        newCondition.setCreated_By(createdBy);
        newCondition.setCreated_Date(timeNow);
        newCondition.setDate_Start(dateStart);
        newCondition.setDate_End(dateEnd);
        newCondition.setMessage(req.getMessage());
        newCondition.setOrderType(orderTypeName);
        newCondition.setIs_enable(req.getIs_enable());
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
                Clob conditions_andClob;
                try {
                    // conditions_andClob = new javax.sql.rowset.serial.SerialClob(updates.getConditions_and().toCharArray());
                    existingEntity.setConditions_and(updates.getConditions_and());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                
            }
            if (updates.getConditions_or() != null ){
                Clob conditions_orClob;
                try {
                    // conditions_orClob = new javax.sql.rowset.serial.SerialClob(updates.getConditions_or().toCharArray());
                    existingEntity.setConditions_or(updates.getConditions_or());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
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
                Clob conditions_or_selectClob;
                try {
                    // conditions_or_selectClob = new javax.sql.rowset.serial.SerialClob(updates.getCONDITIONS_OR_SELECT().toCharArray());
                    existingEntity.setConditions_or_select(updates.getCONDITIONS_OR_SELECT());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                
            }

            if (updates.getCONDITIONS_AND_SELECT() != null ){
                Clob conditions_and_selectClob;
                try {
                    // conditions_and_selectClob = new javax.sql.rowset.serial.SerialClob(updates.getCONDITIONS_AND_SELECT().toCharArray());
                    existingEntity.setConditions_and_select(updates.getCONDITIONS_AND_SELECT());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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
            String orderTypeName = orderType.getOrderTypeName();
            String paddedNumber = getPaddingShiftRefID();
            String refID = orderTypeName.toUpperCase() + paddedNumber;

            ConfigConditionsEntity newCondition = new ConfigConditionsEntity();
            newCondition.setConditions_and(exist.getConditions_and());
            newCondition.setConditions_or_select(exist.getConditions_or_select());
            newCondition.setConditions_and_select(exist.getConditions_and_select());
            newCondition.setConditions_or(exist.getConditions_or());
            newCondition.setRefID(refID);
            newCondition.setCreated_By(createdBy);
            newCondition.setCreated_Date(timeNow);
            newCondition.setDate_Start(exist.getDate_Start());
            newCondition.setOrder_type_MainID(orderType.getMainID());
            newCondition.setDate_End(exist.getDate_End());
            newCondition.setMessage(exist.getMessage());
            newCondition.setOrderType(orderType.getOrderTypeName());
            newCondition.setIs_enable(0);
            ConfigConditionsEntity created = smsConditionRepo.save(newCondition);
            return created.getConditionsID();
        }

        return null;

    }
}
