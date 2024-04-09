package com.nt.red_sms_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.UserEnitiy;


public interface UserRepo extends JpaRepository<UserEnitiy,Long> {
    @SuppressWarnings("null")
    @Query(value = "SELECT * FROM user_db WHERE email=?1", nativeQuery = true)
    public UserEnitiy findByEmail(String email);

}
