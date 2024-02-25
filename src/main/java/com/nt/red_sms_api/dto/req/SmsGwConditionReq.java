package com.nt.red_sms_api.dto.req;



import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SmsGwConditionReq {
    @JsonProperty("gID")
    private Long gID;

    @JsonProperty("orderTypeID")
    private Long orderTypeID;
}
