package com.nt.red_sms_api.repo.view.sms_gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.BySending;

import java.sql.Timestamp;
import java.util.List;

public interface BySendingRepo extends JpaRepository<SmsGatewayEntity,Long> {
    
    @Query(value =  "SELECT TRUNC(smsgw.created_date) AS DATE_ONLY, "+
                    "COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSend, "+
                    "COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) AS totalSuccess, "+
                    "COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalFail "+
                    "FROM sms_gateway smsgw "+
                    "WHERE smsGW.created_date BETWEEN ?1 AND ?2 "+
                    "GROUP BY TRUNC(smsgw.created_date) "+
                    "ORDER BY ?3 "+
                    "OFFSET ?4 ROWS " +
                    "FETCH NEXT ?5 ROWS ONLY ",
                    nativeQuery = true)
    public List<BySending> ListBySending(Timestamp startTime,
                                            Timestamp endTime,
                                            String sort,
                                            Integer offset,
                                            Integer limit);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM ( "+
                        "SELECT COUNT(DISTINCT TRUNC(smsgw.created_date)) AS date_count "+
                        "FROM sms_gateway smsgw "+
                        "WHERE smsgw.created_date BETWEEN ?1 AND ?2 "+
                        "GROUP BY TRUNC(smsgw.created_date) "+
                    ") subquery ",
        nativeQuery = true)
    public Integer getListBySendingTotalCount(Timestamp startTime, Timestamp endTime);

}
