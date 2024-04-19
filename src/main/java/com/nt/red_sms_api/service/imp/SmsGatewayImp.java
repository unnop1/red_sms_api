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
    public PaginationDataResp findSmsGatewayMatchAndUnMatch(Integer page, Integer limit, String orderTypeID, String isStatus) {
        PaginationDataResp resp = new PaginationDataResp();
        List<SmsGatewayEntity> smsGatewayEntities = smsGatewayRepo.findSmsGatewayByOrderTypeAndIsStatus(page, limit, orderTypeID, isStatus);
        Integer count = smsGatewayRepo.getByOrderTypeAndIsStatusTotalCount();
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

    @Override
    public DefaultServiceResp getSmsGatewaysAndCondition(Long gID, Long orderTypeID) {
        String queryString = "SELECT new com.nt.red_sms_api.dto.resp.ViewSmsGwConRespDto( "+
                "smsGW.GID, smsGW.sms_conditions_SMSID, smsGW.SMSMessage, " +
                "smsGW.order_type_MainID , smsGW.OrderType, smsGW.PhoneNumber, "+
                "smsGW.serviceType, smsGW.Frequency, smsGW.Chanel, " +
                "smsGW.OfferingId, smsGW.PayloadMQ, smsGW.IsStatus, smsGW.CreatedDate, "+
                "smsCon.Message, smsCon.by_offeringId, smsCon.DateStart, smsCon.DateEnd, " +
                "smsCon.IsEnable, smsCon.IsDelete, smsCon.CreatedBy_UserID, smsCon.UpdatedDate, smsCon.UpdatedBy_UserID) " +
                "FROM SmsGatewayEntity smsGW " +
                "LEFT JOIN SmsConditionEntity smsCon ON (smsGW.order_type_MainID = smsCon.order_type_MainID)" +
                "WHERE smsGW.GID = :gID AND smsGW.order_type_MainID = :orderTypeID " +
                "ORDER BY smsGW.CreatedDate DESC";
            
        DefaultServiceResp response = new DefaultServiceResp();

        try {
            Query query = entityManager.createQuery(queryString)
                .setParameter("gID", gID)
                .setParameter("orderTypeID", orderTypeID)
                .setMaxResults(1); // Limit the result to one row
            // System.out.println(query.getSingleResult());
            response.setCount(1);
            response.setResult(query.getSingleResult());

        } catch (NoResultException e) {
            response.setCount(0);
            response.setMessage("Not Found");
            response.setResult(null);
        }catch (Exception e) {
            response.setCount(0);
            response.setMessage(e.getMessage());
            response.setError(e.getMessage());
            response.setResult(null);
        }

        return response;
    }
    
}
