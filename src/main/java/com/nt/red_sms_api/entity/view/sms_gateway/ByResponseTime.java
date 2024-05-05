package com.nt.red_sms_api.entity.view.sms_gateway;

import java.sql.Timestamp;

public interface ByResponseTime {
    String getCONFIG_CONDITIONS_ID();
    String getREFID();
    Timestamp getRECEIVE_DATE();
    Timestamp getSEND_DATE();
    String getPHONENUMBER();
    Integer getIS_STATUS();
    String getRESPONSE_TIME();
}
