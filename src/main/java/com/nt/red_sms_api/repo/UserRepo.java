package com.nt.red_sms_api.repo;

import com.nt.red_sms_api.enitiy.UserEnitiy;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<UserEnitiy,Long> {

    public UserEnitiy findByEmail(String email);

}
