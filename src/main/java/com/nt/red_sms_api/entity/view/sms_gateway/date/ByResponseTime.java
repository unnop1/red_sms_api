package com.nt.red_sms_api.entity.view.sms_gateway.date;

import java.sql.Timestamp;

public interface ByResponseTime {
    String getYEAR_ONLY();
    String getMONTH_ONLY();
    String getDATE_ONLY();
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
