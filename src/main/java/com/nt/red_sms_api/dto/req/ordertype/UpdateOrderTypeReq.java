package com.nt.red_sms_api.dto.req.ordertype;

import java.util.Map;

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
public class UpdateOrderTypeReq {
    @JsonProperty("update_id")
    private Long updateID;

    @JsonProperty("update_info")
    private AddOrderTypeReq updateInfo;
}
