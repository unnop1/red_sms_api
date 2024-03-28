package com.nt.red_sms_api.service;

import com.nt.red_sms_api.enitiy.PermissionMenuEntity;


public interface PermissionMenuService {
    PermissionMenuEntity getUserMenuPermission(Long userId);
}
