package com.nt.red_sms_api.repo.view.sms_gateway;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.date.ByCondition;
import com.nt.red_sms_api.entity.view.sms_gateway.month.ByConditionMonth;

public interface ByConditionRepo extends JpaRepository<SmsGatewayEntity,Long> {
    /* BY DATE */
    @Query(value =  """
                    SELECT 
                        TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD') AS DATE_ONLY,
                        conf.conditions_id,
                        conf.refid,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
                    FROM  config_conditions conf 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.config_conditions_id = conf.conditions_id
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), TRUNC(smsgw.created_date, 'DD'), conf.conditions_id, conf.refid
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
                        SELECT COUNT(DISTINCT TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD-MON-YYYY')) AS date_count 
                        FROM  config_conditions conf 
                        LEFT JOIN sms_gateway smsgw 
                        ON smsgw.config_conditions_id = conf.conditions_id 
                        WHERE smsgw.created_date BETWEEN ?1 AND ?2 
                        GROUP BY TRUNC(smsgw.created_date, 'DD'), conf.conditions_id, conf.refid
                    ) subquery 
                    """,
        nativeQuery = true)
    public Integer getListByConditionDateTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
                    SELECT 
                        TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'DD'), 'DD') AS DATE_ONLY,
                        conf.conditions_id,
                        conf.refid,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
                    FROM  config_conditions conf 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.config_conditions_id = conf.conditions_id
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), TRUNC(smsgw.created_date, 'DD'), conf.conditions_id, conf.refid
                    """,
                    nativeQuery = true)
    public List<ByCondition> ListByConditionDate(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );


    /* BY MONTH */
    @Query(value =  """
                    SELECT 
                        TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                        conf.conditions_id,
                        conf.refid,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
                    FROM  config_conditions conf 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.config_conditions_id = conf.conditions_id
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), conf.conditions_id, conf.refid
                    """,
                    nativeQuery = true)
    public List<ByConditionMonth> ListByConditionMonth(
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
                        GROUP BY TRUNC(smsgw.created_date, 'MONTH'), conf.conditions_id 
                    ) subquery 
                    """,
        nativeQuery = true)
    public Integer getListByConditionMonthTotalCount(Timestamp startTime, Timestamp endTime);

    /// no page
    @Query(value =  """
                    SELECT 
                        TO_CHAR(TRUNC(smsgw.created_date, 'YEAR'), 'YYYY') AS YEAR_ONLY,
                        TO_CHAR(TRUNC(smsgw.created_date, 'MONTH'), 'MON') AS MONTH_ONLY,
                        conf.conditions_id,
                        conf.refid,
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalEvent, 
                        COUNT(CASE WHEN smsgw.is_status = 1 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 3 THEN 1 END) AS totalSuccess, 
                        COUNT(CASE WHEN smsgw.is_status = 2 THEN 1 END) +
                        COUNT(CASE WHEN smsgw.is_status = 4 THEN 1 END) AS totalUnmatch
                    FROM  config_conditions conf 
                    LEFT JOIN sms_gateway smsgw
                    ON smsgw.config_conditions_id = conf.conditions_id
                    WHERE smsgw.created_date BETWEEN :start_time AND :end_time 
                    GROUP BY TRUNC(smsgw.created_date, 'YEAR'), TRUNC(smsgw.created_date, 'MONTH'), conf.conditions_id, conf.refid
                    """,
                    nativeQuery = true)
    public List<ByConditionMonth> ListByConditionMonth(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

}
