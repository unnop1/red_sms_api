package com.nt.red_sms_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.PermissionMenuEntity;

public interface PermissionMenuRepo extends JpaRepository<PermissionMenuEntity,Long> {
    
    @Query(value = "SELECT * FROM sa_menu_permission WHERE user_id=?1", nativeQuery = true)
    public PermissionMenuEntity findPermissionByUserId(Long userId);

}
