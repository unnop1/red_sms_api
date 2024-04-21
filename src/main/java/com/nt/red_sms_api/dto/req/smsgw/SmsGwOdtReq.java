package com.nt.red_sms_api.dto.req.smsgw;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsGwOdtReq {
    @JsonProperty("page")
    private Integer page;

    @JsonProperty("limit")
    private Integer limit;


    @JsonProperty("orderTypeMainID")
    private Long orderTypeMainID;

    @JsonProperty("isStatus")
    private Integer isStatus;
}
