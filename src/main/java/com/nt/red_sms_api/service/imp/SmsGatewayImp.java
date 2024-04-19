package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.dto.resp.DefaultServiceResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.repo.SmsGatewayRepo;
import com.nt.red_sms_api.service.SmsGatewayService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SmsGatewayImp implements SmsGatewayService{

    @Autowired
    private SmsGatewayRepo smsGatewayRepo;

    @PersistenceContext
    private EntityManager entityManager;

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
    public PaginationDataResp findSmsGatewaySendAndUnSend(Integer page, Integer limit) {
        PaginationDataResp resp = new PaginationDataResp();
        List<SmsGatewayEntity> smsGatewayEntities = smsGatewayRepo.findSmsGatewaySendUnSend(page, limit);
        Integer count = smsGatewayRepo.getSendUnSendTotalCount();
        resp.setCount(count);
        resp.setData(smsGatewayEntities);
        return resp;
    }
    
}
