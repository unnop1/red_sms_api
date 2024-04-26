package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.req.datamodel.ListDataModelReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;

public interface DataModelService {
    public PaginationDataResp ListAllDataModel(ListDataModelReq req);
}
