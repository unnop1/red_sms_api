package com.nt.red_sms_api.repo.view.user;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.UserEntity;
import com.nt.red_sms_api.entity.view.user.ListUser;

public interface ListUserRepo extends JpaRepository<UserEntity,Long> {
    
    @Query(value = """
                    SELECT u.id, u.username as username, u.is_enable as is_enable, u.name as name , u.departmentname as departmentname, u.created_date as created_date, u.last_login as last_login, sa_pm.permission_name as permission_name
                    FROM user_db u 
                    LEFT JOIN sa_menu_permission sa_pm 
                    ON (u.sa_menu_permission_id = sa_pm.id) 
                """,
                 nativeQuery = true)
    public List<ListUser> getAllUser(Pageable pageable);

    @Query(value = "SELECT  COUNT(*) FROM  user_db ", nativeQuery = true)
    public Integer getTotalCount();

    @Query(value = """
                    SELECT u.id, u.username as username, u.is_enable as is_enable, u.name as name , u.departmentname as departmentname, u.created_date as created_date, u.last_login as last_login, sa_pm.permission_name as permission_name
                    FROM user_db u 
                    LEFT JOIN sa_menu_permission sa_pm 
                    ON (u.sa_menu_permission_id = sa_pm.id)
                    WHERE ( u.username like %:search% OR u.name like %:search% OR u.departmentname like %:search% OR sa_pm.permission_name like %:search% )
                """,
                 nativeQuery = true)
    public List<ListUser> getListUserAllSearch(@Param(value = "search") String search, Pageable pageable);

    @Query(value = """
                    SELECT  COUNT(*) FROM  user_db u
                    WHERE ( u.username like %:search% OR u.name like %:search% OR u.departmentname like %:search% OR sa_pm.permission_name like %:search% )
                    """
                    , nativeQuery = true)
    public Integer getListUserAllSearchTotalCount(@Param(value = "search") String search);

    @Query(value = """
                    SELECT u.id, u.username as username, u.is_enable as is_enable, u.name as name , u.departmentname as departmentname, u.created_date as created_date, u.last_login as last_login, sa_pm.permission_name as permission_name
                    FROM user_db u 
                    LEFT JOIN sa_menu_permission sa_pm 
                    ON (u.sa_menu_permission_id = sa_pm.id)
                    WHERE ( :search_field like %:search%  )
                """,
                 nativeQuery = true)
    public List<ListUser> getListUserOneSearch(
        @Param(value = "search_field") String searchField,
        @Param(value = "search") String search, 
        Pageable pageable
    );

    @Query(value = """
                    SELECT  COUNT(*) FROM  user_db u
                    WHERE ( :search_field like %:search%  )
                    """
                    , nativeQuery = true)
    public Integer getListUserOneSearchTotalCount(                                                        
        @Param(value = "search") String search
    );

}
