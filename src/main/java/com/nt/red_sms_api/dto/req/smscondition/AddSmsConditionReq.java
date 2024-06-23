package com.nt.red_sms_api.dto.req.smscondition;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AddSmsConditionReq {
    @JsonProperty("date_start")
    private String date_start;

    @JsonProperty("date_end")
    private String date_end;

    @JsonProperty("order_type_main_id")
    private Long order_type_main_id;

    @JsonProperty("conditions_or")
    private String conditions_or;

    @JsonProperty("conditions_and")
    private String conditions_and;

    @JsonProperty("message")
    private String message;

    @JsonProperty("is_enable")
    private Integer is_enable;

    @JsonProperty("conditions_or_select")
    private String CONDITIONS_OR_SELECT;

    @JsonProperty("conditions_and_select")
    private String CONDITIONS_AND_SELECT;
    
    @JsonProperty("is_pdpa")
    private Integer is_pdpa=0;

    @JsonProperty("is_period_time")
    private Integer is_period_time=0;

    @JsonProperty("time_end")
    private String time_end=null;
    
    @JsonProperty("time_start")
    private String time_start=null;

    
}
