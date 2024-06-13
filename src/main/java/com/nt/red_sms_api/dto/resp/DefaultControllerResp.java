package com.nt.red_sms_api.dto.resp;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultControllerResp {
    private int statusCode=0;
    private int count=0;
    private int countUnSend=0;
    private int countSend=0;
    private String message="";
    private Object data=null;
    private Integer draw=11;
    private Integer recordsTotal=0;
    private Integer recordsFiltered=0;
}
