package com.nt.red_sms_api.entity.view.sms_gateway.date;


import java.sql.Timestamp;

public interface BySending {
        String getYEAR_ONLY();
        String getMONTH_ONLY();
        String getDATE_ONLY();
        Integer getTOTALSEND();
        Integer getTOTALSUCCESS();
        Integer getTOTALFAIL();

}
