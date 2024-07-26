package com.nt.red_sms_api.entity.view.sms_gateway.date;


import java.sql.Timestamp;

public interface ByOrderType {
        String getYEAR_ONLY();
        String getMONTH_ONLY();
        String getDATE_ONLY();
        String getRESPONSE_TIME();
        String getORDERTYPE_NAME();
        Integer getTOTALEVENT();
        Integer getTOTALUNMATCH();
        Integer getTOTALSUCCESS();

}
