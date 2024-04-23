package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeStatusReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.ByCondition;
import com.nt.red_sms_api.entity.view.sms_gateway.BySending;
import com.nt.red_sms_api.repo.SmsGatewayRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.ByConditionRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.BySendingRepo;
import com.nt.red_sms_api.service.SmsGatewayService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SmsGatewayImp implements SmsGatewayService{

    @Autowired
    private SmsGatewayRepo smsGatewayRepo;

    @Autowired
    private BySendingRepo bySendingRepo;

    @Autowired
    private ByConditionRepo byConditionRepo;

    @Override
    public PaginationDataResp findSmsGatewayMatchAndUnMatch(SmsGwListReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        
        // String startTime = req.getStartTime();
        // String endTime = req.getEndTime();
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = offset / limit;
        String sortBy = req.getSortBy();
        String sortName = req.getSortName();
        JpaSort sort = JpaSort.unsafe(Sort.Direction.fromString(sortBy), "("+sortName+")");

        List<ByCondition> smsGatewayEntities = byConditionRepo.ListByCondition(startTime, endTime, PageRequest.of(page, limit, sort) );
        Integer count = byConditionRepo.getListByConditionTotalCount(startTime, endTime);
        resp.setCount(count);
        resp.setData(smsGatewayEntities);
        return resp;
    }

    @Override
    public PaginationDataResp findSmsGatewaySendAndUnSend(SmsGwListReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        // String sort = "TRUNC(smsgw.created_date)" + " " + req.getSortBy();
        // String startTime = req.getStartTime();
        // String endTime = req.getEndTime();
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = offset / limit;
        String sortBy = req.getSortBy();
        String sortName = req.getSortName();
        JpaSort sort = JpaSort.unsafe(Sort.Direction.fromString(sortBy), "("+sortName+")");

        List<BySending> smsGatewayEntities = bySendingRepo.ListBySending(startTime, endTime, PageRequest.of(page, limit, sort));
        Integer count = bySendingRepo.getListBySendingTotalCount(startTime, endTime);
        resp.setCount(count);
        resp.setData(smsGatewayEntities);
        return resp;
    }

    @Override
    public PaginationDataResp findSmsGatewayResponseTime(SmsGwListReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        // String sort = "TRUNC(smsgw.created_date)" + " " + req.getSortBy();
        // String startTime = req.getStartTime();
        // String endTime = req.getEndTime();
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = offset / limit;
        String sortBy = req.getSortBy();
        String sortName = req.getSortName();
        JpaSort sort = JpaSort.unsafe(Sort.Direction.fromString(sortBy), "("+sortName+")");

        List<BySending> smsGatewayEntities = bySendingRepo.ListBySending(startTime, endTime, PageRequest.of(page, limit, sort));
        Integer count = bySendingRepo.getListBySendingTotalCount(startTime, endTime);
        resp.setCount(count);
        resp.setData(smsGatewayEntities);
        return resp;
    }


    public PaginationDataResp findSmsGatewayOrderTypeAndStatus(SmsGwOrderTypeStatusReq req) {
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
        String searchDateField = "send_date";
        Long orderTypeMainID = req.getOrder_type_main_id();
        Integer isStatus = req.getIsStatus();
        if (req.getIsStatus() == 2){
            searchDateField = "receive_date";
        }

        if ( search.isEmpty()){
            // System.out.println("orderTypeMainID:"+orderTypeMainID);
            // System.out.println("isStatus:"+isStatus);
            // System.out.println("searchDateField:"+searchDateField);
            // System.out.println("startTime:"+startTime.toString());
            // System.out.println("endTime:"+endTime.toString());
            List<SmsGatewayEntity> smsGw = smsGatewayRepo.findSmsGatewayByOrderTypeAndStatus(
                orderTypeMainID,
                isStatus,
                // searchDateField,
                startTime,
                endTime,
                PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = smsGatewayRepo.getSmsGatewayByOrderTypeAndStatusTotalCount(
                orderTypeMainID,
                isStatus,
                // searchDateField,
                startTime,
                endTime
            );
            resp.setCount(count);
            resp.setData(smsGw);
            return resp;
        }else {
            if( !req.getSearchField().isEmpty()){
                // System.out.println("2");
                List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.findSmsGatewayByOrderTypeAndStatusSearch(
                    orderTypeMainID,
                    isStatus,
                    searchField,
                    search,
                    // searchDateField,
                    startTime,
                    endTime,
                    PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
                );

                Integer count = smsGatewayRepo.getSmsGatewayByOrderTypeAndStatusSearchTotalCount(
                    orderTypeMainID,
                    isStatus,
                    searchField,
                    search,
                    // searchDateField,
                    startTime,
                    endTime
                );
                resp.setCount(count);
                resp.setData(smsConditionEntities);
                return resp;
            }
            // System.out.println("3");
            List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.findSmsGatewayByOrderTypeAndStatusAllSearch(
                orderTypeMainID,
                isStatus,
                search,
                // searchDateField,
                startTime,
                endTime,
                PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
            );
            Integer count = smsGatewayRepo.getSmsGatewayByOrderTypeAndStatusAllSearchTotalCount(
                orderTypeMainID,
                isStatus,
                search,
                // searchDateField,
                startTime,
                endTime
            );
            resp.setCount(count);
            resp.setData(smsConditionEntities);
            return resp;
        }
    }

    @Override
    public SmsGatewayEntity findSmsGatewayByID(Long id) {
        SmsGatewayEntity resp = smsGatewayRepo.GetSmsGatewayInfo(id);
        return resp;
    }
    
}
