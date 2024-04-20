package com.nt.red_sms_api.Util;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FromToTimeStamp {
    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

}
