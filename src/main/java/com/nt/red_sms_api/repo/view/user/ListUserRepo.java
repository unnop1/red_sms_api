package com.nt.red_sms_api.repo.view.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.UserEntity;
import com.nt.red_sms_api.entity.view.user.ListUser;

public interface ListUserRepo extends JpaRepository<UserEntity,Long> {
    
    @Query(value = "SELECT u.username, u.name, u.departmentname, u.created_date, u.last_login, sa_pm.permission_name FROM user_db u LEFT JOIN sa_menu_permission sa_pm ON (u.sa_menu_permission_id = sa_pm.id) ORDER BY u.created_date DESC  OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY",
                 nativeQuery = true)
    public List<ListUser> getAllUser(Integer offset, Integer limit);

    @Query(value = "SELECT  COUNT(*) FROM  user_db ", nativeQuery = true)
    public Integer getTotalCount();

}
