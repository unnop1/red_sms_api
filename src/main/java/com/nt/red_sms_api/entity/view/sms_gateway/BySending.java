package com.nt.red_sms_api.entity.view.sms_gateway;


import java.sql.Timestamp;

public interface BySending {
        Timestamp getDATE_ONLY();
        Integer getTOTALSEND();
        Integer getTOTALSUCCESS();
        Integer getTOTALFAIL();

}
