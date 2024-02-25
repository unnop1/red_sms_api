package com.nt.red_sms_api.dto.req;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateByIdReq {
    @JsonProperty("update_id")
    private Long updateID;

    @JsonProperty("update_info")
    private Map<String, Object> updateInfo;
}
