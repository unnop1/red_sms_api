package com.nt.red_sms_api.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserPermissionReq {
    @JsonProperty("user_id")
    private Long userID;

    @JsonProperty("permission_id")
    private Long permissionID;
}
