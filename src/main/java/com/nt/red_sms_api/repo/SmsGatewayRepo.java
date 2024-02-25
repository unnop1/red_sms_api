package com.nt.red_sms_api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.red_sms_api.enitiy.SmsGatewayEntity;

public interface SmsGatewayRepo extends JpaRepository<SmsGatewayEntity,Long> {
    @Query(value = "SELECT * FROM sms_gateway WHERE order_type_MainID=?3 AND IsStatus=?4 ORDER BY CreatedDate DESC  LIMIT ?1, ?2", nativeQuery = true)
    public List<SmsGatewayEntity> findSmsGatewayByOrderTypeAndIsStatus(Integer page, Integer limit, @Param("order_type_MainID") String orderTypeID, @Param("IsStatus") String isStatus);

    @Query(value = "SELECT smsGW.*, smsCon.OfferingId as smsConOfferingId " +
                    "FROM sms_gateway AS smsGW LEFT JOIN "+
                    "sms_conditions AS smsCon ON (smsGW.order_type_MainID = smsCon.order_type_MainID) " +
                    "WHERE smsGW.IsStatus = 1 OR smsGW.IsStatus = 3 "+
                    "ORDER BY smsGW.CreatedDate DESC  LIMIT ?1, ?2", 
                    nativeQuery = true
    )
    public List<SmsGatewayEntity> findSmsGatewaySendUnSend(Integer page, Integer limit);

}
