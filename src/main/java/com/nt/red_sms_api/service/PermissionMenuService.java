package com.nt.red_sms_api.service;

import com.nt.red_sms_api.entity.PermissionMenuEntity;


public interface PermissionMenuService {
    PermissionMenuEntity getUserMenuPermission(Long userId);
}
