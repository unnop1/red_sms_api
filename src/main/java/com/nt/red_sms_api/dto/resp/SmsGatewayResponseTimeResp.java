package com.nt.red_sms_api.dto.resp;

import java.util.List;

import com.nt.red_sms_api.entity.view.sms_gateway.date.ByResponseTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsGatewayResponseTimeResp {
    private Integer count;
    private Integer countUnSend=0;
    private Integer countSend=0;
    private List<ByResponseTime> data;
}
