package com.nt.red_sms_api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsGatewayRepo extends JpaRepository<SmsGatewayEntity,Long> {
    @Query(value = "SELECT * FROM sms_gateway WHERE order_type_mainID=?1 AND Is_Status=?2 ORDER BY Created_Date DESC  OFFSET ?3 ROWS FETCH NEXT ?4 ROWS ONLY ", nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayByOrderTypeAndIsStatus(Long orderTypeMainID, Integer isStatus,Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM sms_gateway WHERE order_type_mainID=?1 AND Is_Status=?2 ", nativeQuery = true)
    public Integer getByOrderTypeAndIsStatusTotalCount(Long orderTypeMainID, Integer isStatus);

    @Query(value = "SELECT smsGW.* "+
                   "FROM sms_gateway smsGW " +
                   "LEFT JOIN config_conditions smsCon ON (smsGW.order_type_mainID = smsCon.order_type_mainID) "+
                   "WHERE smsGW.Is_Status = 1 OR smsGW.Is_Status = 3 "+
                   "ORDER BY smsGW.Created_Date DESC "+
                   "OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY ",
                    nativeQuery = true
    )
    public List<SmsGatewayEntity> findSmsGatewaySendUnSend(Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM sms_gateway smsGW " +
                    "LEFT JOIN config_conditions smsCon ON (smsGW.order_type_mainID = smsCon.order_type_mainID) "+
                    "WHERE smsGW.Is_Status = 1 OR smsGW.Is_Status = 3 ",
                    nativeQuery = true
    )
    public Integer getSendUnSendTotalCount();

}
