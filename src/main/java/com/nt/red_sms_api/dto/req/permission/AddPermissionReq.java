package com.nt.red_sms_api.dto.req.permission;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPermissionReq {
    @JsonProperty("permission_Name")
    private String permissionName;

    @JsonProperty("permission_json")
    private String permission_json;

    @JsonProperty("user_ids")
    private List<Long> userIds;
}
