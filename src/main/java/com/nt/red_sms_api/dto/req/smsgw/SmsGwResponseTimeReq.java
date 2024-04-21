package com.nt.red_sms_api.dto.req.smsgw;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@ToString
public class SmsGwResponseTimeReq {
    @JsonProperty("draw")
    private Integer draw=11;

    @JsonProperty("order[0][dir]")
    private String sortBy="ASC";

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("start")
    private Integer start=0;

    @JsonProperty("length")
    private Integer length=10;

    @JsonProperty("Search")
    private Integer search;

}
