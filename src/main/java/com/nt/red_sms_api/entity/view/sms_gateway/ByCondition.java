package com.nt.red_sms_api.entity.view.sms_gateway;


import java.sql.Timestamp;

public interface ByCondition {
        Timestamp getDATE_ONLY();
        Integer getTOTALEVENT();
        Integer getTOTALUNMATCH();
        Integer getTOTALSUCCESS();

}
