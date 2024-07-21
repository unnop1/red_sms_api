package com.nt.red_sms_api.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;

public interface SmsGatewayRepo extends JpaRepository<SmsGatewayEntity,Long> {

    /* Sendings */

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.ORDER_TYPE_MAINID  = conf.ORDER_TYPE_MAINID
                WHERE (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4)
                AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) || UPPER(smsgw.ORDERTYPE) || LOWER(smsgw.ORDERTYPE) || smsgw.PHONENUMBER like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getSmsGatewayReportSendingsAllSearchTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        @Param(value = "search")String search
    );

    @Query(value = """
                SELECT smsgw.* , conf.DATE_START , conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.ORDER_TYPE_MAINID  = conf.ORDER_TYPE_MAINID
                WHERE (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4)
                AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) || UPPER(smsgw.ORDERTYPE) || LOWER(smsgw.ORDERTYPE) || smsgw.PHONENUMBER like %:search% )
                AND smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time 
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayReportSendingsSearchAll(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime, 
        @Param(value = "search")String search,
        Pageable pageable
    );

    @Query(value = """
                SELECT smsgw.* , conf.DATE_START , conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.ORDER_TYPE_MAINID  = conf.ORDER_TYPE_MAINID
                WHERE (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4)
                AND smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time 
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayReportSendings(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime, 
        Pageable pageable
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.ORDER_TYPE_MAINID  = conf.ORDER_TYPE_MAINID
                WHERE (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4)
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getSmsGatewayReportSendingsTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


    @Query(value = """
                SELECT smsgw.* , conf.DATE_START , conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.ORDER_TYPE_MAINID  = conf.ORDER_TYPE_MAINID
                WHERE (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4)
                AND smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time 
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayReportSendings(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    /* ORDER TYPE ID */

    @Query(value = """
                SELECT smsgw.* , conf.DATE_START , conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.ORDER_TYPE_MAINID  = conf.ORDER_TYPE_MAINID
                WHERE smsgw.ORDER_TYPE_MAINID=:ordertype_main_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3)
                AND smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time 
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayReportByOrderTypeID(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime, 
        Pageable pageable
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.ORDER_TYPE_MAINID  = conf.ORDER_TYPE_MAINID
                WHERE smsgw.ORDER_TYPE_MAINID=:ordertype_main_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3)
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getSmsGatewayReportByOrderTypeIDTotalCount(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


    @Query(value = """
                SELECT smsgw.* , conf.DATE_START , conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.ORDER_TYPE_MAINID  = conf.ORDER_TYPE_MAINID
                WHERE smsgw.ORDER_TYPE_MAINID=:ordertype_main_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3)
                AND smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time 
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayReportByOrderTypeID(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


    /* CONDITIONS ID */
    @Query(value = """
                SELECT smsgw.* , conf.DATE_START , conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time 
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayByConditionID(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime, 
        Pageable pageable
    );

    @Query(value = """
                SELECT smsgw.* , conf.DATE_START , conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time 
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayByConditionID(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getSmsGatewayByConditionIDTotalCount(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    // phonenumber
    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND ( smsgw.PHONENUMBER like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> ConditionPhoneFieldSearch(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND ( smsgw.PHONENUMBER like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> ConditionPhoneFieldSearch(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND ( smsgw.PHONENUMBER like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getConditionPhoneFieldSearchTotalCount(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    // smsmessage
    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND ( smsgw.SMSMESSAGE like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> ConditionSmsMsgFieldSearch(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND ( smsgw.SMSMESSAGE like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> ConditionSmsMsgFieldSearch(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND ( SMSMESSAGE like %:search% )
                AND ( RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getConditionSmsMsgFieldSearchTotalCount(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    // refid
    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND conf.REFID=:search
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> ConditionRefIdFieldSearch(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND conf.REFID=:search
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> ConditionRefIdFieldSearch(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND conf.REFID=:search
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getConditionRefIdFieldSearchTotalCount(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    // transaction_id
    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search%
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> ConditionTransaction_idFieldSearch(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search%
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> ConditionTransaction_idFieldSearch(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search%
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getConditionTransaction_idFieldSearchTotalCount(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END  FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id 
                AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) || UPPER(conf.REFID) || LOWER(conf.REFID) || smsgw.PHONENUMBER || smsgw.SMSMESSAGE like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayByConditionAllSearch(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        // @Param(value = "search_date_field") String searchDateField,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END  FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id 
                AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) || UPPER(conf.REFID) || LOWER(conf.REFID) || smsgw.PHONENUMBER || smsgw.SMSMESSAGE like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayByConditionAllSearch(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        // @Param(value = "search_date_field") String searchDateField,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.CONFIG_CONDITIONS_ID=:conditions_id AND (smsgw.IS_STATUS = 1 OR smsgw.IS_STATUS = 3 OR smsgw.IS_STATUS = 2 OR smsgw.IS_STATUS = 4 )
                AND ( UPPER(conf.REFID) || LOWER(conf.REFID) || smsgw.PHONENUMBER || smsgw.SMSMESSAGE like %:search% )
                AND ( receive_date BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getSmsGatewayByConditionAllSearchTotalCount(
        @Param(value = "conditions_id") Long conditionsID,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    /* ORDERTYPE ID */
    @Query(value = """
                SELECT smsgw.* , conf.DATE_START , conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status 
                AND smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time 
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
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status 
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
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
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END  FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status
                AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) || UPPER(conf.REFID) || LOWER(conf.REFID) || smsgw.PHONENUMBER || smsgw.SMSMESSAGE like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
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
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status
                AND ( UPPER(conf.REFID) || LOWER(conf.REFID) || smsgw.PHONENUMBER || smsgw.SMSMESSAGE like %:search% )
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
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status
                AND ( smsgw.PHONENUMBER like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
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
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status
                AND ( smsgw.PHONENUMBER like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
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
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status
                AND ( smsgw.SMSMESSAGE like %:search% )
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
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
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status
                AND ( SMSMESSAGE like %:search% )
                AND ( RECEIVE_DATE BETWEEN :start_time AND :end_time )
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
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status
                AND conf.REFID=:search
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
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
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status
                AND conf.REFID=:search
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
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
                    SELECT smsgw.* , conf.DATE_START , conf.DATE_END
                    FROM sms_gateway smsgw 
                    LEFT JOIN config_conditions conf 
                    ON conf.CONDITIONS_ID = smsgw.CONFIG_CONDITIONS_ID
                    WHERE smsgw.GID=:gid
                    """,
                    nativeQuery = true)
    public SmsGatewayEntity GetSmsGatewayInfo(@Param(value = "gid") Long gid);

    /// no ordertype_main_id
    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.IS_STATUS=:is_status 
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> OdtStatusNoOrderID(
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                SELECT COUNT(*) FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                    WHERE smsgw.IS_STATUS=:is_status 
                    AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getOdtStatusNoOrderIDTotalCount(
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    /// no ordertype_main_id /// no page
    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.IS_STATUS=:is_status 
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> OdtStatusNoOrderID(
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    /// no ordertype_main_id search phonenumber
    @Query(value = """ 
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.IS_STATUS=:is_status 
                AND ( smsgw.PHONENUMBER like %:search% ) 
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> OdtStatusNoOrderIDSearchPhone(
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
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                    WHERE smsgw.IS_STATUS=:is_status 
                    AND ( smsgw.PHONENUMBER like %:search% ) 
                    AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getOdtStatusNoOrderIDSearchPhoneTotalCount(
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    /// no ordertype_main_id search phonenumber // no page
    @Query(value = """ 
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.IS_STATUS=:is_status 
                AND ( smsgw.PHONENUMBER like %:search% ) 
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> OdtStatusNoOrderIDSearchPhone(
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    // transaction_id
    @Query(value = """
                SELECT smsgw.*, conf.DATE_START, conf.DATE_END FROM sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status
                AND UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search%
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public List<SmsGatewayEntity> OdtStatusTransaction_idFieldSearch(
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
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.CONDITIONS_ID
                WHERE smsgw.ORDER_TYPE_MAINID=:order_type_main_id 
                AND smsgw.IS_STATUS=:is_status
                AND UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search%
                AND ( smsgw.RECEIVE_DATE BETWEEN :start_time AND :end_time )
                """
                , nativeQuery = true)
    public Integer getOdtStatusTransaction_idFieldSearchTotalCount(
        @Param(value = "order_type_main_id") Long orderTypeMainID,
        @Param(value = "is_status") Integer isStatus,
        @Param(value = "search") String search,
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );
}
