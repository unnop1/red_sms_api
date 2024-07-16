package com.nt.red_sms_api.service.imp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import com.nt.red_sms_api.dto.req.smsgw.SmsGwConditionReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeRespTimeReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeStatusReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.SmsGatewayResponseTimeReportResp;
import com.nt.red_sms_api.dto.resp.SmsGatewayResponseTimeResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.ByCondition;
import com.nt.red_sms_api.entity.view.sms_gateway.ByOrderType;
import com.nt.red_sms_api.entity.view.sms_gateway.ByResponseReportTime;
import com.nt.red_sms_api.entity.view.sms_gateway.ByResponseTime;
import com.nt.red_sms_api.entity.view.sms_gateway.BySending;
import com.nt.red_sms_api.entity.view.sms_gateway.ListSmsGateway;
import com.nt.red_sms_api.repo.SmsGatewayRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.ByConditionRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.ByOrderTypeRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.ByResponseTimeRepo;
import com.nt.red_sms_api.repo.view.sms_gateway.ByResponseTimeReportRepo;
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
    private ByResponseTimeReportRepo byResponseTimeReportRepo;

    @Autowired
    private ByConditionRepo byConditionRepo;

    @Autowired
    private ByOrderTypeRepo byOrderTypeRepo;

    @Override
    public PaginationDataResp findSmsGatewayCondition(SmsGwListReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        
        // String startTime = req.getStartTime();
        // String endTime = req.getEndTime();
        String byTime = req.getByTime().toLowerCase();
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = 0;
        if(limit > 0){
            page = offset / limit;
        }
        String sortBy = req.getSortBy();
        String sortName = req.getSortName();
        JpaSort sort = JpaSort.unsafe(Sort.Direction.fromString(sortBy), "("+sortName+")");

        if(byTime.equals("date")){
            if(offset.equals(0) && limit.equals(0)){
                List<ByCondition> smsGatewayEntities = byConditionRepo.ListByConditionDate(startTime, endTime);
                Integer count = byConditionRepo.getListByConditionDateTotalCount(startTime, endTime);
                resp.setCount(count);
                resp.setData(smsGatewayEntities);
                return resp;
            }
            

            List<ByCondition> smsGatewayEntities = byConditionRepo.ListByConditionDate(startTime, endTime, PageRequest.of(page, limit, sort) );
            Integer count = byConditionRepo.getListByConditionDateTotalCount(startTime, endTime);
            resp.setCount(count);
            resp.setData(smsGatewayEntities);
            return resp;
        }else if(byTime.equals("month")) {
            if(offset.equals(0) && limit.equals(0)){
                List<ByCondition> smsGatewayEntities = byConditionRepo.ListByConditionMonth(startTime, endTime);
                Integer count = byConditionRepo.getListByConditionMonthTotalCount(startTime, endTime);
                resp.setCount(count);
                resp.setData(smsGatewayEntities);
                return resp;
            }
            

            List<ByCondition> smsGatewayEntities = byConditionRepo.ListByConditionMonth(startTime, endTime, PageRequest.of(page, limit, sort) );
            Integer count = byConditionRepo.getListByConditionMonthTotalCount(startTime, endTime);
            resp.setCount(count);
            resp.setData(smsGatewayEntities);
            return resp;
        }
        return resp;
    }

    @Override
    public PaginationDataResp findSmsGatewayOrderType(SmsGwListReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        
        // String startTime = req.getStartTime();
        // String endTime = req.getEndTime();
        String byTime = req.getByTime().toLowerCase();
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = 0;
        if(limit > 0){
            page = offset / limit;
        }
        String sortBy = req.getSortBy();
        String sortName = req.getSortName();
        JpaSort sort = JpaSort.unsafe(Sort.Direction.fromString(sortBy), "("+sortName+")");

        if(byTime.equals("date")){
            if(offset.equals(0) && limit.equals(0)){
                List<ByOrderType> smsGatewayEntities = byOrderTypeRepo.ListByOrderTypeDate(startTime, endTime);
                Integer count = byOrderTypeRepo.getListByOrderTypeDateTotalCount(startTime, endTime);
                resp.setCount(count);
                resp.setData(smsGatewayEntities);
                return resp;
            }
            

            List<ByOrderType> smsGatewayEntities = byOrderTypeRepo.ListByOrderTypeDate(startTime, endTime, PageRequest.of(page, limit, sort) );
            Integer count = byOrderTypeRepo.getListByOrderTypeDateTotalCount(startTime, endTime);
            resp.setCount(count);
            resp.setData(smsGatewayEntities);
            return resp;
        }else if(byTime.equals("month")) {
            if(offset.equals(0) && limit.equals(0)){
                List<ByOrderType> smsGatewayEntities = byOrderTypeRepo.ListByOrderTypeMonth(startTime, endTime);
                Integer count = byOrderTypeRepo.getListByOrderTypeMonthTotalCount(startTime, endTime);
                resp.setCount(count);
                resp.setData(smsGatewayEntities);
                return resp;
            }
            

            List<ByOrderType> smsGatewayEntities = byOrderTypeRepo.ListByOrderTypeMonth(startTime, endTime, PageRequest.of(page, limit, sort) );
            Integer count = byOrderTypeRepo.getListByOrderTypeMonthTotalCount(startTime, endTime);
            resp.setCount(count);
            resp.setData(smsGatewayEntities);
            return resp;
        }
        return resp;
    }

    @Override
    public PaginationDataResp findSmsGatewaySendAndUnSend(SmsGwListReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = 0;
        if(limit > 0){
            page = offset / limit;
        }
        String sortBy = req.getSortBy();
        String sortName = req.getSortName();
        JpaSort sort = JpaSort.unsafe(Sort.Direction.fromString(sortBy), "("+sortName+")");

        if(offset.equals(0) && limit.equals(0)){
            List<BySending> smsGatewayEntities = bySendingRepo.ListBySending(startTime, endTime);
            Integer count = bySendingRepo.getListBySendingTotalCount(startTime, endTime);
            resp.setCount(count);
            resp.setData(smsGatewayEntities);
            return resp;
        }

        List<BySending> smsGatewayEntities = bySendingRepo.ListBySending(startTime, endTime, PageRequest.of(page, limit, sort));
        Integer count = bySendingRepo.getListBySendingTotalCount(startTime, endTime);
        resp.setCount(count);
        resp.setData(smsGatewayEntities);
        return resp;
    }

    @Override
    public SmsGatewayResponseTimeResp findSmsGatewayResponseTime(SmsGwOrderTypeRespTimeReq req) {
        SmsGatewayResponseTimeResp resp = new SmsGatewayResponseTimeResp();
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
        String search = req.getSearch(); // fix search phone number

        if(offset.equals(0) && limit.equals(0)){
            if ( search.isEmpty()){
                List<ByResponseTime> smsGws = byResponseTimeRepo.ListByResponseTime(req.getOrderTypeMainID(), startTime, endTime);
                Integer count = byResponseTimeRepo.getListByResponseTimeTotalCount(req.getOrderTypeMainID(), startTime, endTime);
                resp.setCount(count);
                resp.setData(smsGws);
                return resp;
            }else {
                List<ByResponseTime> smsGws = byResponseTimeRepo.ListByResponseTimeSearch(req.getOrderTypeMainID(), startTime, endTime, search);
                Integer count = byResponseTimeRepo.getListByResponseTimeSearchTotalCount(req.getOrderTypeMainID(), startTime, endTime, search);
                resp.setCount(count);
                resp.setData(smsGws);
                return resp;
            }

        }

        if ( search.isEmpty()){
            List<ByResponseTime> smsGws = byResponseTimeRepo.ListByResponseTime(req.getOrderTypeMainID(), startTime, endTime, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = byResponseTimeRepo.getListByResponseTimeTotalCount(req.getOrderTypeMainID(), startTime, endTime);
            resp.setCount(count);
            resp.setData(smsGws);
            return resp;
        }else {
            List<ByResponseTime> smsGws = byResponseTimeRepo.ListByResponseTimeSearch(req.getOrderTypeMainID(), startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
            Integer count = byResponseTimeRepo.getListByResponseTimeSearchTotalCount(req.getOrderTypeMainID(), startTime, endTime, search);
            resp.setCount(count);
            resp.setData(smsGws);
            return resp;
        }
    }

    @Override
    public SmsGatewayResponseTimeReportResp findSmsGatewayResponseTimeReport(SmsGwListReq req) {
        SmsGatewayResponseTimeReportResp resp = new SmsGatewayResponseTimeReportResp();
        Timestamp startTime = Timestamp.valueOf(req.getStartTime());
        Timestamp endTime = Timestamp.valueOf(req.getEndTime());

        List<ByResponseReportTime> smsGws = byResponseTimeReportRepo.ListByResponseReportTime(startTime, endTime);
        
        // for (ByResponseReportTime responseTime : smsGws) {
        //     System.out.println("Receive Date: " + responseTime.getReceiveDate());
        //     System.out.println("Avg Response Time: " + responseTime.getAvgResponseTimePerDay());
        //     // Add similar logs for other fields
        //     System.out.println("Data Column Array: " + responseTime.getDataColumnArray());
        // }

        resp.setData(smsGws);
        return resp;
    }


    public PaginationDataResp findSmsGatewayOrderTypeAndStatus(SmsGwOrderTypeStatusReq req) {
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

        Long orderTypeMainID = req.getOrder_type_main_id();
        Integer isStatus = req.getIsStatus();

        if ( orderTypeMainID == 0){
            if (searchField.equals("phonenumber")){
                if(offset.equals(0) && limit.equals(0)){
                    List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.OdtStatusNoOrderIDSearchPhone(
                        isStatus,
                        search,
                        startTime,
                        endTime
                    );

                    Integer count = smsGatewayRepo.getOdtStatusNoOrderIDSearchPhoneTotalCount(
                        isStatus,
                        search,
                        startTime,
                        endTime
                    );
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }
                List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.OdtStatusNoOrderIDSearchPhone(
                    isStatus,
                    search,
                    startTime,
                    endTime,
                    PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
                );

                Integer count = smsGatewayRepo.getOdtStatusNoOrderIDSearchPhoneTotalCount(
                    isStatus,
                    search,
                    startTime,
                    endTime
                );
                resp.setCount(count);
                resp.setData(smsConditionEntities);
                return resp;
            }
            if(offset.equals(0) && limit.equals(0)){
                List<SmsGatewayEntity> smsGw = smsGatewayRepo.OdtStatusNoOrderID(
                isStatus,
                startTime,
                endTime);
                Integer count = smsGatewayRepo.getOdtStatusNoOrderIDTotalCount(
                    isStatus,
                    startTime,
                    endTime
                );
                resp.setCount(count);
                resp.setData(smsGw);
                return resp;
            }
            List<SmsGatewayEntity> smsGw = smsGatewayRepo.OdtStatusNoOrderID(
                isStatus,
                startTime,
                endTime,
                PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = smsGatewayRepo.getOdtStatusNoOrderIDTotalCount(
                isStatus,
                startTime,
                endTime
            );
            resp.setCount(count);
            resp.setData(smsGw);
            return resp;
        }

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
                }else if (searchField.equals("transaction_id")){
                    List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.OdtStatusTransaction_idFieldSearch(
                        orderTypeMainID,
                        isStatus,
                        search,
                        startTime,
                        endTime,
                        PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
                    );

                    Integer count = smsGatewayRepo.getOdtStatusTransaction_idFieldSearchTotalCount(
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

    public PaginationDataResp findSmsGatewayConditions(SmsGwConditionReq req) {
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

        Long conditionsID = req.getConditions_id();

        if ( search.isEmpty()){
            if(offset.equals(0) && limit.equals(0)){
                List<SmsGatewayEntity> smsGw = smsGatewayRepo.findSmsGatewayByConditionID(
                    conditionsID,
                    startTime,
                    endTime
                );
                Integer count = smsGatewayRepo.getSmsGatewayByConditionIDTotalCount(
                    conditionsID,
                    startTime,
                    endTime
                );
                resp.setCount(count);
                resp.setData(smsGw);
                return resp;
            }

            List<SmsGatewayEntity> smsGw = smsGatewayRepo.findSmsGatewayByConditionID(
                conditionsID,
                startTime,
                endTime,
                PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = smsGatewayRepo.getSmsGatewayByConditionIDTotalCount(
                conditionsID,
                startTime,
                endTime
            );
            resp.setCount(count);
            resp.setData(smsGw);
            return resp;
        }else {
            if( !req.getSearchField().isEmpty()){

                if (searchField.equals("phonenumber")){
                    if(offset.equals(0) && limit.equals(0)){
                        List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.ConditionPhoneFieldSearch(
                            conditionsID,
                            search,
                            startTime,
                            endTime
                        );

                        Integer count = smsGatewayRepo.getConditionPhoneFieldSearchTotalCount(
                            conditionsID,
                            search,
                            startTime,
                            endTime
                        );
                        resp.setCount(count);
                        resp.setData(smsConditionEntities);
                        return resp;
                    }
                    List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.ConditionPhoneFieldSearch(
                        conditionsID,
                        search,
                        startTime,
                        endTime,
                        PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
                    );

                    Integer count = smsGatewayRepo.getConditionPhoneFieldSearchTotalCount(
                        conditionsID,
                        search,
                        startTime,
                        endTime
                    );
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("smsmessage")){

                    if(offset.equals(0) && limit.equals(0)){
                        List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.ConditionSmsMsgFieldSearch(
                            conditionsID,
                            search,
                            startTime,
                            endTime
                        );

                        Integer count = smsGatewayRepo.getConditionSmsMsgFieldSearchTotalCount(
                            conditionsID,
                            search,
                            startTime,
                            endTime
                        );
                        resp.setCount(count);
                        resp.setData(smsConditionEntities);
                        return resp;
                    }

                    List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.ConditionSmsMsgFieldSearch(
                        conditionsID,
                        search,
                        startTime,
                        endTime,
                        PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
                    );

                    Integer count = smsGatewayRepo.getConditionSmsMsgFieldSearchTotalCount(
                        conditionsID,
                        search,
                        startTime,
                        endTime
                    );
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("refid")){
                    if(offset.equals(0) && limit.equals(0)){
                        List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.ConditionRefIdFieldSearch(
                            conditionsID,
                            search,
                            startTime,
                            endTime
                        );

                        Integer count = smsGatewayRepo.getConditionRefIdFieldSearchTotalCount(
                            conditionsID,
                            search,
                            startTime,
                            endTime
                        );
                        resp.setCount(count);
                        resp.setData(smsConditionEntities);
                        return resp;
                    }

                    List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.ConditionRefIdFieldSearch(
                        conditionsID,
                        search,
                        startTime,
                        endTime,
                        PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
                    );

                    Integer count = smsGatewayRepo.getConditionRefIdFieldSearchTotalCount(
                        conditionsID,
                        search,
                        startTime,
                        endTime
                    );
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }else if (searchField.equals("transaction_id")){
                    if(offset.equals(0) && limit.equals(0)){
                        List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.ConditionTransaction_idFieldSearch(
                            conditionsID,
                            search,
                            startTime,
                            endTime
                        );

                        Integer count = smsGatewayRepo.getConditionTransaction_idFieldSearchTotalCount(
                            conditionsID,
                            search,
                            startTime,
                            endTime
                        );
                        resp.setCount(count);
                        resp.setData(smsConditionEntities);
                        return resp;
                    }

                    List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.ConditionTransaction_idFieldSearch(
                        conditionsID,
                        search,
                        startTime,
                        endTime,
                        PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
                    );

                    Integer count = smsGatewayRepo.getConditionTransaction_idFieldSearchTotalCount(
                        conditionsID,
                        search,
                        startTime,
                        endTime
                    );
                    resp.setCount(count);
                    resp.setData(smsConditionEntities);
                    return resp;
                }
            }


            if(offset.equals(0) && limit.equals(0)){
                List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.findSmsGatewayByConditionAllSearch(
                    conditionsID,
                    search,
                    startTime,
                    endTime
                );
                Integer count = smsGatewayRepo.getSmsGatewayByConditionAllSearchTotalCount(
                    conditionsID,
                    search,
                    startTime,
                    endTime
                );
                resp.setCount(count);
                resp.setData(smsConditionEntities);
                return resp;
            }
            List<SmsGatewayEntity> smsConditionEntities = smsGatewayRepo.findSmsGatewayByConditionAllSearch(
                conditionsID,
                search,
                startTime,
                endTime,
                PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName )
            );
            Integer count = smsGatewayRepo.getSmsGatewayByConditionAllSearchTotalCount(
                conditionsID,
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
            } else if(searchField.equals("phonenumber")){
                List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendPhonenumberTypeField(startTime, endTime, search);
                Integer count = listSmsGatewayRepo.getListSendPhonenumberFieldTotalCount(startTime, endTime, search);
                resp.setCount(count);
                resp.setData(smsGws);
                return resp;
            }
            List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendSmsGwAllSearch(startTime, endTime, search);
            Integer count = listSmsGatewayRepo.getListSendSmsGwAllSearchTotalCount(startTime, endTime, search);
            resp.setCount(count);
            resp.setData(smsGws);
            return resp;

        }

        if ( search.isEmpty()){
            List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendSmsGw(startTime, endTime, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = listSmsGatewayRepo.getListSendSmsGwTotalCount(startTime, endTime);
            Integer countSend = listSmsGatewayRepo.getListSendSmsGwStatusSendTotalCount(startTime, endTime);
            Integer countUnSend = listSmsGatewayRepo.getListSendSmsGwStatusUnSendTotalCount(startTime, endTime);
            resp.setCountUnSend(countUnSend);
            resp.setCountSend(countSend);
            resp.setCount(count);
            resp.setData(smsGws);
            return resp;
        }else {
            if( !req.getSearchField().isEmpty()){
                if(searchField.equals("transaction_id")){
                    List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendTransactionIDTypeField(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsGatewayRepo.getListSendTransactionIDFieldTotalCount(startTime, endTime, search);
                    Integer countSend = listSmsGatewayRepo.getListSendTransactionIDFieldStatusSendTotalCount(startTime, endTime, search);
                    Integer countUnSend = listSmsGatewayRepo.getListSendTransactionIDFieldStatusUnSendTotalCount(startTime, endTime, search);
                    resp.setCountUnSend(countUnSend);
                    resp.setCountSend(countSend);
                    resp.setCount(count);
                    resp.setData(smsGws);
                    return resp;
                } else if(searchField.equals("phonenumber")){
                    List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendPhonenumberTypeField(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsGatewayRepo.getListSendPhonenumberFieldTotalCount(startTime, endTime, search);
                    Integer countSend = listSmsGatewayRepo.getListSendPhonenumberFieldStatusSendTotalCount(startTime, endTime, search);
                    Integer countUnSend = listSmsGatewayRepo.getListSendPhonenumberFieldStatusUnSendTotalCount(startTime, endTime, search);
                    resp.setCountUnSend(countUnSend);
                    resp.setCountSend(countSend);
                    resp.setCount(count);
                    resp.setData(smsGws);
                    return resp;
                } else if(searchField.equals("smsmessage")){
                    List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendSmsMsgField(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsGatewayRepo.getListSendSmsMsgFieldTotalCount(startTime, endTime, search);
                    Integer countSend = listSmsGatewayRepo.getListSendSmsMsgFieldStatusSendTotalCount(startTime, endTime, search);
                    Integer countUnSend = listSmsGatewayRepo.getListSendSmsMsgFieldStatusUnSendTotalCount(startTime, endTime, search);
                    resp.setCountUnSend(countUnSend);
                    resp.setCountSend(countSend);
                    resp.setCount(count);
                    resp.setData(smsGws);
                    return resp;
                } else if(searchField.equals("refid")){
                    List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendRefIdField(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsGatewayRepo.getListSendRefIdFieldTotalCount(startTime, endTime, search);
                    Integer countSend = listSmsGatewayRepo.getListSendRefIdFieldStatusSendTotalCount(startTime, endTime, search);
                    Integer countUnSend = listSmsGatewayRepo.getListSendRefIdFieldStatusUnSendTotalCount(startTime, endTime, search);
                    resp.setCountUnSend(countUnSend);
                    resp.setCountSend(countSend);
                    resp.setCount(count);
                    resp.setData(smsGws);
                    return resp;
                } else if(searchField.equals("ordertype")){
                    List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendOrderTypeField(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
                    Integer count = listSmsGatewayRepo.getListSendOrderTypeFieldTotalCount(startTime, endTime, search);
                    Integer countSend = listSmsGatewayRepo.getListSendOrderTypeFieldStatusSendTotalCount(startTime, endTime, search);
                    Integer countUnSend = listSmsGatewayRepo.getListSendOrderTypeFieldStatusUnSendTotalCount(startTime, endTime, search);
                    resp.setCountUnSend(countUnSend);
                    resp.setCountSend(countSend);
                    resp.setCount(count);
                    resp.setData(smsGws);
                    return resp;
                }
            }
            // System.out.println("3");
            List<ListSmsGateway> smsGws = listSmsGatewayRepo.ListSendSmsGwAllSearch(startTime, endTime, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName));
            Integer count = listSmsGatewayRepo.getListSendSmsGwAllSearchTotalCount(startTime, endTime, search);
            Integer countSend = listSmsGatewayRepo.getListSendSmsGwAllStatusSendTotalCount(startTime, endTime, search);
            Integer countUnSend = listSmsGatewayRepo.getListSendSmsGwAllStatusUnSendTotalCount(startTime, endTime, search);
            resp.setCountUnSend(countUnSend);
            resp.setCountSend(countSend);
            resp.setCount(count);
            resp.setData(smsGws);
            return resp;
        }
    }
    
}
