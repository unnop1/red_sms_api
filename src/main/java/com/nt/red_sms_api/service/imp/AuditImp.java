package com.nt.red_sms_api.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nt.red_sms_api.dto.req.audit.ListAuditReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.AuditEntity;
import com.nt.red_sms_api.repo.AuditRepo;
import com.nt.red_sms_api.service.AuditService;

@Service
public class AuditImp implements AuditService {
    @Autowired
    private AuditRepo auditRepo;

    @Override
    public PaginationDataResp ListAllAudit(ListAuditReq req) {
        PaginationDataResp pageResp = new PaginationDataResp();
        // Integer offset = req.getStart();
        // Integer limit = req.getLength();
        // Integer page = 0;
        // if (limit > 0){
        //     page = offset / limit;
        // }
        String sortName = req.getSortName();
        String sortBy = req.getSortBy();
        // String search = req.getSearch();
        // String searchField = req.getSearchField().toLowerCase();
        String[] orderList = {sortName};
        Sort sort = Sort.by(Sort.Direction.fromString(sortBy), orderList);
            // Sort.Direction.fromString(sortBy), sortName );

        List<AuditEntity> resp = auditRepo.findAll(sort);
        pageResp.setCount(resp.size());
        pageResp.setData(resp);
        return pageResp;
    }

    
}
