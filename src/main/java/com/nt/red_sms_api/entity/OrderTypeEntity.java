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
public class OrderTypeEntity {
        
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_type_seq")
        @SequenceGenerator(name = "order_type_seq", allocationSize = 1)
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
        private String CreatedBy = null;

        @Column(name = "Updated_Date", unique = false,nullable = true)
        private Timestamp UpdatedDate = null;

        @Column(name = "Updated_By", unique = false,nullable = true)
        private Long Updated_By = null;

        // @Column(name = "totlemsg", unique = false,nullable = true)
        // private Integer totlemsg = 0;

        // @Column(name = "totlesend", unique = false,nullable = true)
        // private Integer totlesend = 0;
}
