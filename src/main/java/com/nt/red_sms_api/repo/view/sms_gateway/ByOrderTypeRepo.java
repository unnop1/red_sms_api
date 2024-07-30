package com.nt.red_sms_api.repo.view.sms_gateway;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.date.ByCondition;
import com.nt.red_sms_api.entity.view.sms_gateway.date.ByOrderType;
import com.nt.red_sms_api.entity.view.sms_gateway.date.BySending;
import com.nt.red_sms_api.entity.view.sms_gateway.month.ByOrderTypeMonth;

public interface ByOrderTypeRepo extends JpaRepository<SmsGatewayEntity,Long> {
    
    /* BY DATE */
    @Query(value =  """
                    SELECT 
                        TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD') AS DATE_ONLY,
                        odt.ordertype_name,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalUnmatch,
                        SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                            + extract ( hour from (send_date - receive_date) )*3600 
                            + extract ( minute from (send_date - receive_date) )*60 
                            + extract ( second from (send_date - receive_date) )
                        ) as response_time
                    FROM  order_type odt 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.order_type_mainid = odt.mainid
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), TRUNC(smsgw.created_date, 'DD'), odt.ordertype_name 
                    ORDER BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), TRUNC(smsgw.created_date, 'DD')
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
                        SELECT COUNT(DISTINCT TRUNC(smsgw.created_date, 'DD')) AS date_count 
                        FROM  order_type odt 
                        LEFT JOIN sms_gateway smsgw
                        ON smsgw.order_type_mainid = odt.mainid
                        WHERE smsgw.created_date BETWEEN ?1 AND ?2 
                        GROUP BY TRUNC(smsgw.created_date, 'DD'), odt.ordertype_name
                    ) subquery 
                    """,
        nativeQuery = true)
    public Integer getListByOrderTypeDateTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
                    SELECT 
                        TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD') AS DATE_ONLY,
                        odt.ordertype_name,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalUnmatch,
                        SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                            + extract ( hour from (send_date - receive_date) )*3600 
                            + extract ( minute from (send_date - receive_date) )*60 
                            + extract ( second from (send_date - receive_date) )
                        ) as response_time
                    FROM  order_type odt 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.order_type_mainid = odt.mainid
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), TRUNC(smsgw.created_date, 'DD'), odt.ordertype_name 
                    ORDER BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), TRUNC(smsgw.created_date, 'DD')
                    """,
                    nativeQuery = true)
    public List<ByOrderType> ListByOrderTypeDate(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


     
    /* BY MONTH */
    @Query(value =  """
                    SELECT 
                        TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                        odt.ordertype_name,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalUnmatch,
                        SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                            + extract ( hour from (send_date - receive_date) )*3600 
                            + extract ( minute from (send_date - receive_date) )*60 
                            + extract ( second from (send_date - receive_date) )
                        ) as response_time
                    FROM  order_type odt 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.order_type_mainid = odt.mainid
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), odt.ordertype_name
                    """,
                    nativeQuery = true)
    public List<ByOrderTypeMonth> ListByOrderTypeMonth(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*)
                    FROM (
                        SELECT COUNT(DISTINCT TRUNC(smsgw.created_date, 'MONTH')) AS date_count 
                        FROM  order_type odt 
                        LEFT JOIN sms_gateway smsgw
                        ON smsgw.order_type_mainid = odt.mainid
                        WHERE smsgw.created_date BETWEEN ?1 AND ?2 
                        GROUP BY TRUNC(smsgw.created_date, 'MONTH'), odt.ordertype_name
                    ) subquery 
                    """,
        nativeQuery = true)
    public Integer getListByOrderTypeMonthTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
                    SELECT 
                        TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                        odt.ordertype_name,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalUnmatch,
                        SUM(
                            extract ( day from (send_date - receive_date) )*86400 
                            + extract ( hour from (send_date - receive_date) )*3600 
                            + extract ( minute from (send_date - receive_date) )*60 
                            + extract ( second from (send_date - receive_date) )
                        ) as response_time
                    FROM  order_type odt 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.order_type_mainid = odt.mainid
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), odt.ordertype_name
                    """,
                    nativeQuery = true)
    public List<ByOrderTypeMonth> ListByOrderTypeMonth(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


}
