package com.nt.red_sms_api.enitiy;


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
@Table (name = "ORDER_TYPE")
public class OrderTypeEntity {
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUST_GEN")
        @Column(name = "TYPEID")
        private Long TYPEID = null;

        @Column(name = "MainID", unique = false,nullable = true)
        private Long MainID = null;

        @Column(name = "OrderTypeName", unique = false,nullable = true)
        private String OrderTypeName = null;

        @Column(name = "IsEnable", unique = false,nullable = true)
        private Integer IsEnable = 1;

        @Column(name = "IsDelete", unique = false,nullable = true)
        private Integer IsDelete = 0;

        @Column(name = "CreatedDate", unique = false,nullable = true)
        private Timestamp CreatedDate = null;

        @Column(name = "CreatedBy_UserID", unique = false,nullable = true)
        private Long CreatedBy_UserID = null;

        @Column(name = "UpdatedDate", unique = false,nullable = true)
        private Timestamp UpdatedDate = null;

        @Column(name = "UpdatedBy_UserID", unique = false,nullable = true)
        private Long UpdatedBy_UserID = null;
}
