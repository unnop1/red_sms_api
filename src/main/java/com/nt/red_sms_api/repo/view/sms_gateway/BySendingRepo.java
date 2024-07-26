package com.nt.red_sms_api.repo.view.sms_gateway;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.date.BySending;
import com.nt.red_sms_api.entity.view.sms_gateway.month.BySendingMonth;

public interface BySendingRepo extends JpaRepository<SmsGatewayEntity,Long> {
    
    /* BY DATE */
    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                    TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                    TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD') AS DATE_ONLY,
                    COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalSend,
                    COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                    COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess,
                    COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalFail
                    FROM sms_gateway smsgw
                    WHERE smsGW.created_date BETWEEN ?1 AND ?2
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), TRUNC(smsgw.created_date, 'DD')
                    """,
                    nativeQuery = true)
    public List<BySending> ListBySending(Timestamp startTime,
                                            Timestamp endTime,
                                            Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*)
                    FROM (
                        SELECT COUNT(DISTINCT TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD-MON-YYYY')) AS date_count
                        FROM sms_gateway smsgw
                        WHERE smsgw.created_date BETWEEN ?1 AND ?2
                        GROUP BY TRUNC(smsgw.created_date, 'DD')
                    ) subquery 
                    """,
        nativeQuery = true)
    public Integer getListBySendingTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                    TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                    TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD') AS DATE_ONLY,
                    COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalSend,
                    COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                    COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess,
                    COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalFail
                    FROM sms_gateway smsgw
                    WHERE smsGW.created_date BETWEEN ?1 AND ?2
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), TRUNC(smsgw.created_date, 'DD')
                    """,
                    nativeQuery = true)
    public List<BySending> ListBySending(Timestamp startTime,
                                            Timestamp endTime
    );


    /* BY MONTH */
    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                    TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                    COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalSend,
                    COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                    COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess,
                    COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalFail
                    FROM sms_gateway smsgw
                    WHERE smsGW.created_date BETWEEN ?1 AND ?2
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH')
                    """,
                    nativeQuery = true)
    public List<BySendingMonth> ListBySendingMonth(Timestamp startTime,
                                            Timestamp endTime,
                                            Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*)
                    FROM (
                        SELECT COUNT(DISTINCT TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'))) AS date_count
                        FROM sms_gateway smsgw
                        WHERE smsgw.created_date BETWEEN ?1 AND ?2
                        GROUP BY TRUNC(smsgw.created_date, 'MONTH')
                    ) subquery 
                    """,
        nativeQuery = true)
    public Integer getListBySendingMonthTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
                    SELECT 
                    TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                    TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                    COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalSend,
                    COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                    COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess,
                    COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + 
                    COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalFail
                    FROM sms_gateway smsgw
                    WHERE smsGW.created_date BETWEEN ?1 AND ?2
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH')
                    """,
                    nativeQuery = true)
    public List<BySendingMonth> ListBySendingMonth(Timestamp startTime,
                                            Timestamp endTime
    );
}
