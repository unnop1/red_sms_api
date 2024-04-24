package com.nt.red_sms_api.dto.req.smscondition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsConditionMoreDetailReq {
    @JsonProperty("conditions_id")
    private Long conditionsID;

    @JsonProperty("is_enable")
    private Integer isEnable=null;
}
