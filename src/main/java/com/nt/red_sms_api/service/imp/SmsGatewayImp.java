package com.nt.red_sms_api.service.imp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeStatusReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.ByCondition;
import com.nt.red_sms_api.entity.view.sms_gateway.ByResponseTime;
import com.nt.red_sms_api.entity.view.sms_gateway.BySending;
import com.nt.red_sms_api.entity.view.sms_gateway.ListSmsGateway;
import com.nt.red_sms_api.repo.SmsGatewayRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.ByConditionRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.ByResponseTimeRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.BySendingRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.ListSmsGatewayRepo;
import com.nt.red_sms_api.service.SmsGatewayService;

@Service
public class SmsGatewayImp implements SmsGatewayService{

    @Autowired
    private SmsGatewayRepo smsGatewayRepo;

    @Autowired
    private ListSmsGatewayRepo listSmsGatewayRepo;

    @Autowired
    private BySendingRepo bySendingRepo;

    @Autowired
    private ByResponseTimeRepo byResponseTimeRepo;

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
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = offset / limit;
        String sortName = req.getSortName();
        String sortBy = req.getSortBy();
        String search = req.getSearch(); // fix search phone number

        if ( search.isEmpty()){
            List<ByResponseTime> smsGws = byResponseTimeRepo.ListByResponseTime(startTime, endTime, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = byResponseTimeRepo.getListByResponseTimeTotalCount(startTime, endTime);
            resp.setCount(count);
            resp.setData(smsGws);
            return resp;
        }else {
            List<ByResponseTime> smsGws = byResponseTimeRepo.ListByResponseTimeSearch(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
            Integer count = byResponseTimeRepo.getListByResponseTimeSearchTotalCount(startTime, endTime, search);
            resp.setCount(count);
            resp.setData(smsGws);
            return resp;
        }
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
        String searchField = req.getSearchField().toLowerCase();

        Long orderTypeMainID = req.getOrder_type_main_id();
        Integer isStatus = req.getIsStatus();

        if ( search.isEmpty()){
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

                if (searchField.equals("phonenumber")){
                    List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.OdtStatusPhoneFieldSearch(
                        orderTypeMainID,
                        isStatus,
                        search,
                        startTime,
                        endTime,
                        PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
                    );

                    Integer count = smsGatewayRepo.getOdtStatusPhoneFieldSearchTotalCount(
                        orderTypeMainID,
                        isStatus,
                        search,
                        startTime,
                        endTime
                    );
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("smsmessage")){
                    List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.OdtStatusSmsMsgFieldSearch(
                        orderTypeMainID,
                        isStatus,
                        search,
                        startTime,
                        endTime,
                        PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
                    );

                    Integer count = smsGatewayRepo.getOdtStatusSmsMsgFieldSearchTotalCount(
                        orderTypeMainID,
                        isStatus,
                        search,
                        startTime,
                        endTime
                    );
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("refid")){
                    List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.OdtStatusRefIdFieldSearch(
                        orderTypeMainID,
                        isStatus,
                        search,
                        startTime,
                        endTime,
                        PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
                    );

                    Integer count = smsGatewayRepo.getOdtStatusRefIdFieldSearchTotalCount(
                        orderTypeMainID,
                        isStatus,
                        search,
                        startTime,
                        endTime
                    );
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }
            }

            List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.findSmsGatewayByOrderTypeAndStatusAllSearch(
                orderTypeMainID,
                isStatus,
                search,
                startTime,
                endTime,
                PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
            );
            Integer count = smsGatewayRepo.getSmsGatewayByOrderTypeAndStatusAllSearchTotalCount(
                orderTypeMainID,
                isStatus,
                search,
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

    @Override
    public PaginationDataResp ListSendSmsGateWays(SmsGwListReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = 0;
        if(limit > 0){
            page = offset / limit;
        }
        String sortName = req.getSortName();
        String sortBy = req.getSortBy();
        String search = req.getSearch();
        String searchField = req.getSearchField().toLowerCase();

        if(offset.equals(0) && limit.equals(0)){
            if(searchField.equals("ordertype")){
                List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendOrderTypeField(startTime, endTime, search);
                Integer count = listSmsGatewayRepo.getListSendOrderTypeFieldTotalCount(startTime, endTime, search);
                resp.setCount(count);
                resp.setData(smsGws);
                return resp;
            }
        }

        if ( search.isEmpty()){
            List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendSmsGw(startTime, endTime, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = listSmsGatewayRepo.getListSendSmsGwTotalCount(startTime, endTime);
            resp.setCount(count);
            resp.setData(smsGws);
            return resp;
        }else {
            if( !req.getSearchField().isEmpty()){
                if(searchField.equals("transaction_id")){
                    List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendTransactionIDTypeField(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsGatewayRepo.getListSendTransactionIDFieldTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsGws);
                    return resp;
                } else if(searchField.equals("phonenumber")){
                    List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendPhonenumberTypeField(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsGatewayRepo.getListSendPhonenumberFieldTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsGws);
                    return resp;
                } else if(searchField.equals("smsmessage")){
                    List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendSmsMsgField(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsGatewayRepo.getListSendSmsMsgFieldTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsGws);
                    return resp;
                } else if(searchField.equals("refid")){
                    List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendRefIdField(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsGatewayRepo.getListSendRefIdFieldTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsGws);
                    return resp;
                } else if(searchField.equals("ordertype")){
                    List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendOrderTypeField(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsGatewayRepo.getListSendOrderTypeFieldTotalCount(startTime, endTime, search);
                    resp.setCount(count);
                    resp.setData(smsGws);
                    return resp;
                }
            }
            // System.out.println("3");
            List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendSmsGwAllSearch(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
            Integer count = listSmsGatewayRepo.getListSendSmsGwAllSearchTotalCount(startTime, endTime, search);
            resp.setCount(count);
            resp.setData(smsGws);
            return resp;
        }
    }
    
}
