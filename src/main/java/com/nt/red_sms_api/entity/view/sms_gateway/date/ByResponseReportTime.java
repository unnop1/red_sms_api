package com.nt.red_sms_api.entity.view.sms_gateway.date;

import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

public interface ByResponseReportTime {
    String getRECEIVE_DATE();
    String getAVG_RESPONSE_TIME_PER_DAY();
    String getMIN_RESPONSE_TIME_PER_DAY();
    String getMAX_RESPONSE_TIME_PER_DAY();
    String getMED_RESPONSE_TIME_PER_DAY();
    // Object getDATA_COLUMN_ARRAY();
}
