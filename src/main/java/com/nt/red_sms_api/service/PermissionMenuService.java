package com.nt.red_sms_api.service;

import java.util.HashMap;
import java.util.List;

import com.nt.red_sms_api.dto.req.AddPermissionReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.PermissionMenuEntity;
import com.nt.red_sms_api.entity.ViewPermissionWithTotalUserEntity;


public interface PermissionMenuService {
    PaginationDataResp ListMenuPermission(Integer offset, Integer limit);
    PermissionMenuEntity getMenuPermission(Long permissionID);
    Void addSaMenuPermission(AddPermissionReq req, String createdBy);
    Void updatePermission(Long permissionID, HashMap<String, Object> updateInfo, String updatedBy);
    Void removePermission(Long permissionID);
}
