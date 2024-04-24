package com.nt.red_sms_api.repo.view.sms_gateway;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.BySending;
import com.nt.red_sms_api.entity.view.sms_gateway.ListSmsGateway;

import java.sql.Timestamp;
import java.util.List;

public interface ListSmsGatewayRepo extends JpaRepository<SmsGatewayEntity,Long> {
    
    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status 
                    FROM sms_gateway smsgw 
                    WHERE smsgw.send_date BETWEEN :start_time AND :end_time
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendSmsGw(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    WHERE smsgw.send_date BETWEEN :start_time AND :end_time
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsGwTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime
    );


    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status 
                    FROM sms_gateway smsgw 
                    WHERE smsgw.send_date BETWEEN :start_time AND :end_time
                    AND ( smsgw.TRANSACTION_ID like %:search% OR smsgw.phonenumber like %:search% OR smsgw.ordertype like %:search% OR smsgw.is_status like %:search% )
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendSmsGwAllSearch(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    WHERE smsgw.send_date BETWEEN :start_time AND :end_time 
                    AND ( smsgw.TRANSACTION_ID like %:search% OR smsgw.phonenumber like %:search% OR smsgw.ordertype like %:search% OR smsgw.is_status like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsGwAllSearchTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status 
                    FROM sms_gateway smsgw 
                    WHERE smsgw.send_date BETWEEN :start_time AND :end_time 
                    AND ( :search_field like %:search% ) 
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendSmsGwSearch(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search_field")String searchField,
        @Param(value = "search")String search,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    WHERE smsgw.send_date BETWEEN :start_time AND :end_time 
                    AND ( :search_field like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsGwSearchTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search_field")String searchField,
        @Param(value = "search")String search
    );

}
    