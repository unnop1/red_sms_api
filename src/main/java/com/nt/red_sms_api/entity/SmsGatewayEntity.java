package com.nt.red_sms_api.entity;


import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table (name = "sms_gateway")
public class SmsGatewayEntity {
        
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_gateway_seq")
        @SequenceGenerator(name = "sms_gateway_seq", allocationSize = 1)
        @Column(name = "GID")
        private Long GID = null;

        @Column(name = "config_conditions_ID", unique = false,nullable = true)
        private Long config_conditions_ID = null;
        
        @Column(name = "SMSMessage", unique = false,nullable = true)
        private String SMSMessage = null;

        @Column(name = "Message_raw", unique = false,nullable = true)
        private String Message_raw = null;

        @Column(name = "order_type_mainID", unique = false,nullable = true)
        private Long order_type_mainID = null;

        @Column(name = "OrderType", unique = false,nullable = true)
        private String OrderType = null;

        @Column(name = "PhoneNumber", unique = false,nullable = true)
        private String PhoneNumber = null;

        @Column(name = "payloadMQ", unique = false,nullable = true)
        private Integer serviceType = null;

        @Column(name = "payloadGW", unique = false,nullable = true)
        private String OfferingId = null;

        @Column(name = "PayloadMQ", unique = false,nullable = true)
        private String PayloadMQ = null;

        @Column(name = "Is_Status", unique = false,nullable = true)
        private Integer IsStatus = 0;

        @Column(name = "Remark", unique = false,nullable = true)
        private String Remark = null;

        @Column(name = "Created_Date", unique = false,nullable = true)
        private Timestamp CreatedDate = null;
}
