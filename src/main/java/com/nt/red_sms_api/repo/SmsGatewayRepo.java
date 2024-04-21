package com.nt.red_sms_api.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import com.nt.red_sms_api.entity.SmsGatewayEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsGatewayRepo extends JpaRepository<SmsGatewayEntity,Long> {
    @Query(value = "SELECT * FROM sms_gateway WHERE order_type_mainID=?1 AND Is_Status=?2 ORDER BY Created_Date DESC  OFFSET ?3 ROWS FETCH NEXT ?4 ROWS ONLY ", nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayByOrderTypeAndIsStatus(Long orderTypeMainID, Integer isStatus,Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM sms_gateway WHERE order_type_mainID=?1 AND Is_Status=?2 ", nativeQuery = true)
    public Integer getByOrderTypeAndIsStatusTotalCount(Long orderTypeMainID, Integer isStatus);

    @Query(value = "SELECT smsGW.* FROM sms_gateway smsGW LEFT JOIN config_conditions smsCon ON (smsGW.order_type_mainID = smsCon.order_type_mainID) WHERE smsGW.Is_Status = 1 OR smsGW.Is_Status = 3 AND  smsGW.created_date BETWEEN ?1 AND ?2  ORDER BY ?3 OFFSET ?4 ROWS FETCH NEXT ?5 ROWS ONLY "
                    ,nativeQuery = true
    )
    public List<SmsGatewayEntity> findSmsGatewaySendUnSend(Timestamp startTime,
                                                           Timestamp endTime,
                                                           String sort,
                                                           Integer offset, 
                                                           Integer limit);

    @Query(value = "SELECT COUNT(*) FROM sms_gateway smsGW LEFT JOIN config_conditions smsCon ON (smsGW.order_type_mainID = smsCon.order_type_mainID) WHERE smsGW.Is_Status = 1 OR smsGW.Is_Status = 3 AND  smsGW.created_date BETWEEN ?1 AND ?2 ",nativeQuery = true)
    public Integer getSendUnSendTotalCount(Timestamp startTime,Timestamp endTime);

}
