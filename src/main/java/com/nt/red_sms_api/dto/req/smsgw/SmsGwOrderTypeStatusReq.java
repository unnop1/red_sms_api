package com.nt.red_sms_api.dto.req.smsgw;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SmsGwOrderTypeStatusReq {
    @lombok.NonNull
    @JsonProperty("order_type_main_id")
    private Long order_type_main_id;

    @lombok.NonNull
    @JsonProperty("is_status")
    private Integer isStatus;

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
