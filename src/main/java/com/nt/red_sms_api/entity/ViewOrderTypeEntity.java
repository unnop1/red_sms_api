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
@Table (name = "order_type")
public class ViewOrderTypeEntity {
        
        @Id
        @Column(name = "TYPEID")
        private Long TYPEID = null;

        @Column(name = "MainID", unique = false,nullable = true)
        private Long MainID = null;

        @Column(name = "OrderType_Name", unique = false,nullable = true)
        private String OrderTypeName = null;

        @Column(name = "Is_Enable", unique = false,nullable = true)
        private Integer IsEnable = 1;

        @Column(name = "Is_Delete", unique = false,nullable = true)
        private Integer IsDelete = 0;

        @Column(name = "Created_Date", unique = false,nullable = true)
        private Timestamp CreatedDate = null;

        @Column(name = "Created_By", unique = false,nullable = true)
        private Long CreatedBy = null;

        @Column(name = "Updated_Date", unique = false,nullable = true)
        private Timestamp UpdatedDate = null;

        @Column(name = "Updated_By", unique = false,nullable = true)
        private Long UpdatedBy = null;

        @Column(name = "totalmsg", unique = false,nullable = true)
        private Integer totalmsg = 0;

        @Column(name = "totalsend", unique = false,nullable = true)
        private Integer totalsend = 0;

        @Column(name = "totalunsend", unique = false,nullable = true)
        private Integer totalunsend = 0;
}
