package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.dto.req.Vue.SmsGwListReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.ByCondition;
import com.nt.red_sms_api.repo.SmsGatewayRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.ByConditionRepo;
import com.nt.red_sms_api.service.SmsGatewayService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SmsGatewayImp implements SmsGatewayService{

    @Autowired
    private SmsGatewayRepo smsGatewayRepo;

    @Autowired
    private ByConditionRepo byConditionRepo;

    @Override
    public PaginationDataResp findSmsGatewayMatchAndUnMatch(Integer page, Integer limit, Long orderTypeID, Integer isStatus) {
        PaginationDataResp resp = new PaginationDataResp();
        List<SmsGatewayEntity> smsGatewayEntities = smsGatewayRepo.findSmsGatewayByOrderTypeAndIsStatus(orderTypeID, isStatus, page, limit);
        Integer count = smsGatewayRepo.getByOrderTypeAndIsStatusTotalCount(orderTypeID, isStatus);
        resp.setCount(count);
        resp.setData(smsGatewayEntities);
        return resp;
    }

    @Override
    public PaginationDataResp findSmsGatewaySendAndUnSend(SmsGwListReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        String sort = "TRUNC(smsgw.created_date)" + " " + req.getSortBy();
        // String startTime = req.getStartTime();
        // String endTime = req.getEndTime();
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());
        System.out.println("start:" + req.getStart());
        System.out.println("leng:" + req.getLength());
        System.out.println("sort:" + sort);
        System.out.println("startTime: " + startTime + " endTime: " + endTime);
        List<ByCondition> smsGatewayEntities = byConditionRepo.ListByCondition(startTime, endTime,  sort, req.getStart(), req.getLength());
        Integer count = byConditionRepo.getListByConditionTotalCount(startTime, endTime);
        resp.setCount(count);
        resp.setData(smsGatewayEntities);
        return resp;
    }
    
}
