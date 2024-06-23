package com.nt.red_sms_api.entity.view.order_type;


import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public interface ListOrderType {
        Long getTYPEID();
        Long getMAINID();
        String getDESCRIPTION();
        String getORDERTYPE_NAME();
        Integer getIS_ENABLE();
        Integer getIS_DELETE();
        Timestamp getCREATED_DATE();
        String getCREATED_BY();
        Timestamp getUPDATED_DATE();
        String getUPDATED_BY();
        Integer getTotalMsg();
        Integer getTotalSend();
        Integer getTotalUnSend();
}
    