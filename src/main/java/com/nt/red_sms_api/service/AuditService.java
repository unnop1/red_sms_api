package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.req.audit.ListAuditReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;

public interface AuditService {
    public PaginationDataResp ListAllAudit(ListAuditReq req);
}
