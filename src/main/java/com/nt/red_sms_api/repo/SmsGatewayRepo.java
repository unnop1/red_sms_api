package com.nt.red_sms_api.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;

public interface SmsGatewayRepo extends JpaRepository<SmsGatewayEntity,Long> {
    @Query(value = """
                SELECT smsgw.*, conf.refid , conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                WHERE smsgw.order_type_mainID=:order_type_main_id 
                AND smsgw.Is_Status=:is_status 
                AND smsgw.receive_date BETWEEN :start_time AND :end_time 
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
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                WHERE smsgw.order_type_mainID=:order_type_main_id 
                AND smsgw.Is_Status=:is_status 
                AND ( smsgw.receive_date BETWEEN :start_time AND :end_time )
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
                SELECT smsgw.*, conf.refid  FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                WHERE smsgw.order_type_mainID=:order_type_main_id 
                AND smsgw.Is_Status=:is_status
                AND ( smsgw.config_conditions_id like %:search% 
                    OR smsgw.phonenumber like %:search% 
                    OR smsgw.smsmessage like %:search% 
                )
                AND ( smsgw.receive_date BETWEEN :start_time AND :end_time )
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
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                WHERE smsgw.order_type_mainID=:order_type_main_id 
                AND smsgw.Is_Status=:is_status
                AND ( smsgw.config_conditions_id like %:search% 
                    OR smsgw.phonenumber like %:search% 
                    OR smsgw.smsmessage like %:search% 
                )
                AND ( receive_date BETWEEN :start_time AND :end_time )
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
                SELECT smsgw.*, conf.refid FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                WHERE smsgw.order_type_mainID=:order_type_main_id 
                AND smsgw.Is_Status=:is_status
                AND ( smsgw.phonenumber like %:search% )
                AND ( smsgw.receive_date BETWEEN :start_time AND :end_time )
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
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                WHERE smsgw.order_type_mainID=:order_type_main_id 
                AND smsgw.Is_Status=:is_status
                AND ( smsgw.phonenumber like %:search% )
                AND ( smsgw.receive_date BETWEEN :start_time AND :end_time )
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
                SELECT smsgw.*, conf.refid FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                WHERE smsgw.order_type_mainID=:order_type_main_id 
                AND smsgw.Is_Status=:is_status
                AND ( smsgw.smsmessage like %:search% )
                AND ( smsgw.receive_date BETWEEN :start_time AND :end_time )
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
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                WHERE smsgw.order_type_mainID=:order_type_main_id 
                AND smsgw.Is_Status=:is_status
                AND ( smsmessage like %:search% )
                AND ( receive_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getOdtStatusSmsMsgFieldSearchTotalCount(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    // refid
    @Query(value = """
                SELECT smsgw.*, conf.refid FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                WHERE smsgw.order_type_mainID=:order_type_main_id 
                AND smsgw.Is_Status=:is_status
                AND conf.refid=:search
                AND ( smsgw.receive_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> OdtStatusRefIdFieldSearch(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                WHERE smsgw.order_type_mainID=:order_type_main_id 
                AND smsgw.Is_Status=:is_status
                AND conf.refid=:search
                AND ( smsgw.receive_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getOdtStatusRefIdFieldSearchTotalCount(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


    @Query(value = """
                    SELECT smsgw.*, conf.refid , conf.DATE_START , conf.DATE_END
                    FROM sms_gateway smsgw 
                    LEFT JOIN config_conditions conf 
                    ON conf.conditions_id = smsgw.CONFIG_CONDITIONS_ID
                    WHERE smsgw.gid=:gid
                    """,
                    nativeQuery = true)
    public SmsGatewayEntity GetSmsGatewayInfo(@Param(value = "gid") Long gid);
}
