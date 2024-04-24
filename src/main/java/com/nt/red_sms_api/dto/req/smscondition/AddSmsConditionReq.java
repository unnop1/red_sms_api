package com.nt.red_sms_api.dto.req.smscondition;

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

    @JsonProperty("ref_id")
    private String refID;
}
