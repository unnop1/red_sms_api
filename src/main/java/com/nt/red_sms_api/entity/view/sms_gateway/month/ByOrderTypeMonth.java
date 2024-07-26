package com.nt.red_sms_api.entity.view.sms_gateway.month;


import java.sql.Timestamp;

public interface ByOrderTypeMonth {
        String getYEAR_ONLY();
        String getMONTH_ONLY();
        String getRESPONSE_TIME();
        String getORDERTYPE_NAME();
        Integer getTOTALEVENT();
        Integer getTOTALUNMATCH();
        Integer getTOTALSUCCESS();

}
