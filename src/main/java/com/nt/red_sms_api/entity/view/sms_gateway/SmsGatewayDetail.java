package com.nt.red_sms_api.entity.view.sms_gateway;

import java.sql.Timestamp;

public interface SmsGatewayDetail {
    Long getGID();
    Long getCONFIG_CONDITIONS_ID();
    String getSMSMESSAGE();
    String getMESSAGE_RAW();
    Long getORDER_TYPE_MAINID();
    String getORDERTYPE();
    String getPHONENUMBER();
    String getPAYLOADMQ();
    String getPAYLOADGW();
    Integer getIS_STATUS();
    String getREMARK();
    Timestamp getCREATED_DATE();
    Timestamp getRECEIVE_DATE();
    Timestamp getSEND_DATE();
    String getTRANSACTION_ID();
    String getREFID();
}
