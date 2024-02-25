package com.nt.red_sms_api.dto.req;

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


    @JsonProperty("orderTypeID")
    private String orderTypeID;

    @JsonProperty("isStatus")
    private String isStatus;
}
