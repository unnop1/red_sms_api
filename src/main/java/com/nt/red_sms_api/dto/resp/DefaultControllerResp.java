package com.nt.red_sms_api.dto.resp;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultControllerResp {
    private int statusCode;
    private int count;
    private String message;
    private Object data;
}
