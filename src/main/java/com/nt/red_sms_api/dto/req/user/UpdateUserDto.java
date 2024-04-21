package com.nt.red_sms_api.dto.req.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
        @JsonProperty("user_id")
        private Long id;

        @JsonProperty("name")
        private String name="";

        @JsonProperty("about_me")
        private String aboutMe="";
        
        @JsonProperty("email")
        private String email="";

        @JsonProperty("phone_number")
        private String phonenumber="";

        @JsonProperty("password")
        private String password="";

        @JsonProperty("department_name")
        private String departmentName="";

        @JsonProperty("permission_id")
        private Long permissionID=null;
}
