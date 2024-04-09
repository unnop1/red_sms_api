package com.nt.red_sms_api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsGatewayRepo extends JpaRepository<SmsGatewayEntity,Long> {
    @Query(value = "SELECT * FROM sms_gateway WHERE order_type_ID=?3 AND Is_Status=?4 ORDER BY Created_Date DESC  OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY ", nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayByOrderTypeAndIsStatus(Integer page, Integer limit, @Param("order_type_ID") String orderTypeID, @Param("IsStatus") String isStatus);

    @Query(value = "SELECT smsGW.* " +
                    "FROM sms_gateway AS smsGW LEFT JOIN "+
                    "config_conditions AS smsCon ON (smsGW.order_type_ID = smsCon.order_type_ID) " +
                    "WHERE smsGW.Is_Status = 1 OR smsGW.Is_Status = 3 "+
                    "ORDER BY smsGW.Created_Date DESC  OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY ", 
                    nativeQuery = true
    )
    public List<SmsGatewayEntity> findSmsGatewaySendUnSend(Integer page, Integer limit);

}
