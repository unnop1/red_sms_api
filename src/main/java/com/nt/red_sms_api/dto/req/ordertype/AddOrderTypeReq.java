package com.nt.red_sms_api.dto.req.ordertype;

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
public class AddOrderTypeReq {
    @JsonProperty("IsEnable")
    private Integer isEnable;
}
