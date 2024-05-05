
package com.nt.red_sms_api.repo.view.sms_gateway;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.ByResponseTime;
import com.nt.red_sms_api.entity.view.sms_gateway.ListSmsGateway;

import java.sql.Timestamp;
import java.util.List;

public interface ByResponseTimeRepo extends JpaRepository<SmsGatewayEntity,Long> {
    
    @Query(value =  """
                    SELECT 
                    smsgw.GID,
                    smsgw.config_conditions_id,
                    smsgw.phonenumber,
                    smsgw.ordertype,
                    smsgw.receive_date,
                    smsgw.send_date,
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
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.send_date BETWEEN :start_time AND :end_time 
                    GROUP BY GID, config_conditions_id, smsgw.phonenumber, smsgw.ordertype, smsgw.receive_date, smsgw.send_date, conf.refid
                    """
                    ,nativeQuery = true)
    public List<ByResponseTime> ListByResponseTime(
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
                    WHERE smsgw.send_date BETWEEN :start_time AND :end_time 
                    """
                    ,nativeQuery = true)
    public Integer getListByResponseTimeTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime
    );


    @Query(value =  """
                    SELECT 
                    smsgw.GID,
                    smsgw.config_conditions_id,
                    smsgw.phonenumber,
                    smsgw.ordertype,
                    smsgw.receive_date,
                    smsgw.send_date,
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
                    ON smsgw.CONFIG_CONDITIONS_ID = conf.conditions_id
                    WHERE smsgw.send_date BETWEEN :start_time AND :end_time 
                    AND smsgw.phonenumber like %:search%
                    GROUP BY GID, config_conditions_id, smsgw.phonenumber, smsgw.ordertype, smsgw.receive_date, smsgw.send_date, conf.refid
                    """
                    ,nativeQuery = true)
    public List<ByResponseTime> ListByResponseTimeSearch(
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
                    WHERE smsgw.send_date BETWEEN :start_time AND :end_time 
                    AND smsgw.phonenumber like %:search%
                    """
                    ,nativeQuery = true)
    public Integer getListByResponseTimeSearchTotalCount(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search")String search
    );

}
        