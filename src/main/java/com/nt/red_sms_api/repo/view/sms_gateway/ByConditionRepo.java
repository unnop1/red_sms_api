package com.nt.red_sms_api.repo.view.sms_gateway;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.ByCondition;

public interface ByConditionRepo extends JpaRepository<SmsGatewayEntity,Long> {
    
    @Query(value =  """
        SELECT TRUNC(smsgw.created_date) AS DATE_ONLY,
        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) AS totalEvent, 
        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) AS totalSuccess, 
        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) AS totalUnmatch
        FROM sms_gateway smsgw
        WHERE smsGW.created_date BETWEEN :start_time AND :end_time 
        GROUP BY TRUNC(smsgw.created_date)
                    """,
                    nativeQuery = true)
    public List<ByCondition> ListByCondition(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) "+
                    "FROM ( "+
                        "SELECT COUNT(DISTINCT TRUNC(smsgw.created_date)) AS date_count "+
                        "FROM sms_gateway smsgw "+
                        "WHERE smsgw.created_date BETWEEN ?1 AND ?2 "+
                        "GROUP BY TRUNC(smsgw.created_date) "+
                    ") subquery ",
        nativeQuery = true)
    public Integer getListByConditionTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
        SELECT TRUNC(smsgw.created_date) AS DATE_ONLY,
        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) AS totalEvent, 
        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) AS totalSuccess, 
        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) AS totalUnmatch
        FROM sms_gateway smsgw
        WHERE smsGW.created_date BETWEEN :start_time AND :end_time 
        GROUP BY TRUNC(smsgw.created_date)
                    """,
                    nativeQuery = true)
    public List<ByCondition> ListByCondition(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

}
