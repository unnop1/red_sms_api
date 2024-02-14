package com.nt.red_sms_api.repo;

import com.nt.red_sms_api.enitiy.UserEnitiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEnitiy,Long> {

    public Optional<UserEnitiy> findByEmail(String email);

}
