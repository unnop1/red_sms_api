package com.nt.red_sms_api.repo.view.order_type;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.view.order_type.ListOrderType;

import java.sql.Timestamp;
import java.util.List;

public interface ListOrderTypeRepo extends JpaRepository<ListOrderType,Long> {
    
    @Query(value = "SELECT  odt.*,"+  
                   " (SELECT COUNT(sms_gw.GID) FROM sms_gateway sms_gw WHERE odt.MainID = sms_gw.order_type_mainID AND ( sms_gw.CREATED_DATE >= trunc(sysdate)  OR sms_gw.CREATED_DATE IS NULL) ) as TotalMsg,"+
                   " (SELECT COUNT(sms_gw.GID) FROM sms_gateway sms_gw WHERE odt.MainID = sms_gw.order_type_mainID AND sms_gw.Is_Status = 1 AND ( sms_gw.CREATED_DATE >= trunc(sysdate)  OR sms_gw.CREATED_DATE IS NULL)) as TotalSend,"+
                   " (SELECT COUNT(sms_gw.GID) FROM sms_gateway sms_gw WHERE odt.MainID = sms_gw.order_type_mainID AND sms_gw.Is_Status = 2 AND ( sms_gw.CREATED_DATE >= trunc(sysdate)  OR sms_gw.CREATED_DATE IS NULL)) as TotalUnSend"+
                   " FROM  order_type odt  WHERE  odt.Is_Delete = 0 "+
                   " AND odt.created_date BETWEEN :start_time AND :end_time ",
                 nativeQuery = true)
    public List<ListOrderType> ListOrderType(
      @Param(value = "start_time") Timestamp startTime,
      @Param(value = "end_time") Timestamp endTime,
      Pageable pageable
    );

    @Query(value = "SELECT  COUNT(*) FROM  order_type odt  WHERE  Is_Delete = 0 AND odt.created_date BETWEEN :start_time AND :end_time", nativeQuery = true)
    public Integer getTotalCount(
      @Param(value = "start_time") Timestamp startTime,
      @Param(value = "end_time") Timestamp endTime
    );

}
