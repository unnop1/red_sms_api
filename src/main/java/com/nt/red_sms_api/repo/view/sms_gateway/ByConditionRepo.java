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
    /* BY DATE */
    @Query(value =  """
                    SELECT TRUNC(smsgw.created_date) AS DATE_ONLY,
                        conf.conditions_id,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END)
                        AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
                    FROM  config_conditions conf 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.config_conditions_id = conf.conditions_id
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY conf.conditions_id, TRUNC(smsgw.created_date)
                    """,
                    nativeQuery = true)
    public List<ByCondition> ListByConditionDate(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*)
                    FROM (
                        SELECT COUNT(DISTINCT TRUNC(smsgw.created_date)) AS date_count 
                        FROM  config_conditions conf 
                        LEFT JOIN sms_gateway smsgw 
                        ON smsgw.config_conditions_id = conf.conditions_id 
                        WHERE smsgw.created_date BETWEEN ?1 AND ?2 
                        GROUP BY conf.conditions_id, TRUNC(smsgw.created_date) 
                    ) subquery 
                    """,
        nativeQuery = true)
    public Integer getListByConditionDateTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
                    SELECT TRUNC(smsgw.created_date) AS DATE_ONLY,
                        conf.conditions_id,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END)
                        AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
                    FROM  config_conditions conf 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.config_conditions_id = conf.conditions_id
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY conf.conditions_id, TRUNC(smsgw.created_date)
                    """,
                    nativeQuery = true)
    public List<ByCondition> ListByConditionDate(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


    /* BY MONTH */
    @Query(value =  """
        SELECT TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY') AS DATE_ONLY,
            conf.conditions_id,
            COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
            COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END)
            AS totalEvent, 
            COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
            COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
        FROM  config_conditions conf 
        LEFT JOIN sms_gateway smsgw
        ON smsgw.config_conditions_id = conf.conditions_id
        WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
        GROUP BY conf.conditions_id, TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY')
                    """,
                    nativeQuery = true)
    public List<ByCondition> ListByConditionMonth(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime,
        Pageable pageable
    );

    @Query(value = """
                    SELECT COUNT(*)
                    FROM (
                        SELECT COUNT(DISTINCT TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY')) AS date_count 
                        FROM  config_conditions conf 
                        LEFT JOIN sms_gateway smsgw
                        ON smsgw.config_conditions_id = conf.conditions_id 
                        WHERE smsgw.created_date BETWEEN ?1 AND ?2 
                        GROUP BY conf.conditions_id, TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY')
                    ) subquery 
                    """,
        nativeQuery = true)
    public Integer getListByConditionMonthTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
        SELECT TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY') AS DATE_ONLY,
            conf.conditions_id,
            COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
            COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END)
            AS totalEvent, 
            COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
            COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) + COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
        FROM  config_conditions conf 
        LEFT JOIN sms_gateway smsgw
        ON smsgw.config_conditions_id = conf.conditions_id
        WHERE smsGW.created_date BETWEEN :start_time AND :end_time 
        GROUP BY conf.conditions_id, TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON-YYYY')
                    """,
                    nativeQuery = true)
    public List<ByCondition> ListByConditionMonth(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

}
