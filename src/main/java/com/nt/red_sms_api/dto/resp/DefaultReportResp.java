package com.nt.red_sms_api.dto.resp;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultReportResp {
    private int statusCode=0;
    private String message="";
    private Object data=null;
}
