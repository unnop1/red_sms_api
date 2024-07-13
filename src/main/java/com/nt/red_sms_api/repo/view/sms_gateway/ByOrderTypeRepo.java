package com.nt.red_sms_api.repo.view.sms_gateway;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.ByCondition;
import com.nt.red_sms_api.entity.view.sms_gateway.ByOrderType;
import com.nt.red_sms_api.entity.view.sms_gateway.BySending;

public interface ByOrderTypeRepo extends JpaRepository<SmsGatewayEntity,Long> {
    
    /* BY DATE */
    @Query(value =  """
                    SELECT TRUNC(smsgw.created_date) AS DATE_ONLY,
                        odt.ordertype_name,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END)
                        AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
                    FROM  order_type odt 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.order_type_mainid = odt.mainid
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY odt.ordertype_name, TRUNC(smsgw.created_date)
                    """,
                    nativeQuery = true)
    public List<ByOrderType> ListByOrderTypeDate(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*)
                    FROM (
                        SELECT COUNT(DISTINCT TRUNC(smsgw.created_date)) AS date_count 
                        FROM  order_type odt 
                        LEFT JOIN sms_gateway smsgw
                        ON smsgw.order_type_mainid = odt.mainid
                        WHERE smsgw.created_date BETWEEN ?1 AND ?2 
                        GROUP BY odt.ordertype_name, TRUNC(smsgw.created_date) 
                    ) subquery 
                    """,
        nativeQuery = true)
    public Integer getListByOrderTypeDateTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
                    SELECT TRUNC(smsgw.created_date) AS DATE_ONLY,
                        odt.ordertype_name,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END)
                        AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
                    FROM  order_type odt 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.order_type_mainid = odt.mainid
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY odt.ordertype_name, TRUNC(smsgw.created_date)
                    """,
                    nativeQuery = true)
    public List<ByOrderType> ListByOrderTypeDate(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


    /* BY MONTH */
    @Query(value =  """
        SELECT TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY') AS DATE_ONLY,
            odt.ordertype_name,
            COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
            COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END)
            AS totalEvent, 
            COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
            COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
        FROM  order_type odt 
        LEFT JOIN sms_gateway smsgw
        ON smsgw.order_type_mainid = odt.mainid
        WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
        GROUP BY odt.ordertype_name, TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY')
                    """,
                    nativeQuery = true)
    public List<ByOrderType> ListByOrderTypeMonth(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*)
                    FROM (
                        SELECT COUNT(DISTINCT TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY')) AS date_count 
                        FROM  order_type odt 
                        LEFT JOIN sms_gateway smsgw
                        ON smsgw.order_type_mainid = odt.mainid
                        WHERE smsgw.created_date BETWEEN ?1 AND ?2 
                        GROUP BY odt.ordertype_name, TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY')
                    ) subquery 
                    """,
        nativeQuery = true)
    public Integer getListByOrderTypeMonthTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
        SELECT TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY') AS DATE_ONLY,
            odt.ordertype_name,
            COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
            COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END)
            AS totalEvent, 
            COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
            COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
        FROM  order_type odt 
        LEFT JOIN sms_gateway smsgw
        ON smsgw.order_type_mainid = odt.mainid
        WHERE smsGW.created_date BETWEEN :start_time AND :end_time 
        GROUP BY odt.ordertype_name, TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY')
                    """,
                    nativeQuery = true)
    public List<ByOrderType> ListByOrderTypeMonth(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

}
