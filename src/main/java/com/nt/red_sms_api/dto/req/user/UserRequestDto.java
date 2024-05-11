package com.nt.red_sms_api.dto.req.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
        @JsonProperty("name")
        private String name=null;

        @JsonProperty("about_me")
        private String aboutMe=null;
        
        @JsonProperty("email")
        private String email;

        @JsonProperty("username")
        private String username;

        @JsonProperty("phone_number")
        private String phonenumber;

        @JsonProperty("password")
        private String password;

        @JsonProperty("department_name")
        private String departmentName=null;

        @JsonProperty("sa_menu_permission_id")
        private Long permissionID;
}
