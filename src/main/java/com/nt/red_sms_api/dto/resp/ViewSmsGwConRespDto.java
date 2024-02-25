package com.nt.red_sms_api.dto.resp;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ViewSmsGwConRespDto {
        @JsonProperty("GID")
        private Long GID = null;

        @JsonProperty("sms_conditions_SMSID")
        private Long sms_conditions_SMSID = null;
        
        @JsonProperty("SMSMessage")
        private String SMSMessage = null;

        @JsonProperty("order_type_MainID")
        private Long order_type_MainID = null;

        @JsonProperty("OrderType")
        private String OrderType = null;

        @JsonProperty("PhoneNumber")
        private String PhoneNumber = null;

        @JsonProperty("serviceType")
        private Integer serviceType = null;

        @JsonProperty("Frequency")
        private String Frequency = null;

        @JsonProperty("Chanel")
        private String Chanel = null;

        @JsonProperty("OfferingId")
        private String OfferingId = null;

        @JsonProperty("PayloadMQ")
        private String PayloadMQ = null;

        @JsonProperty("IsStatus")
        private Integer IsStatus = 0;

        @JsonProperty("CreatedDate")
        private Timestamp CreatedDate = null;

        // //

        @JsonProperty("Message")
        private String Message = null;

        @JsonProperty("by_offeringId")
        private String by_offeringId = null;

        @JsonProperty("DateStart")
        private String DateStart = null;

        @JsonProperty("DateEnd")
        private String DateEnd = null;

        @JsonProperty("IsEnable")
        private Integer IsEnable = 1;

        @JsonProperty("IsDelete")
        private Integer IsDelete = 0;

        @JsonProperty("CreatedBy_UserID")
        private Long CreatedBy_UserID = null;

        @JsonProperty("UpdatedDate")
        private String UpdatedDate = null;

        @JsonProperty("UpdatedBy_UserID")
        private Long UpdatedBy_UserID = null;
}
