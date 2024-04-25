package com.nt.red_sms_api.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsGatewayRepo extends JpaRepository<SmsGatewayEntity,Long> {
    @Query(value = """
                SELECT * FROM sms_gateway 
                WHERE order_type_mainID=:order_type_main_id 
                AND Is_Status=:is_status 
                AND send_date BETWEEN :start_time AND :end_time 
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayByOrderTypeAndStatus(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        // @Param(value = "search_date_field") String searchDateField,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime, 
        Pageable pageable
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway 
                WHERE order_type_mainID=:order_type_main_id 
                AND Is_Status=:is_status 
                AND ( send_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getSmsGatewayByOrderTypeAndStatusTotalCount(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        // @Param(value = "search_date_field") String searchDateField,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


    @Query(value = """
                SELECT *  FROM sms_gateway
                WHERE order_type_mainID=:order_type_main_id 
                AND Is_Status=:is_status
                AND ( config_conditions_id like %:search% 
                    OR phonenumber like %:search% 
                    OR smsmessage like %:search% 
                )
                AND ( send_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayByOrderTypeAndStatusAllSearch(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        // @Param(value = "search_date_field") String searchDateField,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway 
                WHERE order_type_mainID=:order_type_main_id 
                AND Is_Status=:is_status
                AND ( config_conditions_id like %:search% 
                    OR phonenumber like %:search% 
                    OR smsmessage like %:search% 
                )
                AND ( send_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getSmsGatewayByOrderTypeAndStatusAllSearchTotalCount(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    // phonenumber
    @Query(value = """
                SELECT *  FROM sms_gateway
                WHERE order_type_mainID=:order_type_main_id 
                AND Is_Status=:is_status
                AND ( phonenumber like %:search% )
                AND ( send_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> OdtStatusPhoneFieldSearch(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway 
                WHERE order_type_mainID=:order_type_main_id 
                AND Is_Status=:is_status
                AND ( phonenumber like %:search% )
                AND ( send_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getOdtStatusPhoneFieldSearchTotalCount(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    // smsmessage
    @Query(value = """
                SELECT *  FROM sms_gateway
                WHERE order_type_mainID=:order_type_main_id 
                AND Is_Status=:is_status
                AND ( smsmessage like %:search% )
                AND ( send_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> OdtStatusSmsMsgFieldSearch(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway 
                WHERE order_type_mainID=:order_type_main_id 
                AND Is_Status=:is_status
                AND ( smsmessage like %:search% )
                AND ( send_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getOdtStatusSmsMsgFieldSearchTotalCount(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    // config_conditions_id
    @Query(value = """
                SELECT *  FROM sms_gateway
                WHERE order_type_mainID=:order_type_main_id 
                AND Is_Status=:is_status
                AND ( config_conditions_id like %:search% )
                AND ( send_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> OdtStatusConfIdFieldSearch(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway 
                WHERE order_type_mainID=:order_type_main_id 
                AND Is_Status=:is_status
                AND ( config_conditions_id like %:search% )
                AND ( send_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getOdtStatusConfIdFieldSearchTotalCount(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


    @Query(value = "SELECT * FROM sms_gateway WHERE gid=:gid ",nativeQuery = true)
    public SmsGatewayEntity GetSmsGatewayInfo(@Param(value = "gid") Long gid);
}
