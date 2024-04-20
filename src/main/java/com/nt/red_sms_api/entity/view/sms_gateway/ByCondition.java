package com.nt.red_sms_api.entity.view.sms_gateway;


import java.sql.Timestamp;

public interface ByCondition {

        Timestamp getDATE_ONLY();
        Integer getTOTALSEND();
        Integer getTOTALPENDING();
        Integer getTOTALUNMATCH();
        Integer getTOTALSUCCESS();
        Integer getTOTALFAIL();

}
