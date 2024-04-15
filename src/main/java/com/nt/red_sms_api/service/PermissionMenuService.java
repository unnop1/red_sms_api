package com.nt.red_sms_api.service;

import java.util.List;

import com.nt.red_sms_api.entity.PermissionMenuEntity;


public interface PermissionMenuService {
    List<PermissionMenuEntity> ListMenuPermission(Integer offset, Integer limit);
    PermissionMenuEntity getMenuPermission(Long permissionID);
    Object addUserSaMenuPermission(Long userID, Long PermissionID);
}
