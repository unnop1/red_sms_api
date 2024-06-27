package com.nt.red_sms_api.entity.view.sms_gateway;

import java.sql.Timestamp;

public interface ByResponseTime {
    String getCONFIG_CONDITIONS_ID();
    String getREFID();
    String getORDERTYPE();
    Timestamp getRECEIVE_DATE();
    Timestamp getSEND_DATE();
    String getPHONENUMBER();
    Integer getIS_STATUS();
    String getSUM_RESPONSE_TIME();
    String getAVG_RESPONSE_TIME();
    String getMAX_RESPONSE_TIME();
    String getMIN_RESPONSE_TIME();
    String getTRANSACTION_ID();
}
