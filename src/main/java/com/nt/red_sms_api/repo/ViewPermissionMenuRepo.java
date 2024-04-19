package com.nt.red_sms_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nt.red_sms_api.entity.ViewPermissionWithTotalUserEntity;

import java.util.List;

public interface ViewPermissionMenuRepo extends JpaRepository<ViewPermissionWithTotalUserEntity,Long> {
    
    @Query(value = "SELECT  sa_pm.*,"+  
                   " (SELECT COUNT(u.ID) FROM user_db u WHERE u.SA_MENU_PERMISSION_ID = sa_pm.ID) as totalUser "+
                   " FROM  sa_menu_permission sa_pm OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY",
                 nativeQuery = true)
    public List<ViewPermissionWithTotalUserEntity> findAll(Integer offset, Integer limit);

    @Query(value = "SELECT  COUNT(*) FROM  sa_menu_permission ", nativeQuery = true)
    public Integer getTotalCount(Integer offset, Integer limit);

}
