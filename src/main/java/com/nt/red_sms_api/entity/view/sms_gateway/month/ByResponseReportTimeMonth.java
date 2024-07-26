package com.nt.red_sms_api.entity.view.sms_gateway.month;

import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

public interface ByResponseReportTimeMonth {
    String getRECEIVE_DATE();
    String getAVG_RESPONSE_TIME_PER_MONTH();
    String getMIN_RESPONSE_TIME_PER_MONTH();
    String getMAX_RESPONSE_TIME_PER_MONTH();
    String getMED_RESPONSE_TIME_PER_MONTH();
    // Object getDATA_COLUMN_ARRAY();
}
