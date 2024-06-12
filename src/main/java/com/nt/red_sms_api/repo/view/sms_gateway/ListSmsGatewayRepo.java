package com.nt.red_sms_api.repo.view.sms_gateway;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.ListSmsGateway;

public interface ListSmsGatewayRepo extends JpaRepository<SmsGatewayEntity,Long> {
    
    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status, conf.refid 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
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
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsGwTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 1 
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsGwStatusSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 3 
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsGwStatusUnSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime
    );

    

    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status , conf.refid 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search% OR smsgw.phonenumber like %:search% OR UPPER(smsgw.ordertype) || LOWER(smsgw.ordertype) like %:search% OR smsgw.is_status like %:search% )
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
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search% OR smsgw.phonenumber like %:search% OR UPPER(smsgw.ordertype) || LOWER(smsgw.ordertype) like %:search% OR smsgw.is_status like %:search% )
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsGwAllSearchTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id 
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 1 
                    AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search% OR smsgw.phonenumber like %:search% OR UPPER(smsgw.ordertype) || LOWER(smsgw.ordertype) like %:search% OR smsgw.is_status like %:search% )
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsGwAllStatusSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id 
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 3 
                    AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search% OR smsgw.phonenumber like %:search% OR UPPER(smsgw.ordertype) || LOWER(smsgw.ordertype) like %:search% OR smsgw.is_status like %:search% )
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsGwAllStatusUnSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

    //// no page
    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status , conf.refid 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search% OR smsgw.phonenumber like %:search% OR UPPER(smsgw.ordertype) || LOWER(smsgw.ordertype) like %:search% OR smsgw.is_status like %:search% ) 
                    ORDER BY smsgw.send_date DESC
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendSmsGwAllSearch(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

    // smsmessage
    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status , conf.refid 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( smsmessage like %:search% ) 
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendSmsMsgField(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( smsmessage like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsMsgFieldTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 1 
                    AND ( smsmessage like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsMsgFieldStatusSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 3 
                    AND ( smsmessage like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendSmsMsgFieldStatusUnSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    // refID
    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status , conf.refid 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( UPPER(conf.refid) || LOWER(conf.refid) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendRefIdField(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( UPPER(conf.refid) || LOWER(conf.refid) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendRefIdFieldTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 1 
                    AND ( UPPER(conf.refid) || LOWER(conf.refid) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendRefIdFieldStatusSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 3 
                    AND ( UPPER(conf.refid) || LOWER(conf.refid) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendRefIdFieldStatusUnSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );


    // ordertype
    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status , conf.refid 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( UPPER(conf.ordertype) || LOWER(conf.ordertype) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendOrderTypeField(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( UPPER(conf.ordertype) || LOWER(conf.ordertype) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendOrderTypeFieldTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 1
                    AND ( UPPER(conf.ordertype) || LOWER(conf.ordertype) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendOrderTypeFieldStatusSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 3
                    AND ( UPPER(conf.ordertype) || LOWER(conf.ordertype) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendOrderTypeFieldStatusUnSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    // ordertype no limit
    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status , conf.refid 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( UPPER(conf.ordertype) || LOWER(conf.ordertype) like %:search% ) 
                    ORDER BY smsgw.send_DATE DESC
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendOrderTypeField(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

    

    // transaction_ID
    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status , conf.refid 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendTransactionIDTypeField(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendTransactionIDFieldTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 1 
                    AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendTransactionIDFieldStatusSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 3 
                    AND ( UPPER(smsgw.TRANSACTION_ID) || LOWER(smsgw.TRANSACTION_ID) like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendTransactionIDFieldStatusUnSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );
    

    //phonenumber
    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status , conf.refid 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( smsgw.phonenumber like %:search% ) 
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendPhonenumberTypeField(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( smsgw.phonenumber like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendPhonenumberFieldTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 1 
                    AND ( smsgw.phonenumber like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendPhonenumberFieldStatusSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND smsgw.is_status = 3 
                    AND ( smsgw.phonenumber like %:search% ) 
                    """
                    ,nativeQuery = true)
    public Integer getListSendPhonenumberFieldStatusUnSendTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,  
        @Param(value = "search")String search
    );

    //phonenumber no page
    @Query(value =  """
                    SELECT smsgw.GID, smsgw.TRANSACTION_ID, smsgw.send_date, smsgw.phonenumber, smsgw.ordertype, smsgw.is_status , conf.refid 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.CREATED_DATE BETWEEN :start_time AND :end_time AND (smsgw.is_status = 1 OR smsgw.is_status = 3) 
                    AND ( smsgw.phonenumber like %:search% ) 
                    ORDER BY smsgw.send_date DESC
                    """
                    ,nativeQuery = true)
    public List<ListSmsGateway> ListSendPhonenumberTypeField(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

}
    