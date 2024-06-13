package com.nt.red_sms_api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity,Long> {

    @Query(value = "SELECT * FROM user_db WHERE id=?1", nativeQuery = true)
    public UserEntity findByID(Long userID);

    @Query(value = "SELECT * FROM user_db WHERE id IN (?1)", nativeQuery = true)
    public List<UserEntity> findInID(List<Long> userIDs);


    @Query(value = "SELECT * FROM user_db WHERE email=?1", nativeQuery = true)
    public UserEntity findByEmail(String email);

    @Query(value = "SELECT * FROM user_db WHERE username=?1", nativeQuery = true)
    public UserEntity findByUsername(String username);

     
    @Query(value = "SELECT * FROM user_db WHERE email=?1 AND username=?2", nativeQuery = true)
    public UserEntity loadByUniqueUser(String email, String username);

    @Query(value = "SELECT * FROM user_db WHERE email=?1 OR username=?1 AND is_enable=1", nativeQuery = true)
    public UserEntity findLoginUser(String username);

}
