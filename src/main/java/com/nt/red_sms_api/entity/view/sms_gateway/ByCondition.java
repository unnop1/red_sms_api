package com.nt.red_sms_api.entity.view.sms_gateway;


import java.sql.Timestamp;

public interface ByCondition {
        Long getCONDITIONS_ID();
        String getREFID();
        Integer getTOTALEVENT();
        Integer getTOTALUNMATCH();
        Integer getTOTALSUCCESS();

}
