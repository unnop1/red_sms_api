package com.nt.red_sms_api.dto.resp;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDataResp {   
    private Integer count;

    private Object data;

}
