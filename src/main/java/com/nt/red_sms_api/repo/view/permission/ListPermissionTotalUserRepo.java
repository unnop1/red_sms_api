package com.nt.red_sms_api.repo.view.permission;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.PermissionMenuEntity;
import com.nt.red_sms_api.entity.view.permission.ListPermissionTotalUser;

public interface ListPermissionTotalUserRepo extends JpaRepository<PermissionMenuEntity,Long> {

    @Query(value = "SELECT sa_pm.*, " +
               "(SELECT COUNT(id) FROM user_db u WHERE u.SA_MENU_PERMISSION_ID = sa_pm.ID) AS TotalUser " +
               "FROM sa_menu_permission sa_pm ",
       nativeQuery = true)
    public List<ListPermissionTotalUser> GetAllWithTotalUser(
        Pageable pageable
    );

    @Query(value = "SELECT sa_pm.*, " +
               "(SELECT COUNT(id) FROM user_db u WHERE u.SA_MENU_PERMISSION_ID = sa_pm.ID) AS TotalUser " +
               "FROM sa_menu_permission sa_pm ",
       nativeQuery = true)
    public List<ListPermissionTotalUser> GetAllWithTotalUser();

    @Query(value = "SELECT COUNT(*) FROM sa_menu_permission ",
        nativeQuery = true)
    public Integer getGetAllWithTotalUserTotalCount();

    @Query(value = "SELECT sa_pm.*, " +
               "(SELECT COUNT(id) FROM user_db u WHERE u.SA_MENU_PERMISSION_ID = sa_pm.ID) AS TotalUser " +
               "FROM sa_menu_permission sa_pm " +
               "WHERE sa_pm.permission_name like %:search% ",
       nativeQuery = true)
    public List<ListPermissionTotalUser> GetAllWithTotalUserAllLike(
        @Param(value = "search")String search,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) FROM sa_menu_permission sa_pm "+
                    "WHERE sa_pm.permission_name like %:search% ",
        nativeQuery = true)
    public Integer getGetAllWithTotalUserAllLikeTotalCount(
        @Param(value = "search")String search
    );


    @Query(value = "SELECT sa_pm.*, " +
               "(SELECT COUNT(id) FROM user_db u WHERE u.SA_MENU_PERMISSION_ID = sa_pm.ID) AS TotalUser " +
               "FROM sa_menu_permission sa_pm " +
               "WHERE :search_field like %:search% ",
       nativeQuery = true)
    public List<ListPermissionTotalUser> GetAllWithTotalUserLike(
        @Param(value = "search_field")String search_field,    
        @Param(value = "search")String search,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) FROM sa_menu_permission "+
                    "WHERE :search_field like %:search% ",
        nativeQuery = true)
    public Integer getGetAllWithTotalUserLikeTotalCount(
        @Param(value = "search_field")String search_field,    
        @Param(value = "search")String search
    );
    

}
