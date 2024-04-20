package com.nt.red_sms_api.service;

import java.util.HashMap;
import com.nt.red_sms_api.dto.req.AddPermissionReq;
import com.nt.red_sms_api.dto.req.Vue.PermissionListReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.PermissionMenuEntity;


public interface PermissionMenuService {
    PaginationDataResp ListMenuPermission(PermissionListReq req);
    PermissionMenuEntity getMenuPermission(Long permissionID);
    Void addSaMenuPermission(AddPermissionReq req, String createdBy);
    Void updatePermission(Long permissionID, HashMap<String, Object> updateInfo, String updatedBy);
    Void removePermission(Long permissionID);
}
