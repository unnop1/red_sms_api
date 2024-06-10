package com.nt.red_sms_api.dto.resp;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nt.red_sms_api.entity.UserEntity;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResp {
    @Column
    @JsonProperty("user")
    private UserResp userLogin;

    @Column
    @JsonProperty("access_token")
    private String jwtToken;

    @Column
    @JsonProperty("permission_menu")
    private Object permissionJson;

    @Column
    @JsonProperty("permissionName")    
    private String permissionName;
}
