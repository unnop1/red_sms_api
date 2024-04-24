package com.nt.red_sms_api.entity.view.sms_gateway;

import java.sql.Timestamp;

public interface ListSmsGateway {
    String getTRANSACTION_ID();
    Timestamp getSEND_DATE();
    String getPHONENUMBER();
    Integer getIS_STATUS();
    String getORDERTYPE();
}
