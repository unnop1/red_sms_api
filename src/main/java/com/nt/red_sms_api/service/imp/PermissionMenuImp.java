package com.nt.red_sms_api.service.imp;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;

import com.nt.red_sms_api.dto.req.permission.AddPermissionReq;
import com.nt.red_sms_api.dto.req.permission.PermissionListReq;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.PermissionMenuEntity;
import com.nt.red_sms_api.entity.UserEntity;
import com.nt.red_sms_api.entity.view.permission.ListPermissionTotalUser;
import com.nt.red_sms_api.repo.PermissionMenuRepo;
import com.nt.red_sms_api.repo.UserRepo;
import com.nt.red_sms_api.repo.view.permission.ListPermissionTotalUserRepo;

import java.sql.Timestamp;
import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.service.PermissionMenuService;

@Service
public class PermissionMenuImp implements PermissionMenuService {

    @Autowired
    private PermissionMenuRepo permissionMenuRepo;

    @Autowired
    private ListPermissionTotalUserRepo viewPermissionMenuRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public PaginationDataResp ListMenuPermission(PermissionListReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        String searchName = req.getSearch();
        String sort = "sa_pm."+req.getSortName() + " " + req.getSortBy();
        System.out.println("VueListReq: " + req.toString());
        List<ListPermissionTotalUser> permissionMenu = viewPermissionMenuRepo.findByQuery(
            searchName, sort, offset, limit
        );
        Integer count = viewPermissionMenuRepo.getFindQueryTotalCount(searchName);
        resp.setData(permissionMenu);
        resp.setCount(count);
        // System.out.println("permissionMenus: " + permissionMenu);
        return resp;
    }

    @Override
    public PermissionMenuEntity getMenuPermission(Long permissionID) {
        PermissionMenuEntity permissionMenu = permissionMenuRepo.findPermissionById(permissionID);
        // System.out.println("permissionMenus: " + permissionMenu);
        return permissionMenu;
    }

    @Override
    public Void addSaMenuPermission(AddPermissionReq req, String createdBy) {
        Timestamp timeNow = DateTime.getTimeStampNow();
        PermissionMenuEntity newPermissionMenu = new PermissionMenuEntity();
        newPermissionMenu.setPermission_Name(req.getPermissionName());
        newPermissionMenu.setPermission_json(req.getPermission_json());
        newPermissionMenu.setCreated_By(createdBy);
        newPermissionMenu.setCreated_Date(timeNow);
        PermissionMenuEntity created = permissionMenuRepo.save(newPermissionMenu);

        if (!req.getUserIds().isEmpty()){
            // Add user permissions
            List<UserEntity> existUser = userRepo.findInID(req.getUserIds());
            // System.out.println("created.getId : " +created.getId()+1);
            for (UserEntity user : existUser){
                user.setSa_menu_permission_id(created.getId()+1);
                userRepo.save(user);
            }
        }
        return null;
    }

    @Override
    public Void updatePermission(Long permissionID, HashMap<String, Object> updateInfo, String updatedBy) {
        PermissionMenuEntity existingEntity = permissionMenuRepo.findById(permissionID).orElse(null);
        // If the entity exists
        if (existingEntity != null) {
            Timestamp timeNow = DateTime.getTimeStampNow();
            // System.out.println("existingEntity ID: " + existingEntity.getId());
            // Iterate over the entries of the updates map
            for (Map.Entry<String, Object> entry : updateInfo.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();

                try {
                    // Get the field from the entity class
                    Field field = PermissionMenuEntity.class.getDeclaredField(fieldName);
                    // Set the accessibility of the field to true if it's not already accessible
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    // Set the value of the field in the entity
                    field.set(existingEntity, value);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // Handle any exceptions (e.g., field not found, access violation)
                    e.printStackTrace();
                }
            }

            existingEntity.setUpdated_Date(timeNow);
            existingEntity.setUpdated_By(updatedBy);

            // Save the updated entity back to the database
            permissionMenuRepo.save(existingEntity);
        }

        return null;
    }

    @Override
    public Void removePermission(Long permissionID) {
        permissionMenuRepo.deleteById(permissionID);
        return null;
    }
    
}
