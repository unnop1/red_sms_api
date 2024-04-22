package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
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
    
}
