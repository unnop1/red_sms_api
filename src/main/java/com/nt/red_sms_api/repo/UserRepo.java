package com.nt.red_sms_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.red_sms_api.entity.UserEnitiy;


public interface UserRepo extends JpaRepository<UserEnitiy,Long> {

    public UserEnitiy findByEmail(String email);

}
