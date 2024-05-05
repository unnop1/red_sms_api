package com.nt.red_sms_api.entity.view.smscondition;


import java.sql.Timestamp;

public interface ListSmsCondition {
    Long getCONDITIONS_ID();
    String getREFID();
    Timestamp getDATE_START();
    Timestamp getDATE_END();
    Timestamp getCREATED_DATE();
    String getMESSAGE();
    String getORDERTYPE();
    String getIS_DELETE();
}
