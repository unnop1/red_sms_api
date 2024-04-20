package com.nt.red_sms_api.repo.view.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.view.permission.ListPermissionTotalUser;

import java.util.List;

public interface ListPermissionTotalUserRepo extends JpaRepository<ListPermissionTotalUser,Long> {
    
    @Query(value = "SELECT  sa_pm.*,"+  
                   " (SELECT COUNT(u.ID) FROM user_db u WHERE u.SA_MENU_PERMISSION_ID = sa_pm.ID) as totalUser "+
                   " FROM  sa_menu_permission sa_pm OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY",
                 nativeQuery = true)
    public List<ListPermissionTotalUser> findAll(Integer offset, Integer limit);

    @Query(value = "SELECT  COUNT(*) FROM  sa_menu_permission ", nativeQuery = true)
    public Integer getTotalCount(Integer offset, Integer limit);

    @Query(value = "SELECT sa_pm.*, " +
               "(SELECT COUNT(u.ID) " +
               "FROM user_db u " +
               "WHERE u.SA_MENU_PERMISSION_ID = sa_pm.ID) AS totalUser " +
               "FROM sa_menu_permission sa_pm " +
               "WHERE sa_pm.PERMISSION_NAME = ?1 " +
               "ORDER BY ?2 " +
               "OFFSET ?3 ROWS " +
               "FETCH NEXT ?4 ROWS ONLY ",
       nativeQuery = true)
    public List<ListPermissionTotalUser> findByQuery(String searchName,
                                                                String sort,
                                                                Integer offset,
                                                                Integer limit);

    @Query(value = "SELECT COUNT(*) FROM sa_menu_permission " +
                   "WHERE PERMISSION_NAME = ?1 ",
        nativeQuery = true)
    public Integer getFindQueryTotalCount(String searchName);

}
