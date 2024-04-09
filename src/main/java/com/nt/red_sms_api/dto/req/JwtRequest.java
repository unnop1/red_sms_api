package com.nt.red_sms_api.dto.req;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@ToString
public class JwtRequest {
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("device")
    private String device="Unknown device";
    @JsonProperty("system")
    private String system="Unknown system";
    @JsonProperty("browser")
    private String browser="Unknown browser";
}
