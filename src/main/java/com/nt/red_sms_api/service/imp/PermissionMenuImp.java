package com.nt.red_sms_api.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.red_sms_api.entity.PermissionMenuEntity;
import com.nt.red_sms_api.entity.UserEnitiy;
import com.nt.red_sms_api.repo.PermissionMenuRepo;
import com.nt.red_sms_api.repo.UserRepo;
import com.nt.red_sms_api.service.PermissionMenuService;

@Service
public class PermissionMenuImp implements PermissionMenuService {

    @Autowired
    private PermissionMenuRepo permissionMenuRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<PermissionMenuEntity> ListMenuPermission(Integer page, Integer limit) {
        Integer offset = (page-1)*limit;
        List<PermissionMenuEntity> permissionMenu = permissionMenuRepo.findAll(offset, limit);
        System.out.println("permissionMenus: " + permissionMenu);
        return permissionMenu;
    }

    @Override
    public PermissionMenuEntity getMenuPermission(Long permissionID) {
        PermissionMenuEntity permissionMenu = permissionMenuRepo.findPermissionById(permissionID);
        System.out.println("permissionMenus: " + permissionMenu);
        return permissionMenu;
    }

    @Override
    public Object addUserSaMenuPermission(Long userID, Long PermissionID) {
        UserEnitiy userPermission = userRepo.findByID(userID);

        userPermission.setSa_permission_id(PermissionID);
        UserEnitiy updaUserEnitiy = userRepo.save(userPermission);    
        System.out.println("updaUserEnitiy: " + updaUserEnitiy);
        return updaUserEnitiy;
    }
    
}
