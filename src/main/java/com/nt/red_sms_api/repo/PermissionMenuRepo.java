package com.nt.red_sms_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.PermissionMenuEntity;

public interface PermissionMenuRepo extends JpaRepository<PermissionMenuEntity,Long> {

    @Query(value = "SELECT COUNT(*) FROM sa_menu_permission ", nativeQuery = true)
    public Integer getTotalCount();

    @Query(value = "SELECT * FROM sa_menu_permission WHERE ID=?1 ", nativeQuery = true)
    public PermissionMenuEntity findPermissionById(Long saPermissionID);
    
}
