package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.req.UserRequestDto;
import com.nt.red_sms_api.dto.resp.UserResp;
import com.nt.red_sms_api.entity.UserEnitiy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserResp> getAllUser();
    public UserResp createUser(UserRequestDto userRequestDto);
    void updateUser(String email, HashMap<String, Object> updateInfo);
    UserEnitiy loadUserByUsername(String email) throws UsernameNotFoundException;
    UserEnitiy loadUniqueUser(String email, String username) throws UsernameNotFoundException;
}
