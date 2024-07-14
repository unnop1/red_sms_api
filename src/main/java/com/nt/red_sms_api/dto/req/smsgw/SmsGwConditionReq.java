package com.nt.red_sms_api.dto.req.smsgw;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SmsGwConditionReq {
    @lombok.NonNull
    @JsonProperty("conditions_id")
    private Long conditions_id;

    @JsonProperty("sort_by")
    private String sortBy="ASC";

    @JsonProperty("sort_name")
    private String sortName="conditions_id";

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

    @JsonProperty("Search")
    private String search="";

    @JsonProperty("Search_field")
    private String searchField="";

    

}
