package com.nt.red_sms_api.dto.resp;

import java.util.List;

import com.nt.red_sms_api.entity.view.sms_gateway.ByResponseReportTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsGatewayResponseTimeReportResp {

    private List<ByResponseReportTime> data;
}
