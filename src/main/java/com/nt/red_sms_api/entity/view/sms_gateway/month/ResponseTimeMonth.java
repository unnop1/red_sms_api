package com.nt.red_sms_api.entity.view.sms_gateway.month;

import java.sql.Timestamp;

public interface ResponseTimeMonth {
    String getYEAR_ONLY();
    String getMONTH_ONLY();
    String getCONFIG_CONDITIONS_ID();
    String getREFID();
    String getORDERTYPE();
    Timestamp getRECEIVE_DATE();
    Timestamp getSEND_DATE();
    String getPHONENUMBER();
    Integer getIS_STATUS();
    String getRESPONSE_TIME();
    String getTRANSACTION_ID();
}
