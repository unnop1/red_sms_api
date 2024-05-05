package com.nt.red_sms_api.entity.view.sms_gateway;

import java.sql.Timestamp;

public interface ListSmsGateway {
    Long getGID();
    String getTRANSACTION_ID();
    Timestamp getSEND_DATE();
    String getREFID();
    String getPHONENUMBER();
    Integer getIS_STATUS();
    String getORDERTYPE();
}
