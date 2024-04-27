package com.nt.red_sms_api.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.red_sms_api.dto.req.datamodel.ListDataModelReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.DataModelEntity;
import com.nt.red_sms_api.repo.DataModelRepo;
import com.nt.red_sms_api.service.DataModelService;

@Service
public class DataModelImp implements DataModelService {
    @Autowired
    private DataModelRepo dataModelRepo;

    @Override
    public PaginationDataResp ListAllDataModel(ListDataModelReq req) {
        PaginationDataResp pageResp = new PaginationDataResp();
        List<DataModelEntity> resp = dataModelRepo.ListAll();
        pageResp.setCount(resp.size());
        pageResp.setData(resp);
        return pageResp;
    }

    
}
