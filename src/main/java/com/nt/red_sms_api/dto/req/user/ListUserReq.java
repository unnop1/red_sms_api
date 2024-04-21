package com.nt.red_sms_api.dto.req.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListUserReq {

        @JsonProperty("draw")
        private Integer draw=11;

        @JsonProperty("order[0][dir]")
        private String sortBy="ASC";

        @JsonProperty("order[0][name]")
        private String sortName="";

        @JsonProperty("start")
        private Integer start;

        @JsonProperty("length")
        private Integer length=10;

        @JsonProperty("Search")
        private String Search=null;
}
