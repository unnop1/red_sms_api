package com.nt.red_sms_api.dto.resp;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthSuccessResp {
    private String token;
}
