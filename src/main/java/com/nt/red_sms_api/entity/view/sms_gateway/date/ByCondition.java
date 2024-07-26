package com.nt.red_sms_api.entity.view.sms_gateway.date;


import java.sql.Timestamp;

public interface ByCondition {
        String getYEAR_ONLY();
        String getMONTH_ONLY();
        String getDATE_ONLY();
        Long getCONDITIONS_ID();
        String getREFID();
        Integer getTOTALEVENT();
        Integer getTOTALUNMATCH();
        Integer getTOTALSUCCESS();

}
