
package com.nt.red_sms_api.repo.view.sms_gateway;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.date.ByResponseTime;

public interface ByResponseTimeRepo extends JpaRepository<SmsGatewayEntity,Long> {
    /* BY DATE */
    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD-MON-YYYY') AS DATE_ONLY,
                    smsgw.GID,
                    smsgw.config_conditions_id,
                    smsgw.order_type_mainid,
                    smsgw.transaction_id,
                    smsgw.phonenumber,
                    smsgw.ordertype,
                    smsgw.receive_date,
                    smsgw.send_date,
                    smsgw.is_status,
                    conf.refid,
                    SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                        + extract ( hour from (send_date - receive_date) )*3600 
                        + extract ( minute from (send_date - receive_date) )*60 
                        + extract ( second from (send_date - receive_date) )
                    ) as response_time
                    FROM sms_gateway smsgw
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    GROUP BY GID, TRUNC(smsgw.created_date, 'DD'), smsgw.order_type_mainid, config_conditions_id, smsgw.transaction_id, smsgw.phonenumber, smsgw.ordertype, smsgw.receive_date, smsgw.send_date, smsgw.is_status, conf.refid 
                    ORDER BY smsgw.receive_date desc 
                    """
                    ,nativeQuery = true)
    public List<ByResponseTime> ListByResponseTime(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        Pageable pageable
    );

    //// no page
    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD-MON-YYYY') AS DATE_ONLY,
                    smsgw.GID,
                    smsgw.config_conditions_id,
                    smsgw.order_type_mainid,
                    smsgw.transaction_id,
                    smsgw.phonenumber,
                    smsgw.ordertype,
                    smsgw.receive_date,
                    smsgw.send_date,
                    smsgw.is_status,
                    conf.refid,
                    SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                        + extract ( hour from (send_date - receive_date) )*3600 
                        + extract ( minute from (send_date - receive_date) )*60 
                        + extract ( second from (send_date - receive_date) )
                    ) as response_time
                    FROM sms_gateway smsgw
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    GROUP BY GID, TRUNC(smsgw.created_date, 'DD'), smsgw.order_type_mainid, config_conditions_id, smsgw.transaction_id, smsgw.phonenumber, smsgw.ordertype, smsgw.receive_date, smsgw.send_date, smsgw.is_status, conf.refid 
                    ORDER BY smsgw.receive_date desc 
                    """
                    ,nativeQuery = true)
    public List<ByResponseTime> ListByResponseTime(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.config_conditions_id = conf.conditions_id
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    """
                    ,nativeQuery = true)
    public Integer getListByResponseTimeTotalCount(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime
    );


    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD-MON-YYYY') AS DATE_ONLY,
                    smsgw.GID,
                    smsgw.config_conditions_id,
                    smsgw.order_type_mainid,
                    smsgw.transaction_id,
                    smsgw.phonenumber,
                    smsgw.ordertype,
                    smsgw.receive_date,
                    smsgw.send_date,
                    smsgw.is_status,
                    conf.refid,
                    SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                        + extract ( hour from (send_date - receive_date) )*3600 
                        + extract ( minute from (send_date - receive_date) )*60 
                        + extract ( second from (send_date - receive_date) )
                    ) as response_time 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time 
                    AND smsgw.phonenumber like %:search% AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    GROUP BY GID, TRUNC(smsgw.created_date, 'DD'), smsgw.order_type_mainid, config_conditions_id, smsgw.transaction_id, smsgw.phonenumber, smsgw.ordertype, smsgw.receive_date, smsgw.send_date, smsgw.is_status, conf.refid 
                    """
                    ,nativeQuery = true)
    public List<ByResponseTime> ListByResponseTimeSearch(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search,
        Pageable pageable
    );

    //// Search phonenumber #no page
    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD-MON-YYYY') AS DATE_ONLY,
                    smsgw.GID,
                    smsgw.config_conditions_id,
                    smsgw.order_type_mainid,
                    smsgw.transaction_id,
                    smsgw.phonenumber,
                    smsgw.ordertype,
                    smsgw.receive_date,
                    smsgw.send_date,
                    smsgw.is_status,
                    conf.refid,
                    SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                        + extract ( hour from (send_date - receive_date) )*3600 
                        + extract ( minute from (send_date - receive_date) )*60 
                        + extract ( second from (send_date - receive_date) )
                    ) as response_time 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time 
                    AND smsgw.phonenumber like %:search% AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    GROUP BY GID, TRUNC(smsgw.created_date, 'DD'), smsgw.order_type_mainid, config_conditions_id, smsgw.transaction_id, smsgw.phonenumber, smsgw.ordertype, smsgw.receive_date, smsgw.send_date, smsgw.is_status, conf.refid 
                    ORDER BY smsgw.receive_date desc 
                    """
                    ,nativeQuery = true)
    public List<ByResponseTime> ListByResponseTimeSearch(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*)  
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time 
                    AND smsgw.phonenumber like %:search% AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    """
                    ,nativeQuery = true)
    public Integer getListByResponseTimeSearchTotalCount(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );



    /* BY MONTH */
    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY') AS DATE_ONLY,
                    smsgw.GID,
                    smsgw.config_conditions_id,
                    smsgw.order_type_mainid,
                    smsgw.transaction_id,
                    smsgw.phonenumber,
                    smsgw.ordertype,
                    smsgw.receive_date,
                    smsgw.send_date,
                    smsgw.is_status,
                    conf.refid,
                    SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                        + extract ( hour from (send_date - receive_date) )*3600 
                        + extract ( minute from (send_date - receive_date) )*60 
                        + extract ( second from (send_date - receive_date) )
                    ) as response_time
                    FROM sms_gateway smsgw
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    GROUP BY GID, TRUNC(smsgw.created_date, 'MONTH'), smsgw.order_type_mainid, config_conditions_id, smsgw.transaction_id, smsgw.phonenumber, smsgw.ordertype, smsgw.receive_date, smsgw.send_date, smsgw.is_status, conf.refid 
                    ORDER BY smsgw.receive_date desc 
                    """
                    ,nativeQuery = true)
    public List<ByResponseTime> ListByResponseTimeMonth(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        Pageable pageable
    );

    //// no page
    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY') AS DATE_ONLY,
                    smsgw.GID,
                    smsgw.config_conditions_id,
                    smsgw.order_type_mainid,
                    smsgw.transaction_id,
                    smsgw.phonenumber,
                    smsgw.ordertype,
                    smsgw.receive_date,
                    smsgw.send_date,
                    smsgw.is_status,
                    conf.refid,
                    SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                        + extract ( hour from (send_date - receive_date) )*3600 
                        + extract ( minute from (send_date - receive_date) )*60 
                        + extract ( second from (send_date - receive_date) )
                    ) as response_time
                    FROM sms_gateway smsgw
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    GROUP BY GID, TRUNC(smsgw.created_date, 'MONTH'), smsgw.order_type_mainid, config_conditions_id, smsgw.transaction_id, smsgw.phonenumber, smsgw.ordertype, smsgw.receive_date, smsgw.send_date, smsgw.is_status, conf.refid 
                    ORDER BY smsgw.receive_date desc 
                    """
                    ,nativeQuery = true)
    public List<ByResponseTime> ListByResponseTimeMonth(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime
    );

    @Query(value = """
                    SELECT COUNT(*) 
                    FROM sms_gateway smsgw
                    LEFT JOIN 
                    config_conditions conf
                    ON smsgw.config_conditions_id = conf.conditions_id
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    """
                    ,nativeQuery = true)
    public Integer getListByResponseTimeMonthTotalCount(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime
    );


    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY') AS DATE_ONLY,
                    smsgw.GID,
                    smsgw.config_conditions_id,
                    smsgw.order_type_mainid,
                    smsgw.transaction_id,
                    smsgw.phonenumber,
                    smsgw.ordertype,
                    smsgw.receive_date,
                    smsgw.send_date,
                    smsgw.is_status,
                    conf.refid,
                    SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                        + extract ( hour from (send_date - receive_date) )*3600 
                        + extract ( minute from (send_date - receive_date) )*60 
                        + extract ( second from (send_date - receive_date) )
                    ) as response_time 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time 
                    AND smsgw.phonenumber like %:search% AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    GROUP BY GID, TRUNC(smsgw.created_date, 'MONTH'), smsgw.order_type_mainid, config_conditions_id, smsgw.transaction_id, smsgw.phonenumber, smsgw.ordertype, smsgw.receive_date, smsgw.send_date, smsgw.is_status, conf.refid 
                    """
                    ,nativeQuery = true)
    public List<ByResponseTime> ListByResponseTimeMonthSearch(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search,
        Pageable pageable
    );

    //// Search phonenumber #no page
    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY') AS DATE_ONLY,
                    smsgw.GID,
                    smsgw.config_conditions_id,
                    smsgw.order_type_mainid,
                    smsgw.transaction_id,
                    smsgw.phonenumber,
                    smsgw.ordertype,
                    smsgw.receive_date,
                    smsgw.send_date,
                    smsgw.is_status,
                    conf.refid,
                    SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                        + extract ( hour from (send_date - receive_date) )*3600 
                        + extract ( minute from (send_date - receive_date) )*60 
                        + extract ( second from (send_date - receive_date) )
                    ) as response_time 
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time 
                    AND smsgw.phonenumber like %:search% AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    GROUP BY GID, TRUNC(smsgw.created_date, 'MONTH'), smsgw.order_type_mainid, config_conditions_id, smsgw.transaction_id, smsgw.phonenumber, smsgw.ordertype, smsgw.receive_date, smsgw.send_date, smsgw.is_status, conf.refid 
                    ORDER BY smsgw.receive_date desc 
                    """
                    ,nativeQuery = true)
    public List<ByResponseTime> ListByResponseTimeMonthSearch(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

    @Query(value = """
                    SELECT COUNT(*)  
                    FROM sms_gateway smsgw 
                    LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                    WHERE smsgw.receive_date BETWEEN :start_time AND :end_time 
                    AND smsgw.phonenumber like %:search% AND smsgw.order_type_mainid = :ordertype_main_id AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
                    """
                    ,nativeQuery = true)
    public Integer getListByResponseTimeMonthSearchTotalCount(
        @Param(value = "ordertype_main_id") Long orderTypeMainID,
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

}
        