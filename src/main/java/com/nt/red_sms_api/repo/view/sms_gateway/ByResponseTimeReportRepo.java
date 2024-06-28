
package com.nt.red_sms_api.repo.view.sms_gateway;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.ByResponseReportTime;


public interface ByResponseTimeReportRepo extends JpaRepository<SmsGatewayEntity,Long> {

    //// no page
    @Query(value = """
            WITH response_times AS (
                SELECT 
                    TRUNC(smsgw.receive_date) AS receive_date,
                    EXTRACT(DAY FROM (smsgw.send_date - smsgw.receive_date)) * 86400 
                    + EXTRACT(HOUR FROM (smsgw.send_date - smsgw.receive_date)) * 3600 
                    + EXTRACT(MINUTE FROM (smsgw.send_date - smsgw.receive_date)) * 60 
                    + EXTRACT(SECOND FROM (smsgw.send_date - smsgw.receive_date)) AS response_time
                FROM 
                    sms_gateway smsgw
                LEFT JOIN 
                    config_conditions conf 
                    ON smsgw.config_conditions_id = conf.conditions_id 
                WHERE 
                    smsgw.receive_date BETWEEN :start_time AND :end_time 
                    AND (smsgw.is_status = 1 OR smsgw.is_status = 3)
            )
            SELECT 
                receive_date,
                AVG(response_time) AS avg_response_time_per_day,
                MIN(response_time) AS min_response_time_per_day,
                MAX(response_time) AS max_response_time_per_day,
                PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY response_time) AS med_response_time_per_day
            FROM 
                response_times
            GROUP BY 
                receive_date
            ORDER BY 
                receive_date DESC
            """, nativeQuery = true)
    public List<ByResponseReportTime> ListByResponseReportTime(
        @Param(value = "start_time")Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime
    );

}
        