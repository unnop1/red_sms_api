package com.nt.red_sms_api.service;

import com.nt.red_sms_api.dto.req.UserRequestDto;
import com.nt.red_sms_api.dto.resp.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserResponseDto> getAllUser();
    public UserResponseDto createUser(UserRequestDto userRequestDto);
}
