package com.nt.red_sms_api.entity.view.sms_gateway.date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ByResponseReportRangeTime {

    @JsonProperty("receive_DATE")
    String getRECEIVE_DATE;

    @JsonProperty("avg_RESPONSE_TIME_PER_DAY")
    String getAVG_RESPONSE_TIME_PER_DAY = "0";

    @JsonProperty("min_RESPONSE_TIME_PER_DAY")
    String getMIN_RESPONSE_TIME_PER_DAY = "0";

    @JsonProperty("max_RESPONSE_TIME_PER_DAY")
    String getMAX_RESPONSE_TIME_PER_DAY = "0";

    @JsonProperty("med_RESPONSE_TIME_PER_DAY")
    String getMED_RESPONSE_TIME_PER_DAY = "0";
    // Object getDATA_COLUMN_ARRAY();
}
