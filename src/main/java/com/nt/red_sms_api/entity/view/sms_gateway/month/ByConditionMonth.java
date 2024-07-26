package com.nt.red_sms_api.entity.view.sms_gateway.month;


import java.sql.Timestamp;

public interface ByConditionMonth {
        String getYEAR_ONLY();
        String getMONTH_ONLY();
        Long getCONDITIONS_ID();
        String getREFID();
        Integer getTOTALEVENT();
        Integer getTOTALUNMATCH();
        Integer getTOTALSUCCESS();

}
