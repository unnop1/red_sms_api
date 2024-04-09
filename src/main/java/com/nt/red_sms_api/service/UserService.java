package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.req.UserRequestDto;
import com.nt.red_sms_api.dto.resp.UserResp;
import com.nt.red_sms_api.entity.UserEnitiy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    List<UserResp> getAllUser();
    public UserResp createUser(UserRequestDto userRequestDto);
    void updateUser(Long userID, HashMap<String, Object> updateInfo);
    UserEnitiy loadUniqueUser(String email, String username) throws UsernameNotFoundException;
}
