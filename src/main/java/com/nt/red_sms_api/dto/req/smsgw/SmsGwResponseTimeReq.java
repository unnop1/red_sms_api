package com.nt.red_sms_api.dto.req.smsgw;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SmsGwResponseTimeReq {

    @JsonProperty("order_type_name")
    private String orderTypeName;

    @JsonProperty("draw")
    private Integer draw=11;

    @JsonProperty("order[0][dir]")
    private String sortBy="ASC";

    @JsonProperty("order[0][name]")
    private String sortName="created_date";

    @JsonProperty("by_time")
    private String byTime="date";

    @lombok.NonNull
    @JsonProperty("start_time")
    private String startTime;

    @lombok.NonNull
    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("start")
    private Integer start=0;

    @JsonProperty("length")
    private Integer length=10;

    @JsonProperty("search")
    private String search="";

    @JsonProperty("search_field")
    private String searchField="";
}
