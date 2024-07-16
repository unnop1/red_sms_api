package com.nt.red_sms_api.entity.view.sms_gateway;


import java.sql.Timestamp;

public interface ByOrderType {
        String getRESPONSE_TIME();
        String getORDERTYPE_NAME();
        Integer getTOTALEVENT();
        Integer getTOTALUNMATCH();
        Integer getTOTALSUCCESS();

}
