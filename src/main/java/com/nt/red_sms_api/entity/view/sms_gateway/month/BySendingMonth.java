package com.nt.red_sms_api.entity.view.sms_gateway.month;


import java.sql.Timestamp;

public interface BySendingMonth {
        String getYEAR_ONLY();
        String getMONTH_ONLY();
        Integer getTOTALSEND();
        Integer getTOTALSUCCESS();
        Integer getTOTALFAIL();

}
