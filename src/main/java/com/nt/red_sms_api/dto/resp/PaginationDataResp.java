package com.nt.red_sms_api.dto.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDataResp {   
    private Integer count;
    private Integer countUnSend=0;
    private Integer countSend=0;
    private Object data;

}
