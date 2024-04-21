package com.nt.red_sms_api.entity.view.user;

import java.sql.Timestamp;


public interface ListUser {
    
    Long getID();
    String getNAME();
    String getUSERNAME();
    Timestamp getLAST_LOGIN();
    Timestamp getCREATED_DATE();
    String getPERMISSION_NAME();

}
