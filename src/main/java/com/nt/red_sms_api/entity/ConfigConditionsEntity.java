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
@Table (name = "config_conditions")
public class ConfigConditionsEntity {
        
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_conditions_seq")
        @SequenceGenerator(name = "config_conditions_seq", allocationSize = 1)
        @Column(name = "conditions_ID")
        private Long conditionsID;

        @Column(name = "order_type_MainID", unique = false,nullable = true)
        private Long order_type_MainID = null;
        
        @Column(name = "orderType", unique = false,nullable = true)
        private String orderType = null;

        @Column(name = "refID", unique = false,nullable = true)
        private String refID = null;

        @Column(name = "date_Start", unique = false,nullable = true)
        private Timestamp date_Start = null;

        @Column(name = "date_End", unique = false,nullable = true)
        private Timestamp date_End = null;

        @Column(name = "message", unique = false,nullable = true)
        private String message = null;

        @Column(name = "conditions_or", unique = false,nullable = true)
        private String conditions_or = null;

        @Column(name = "conditions_and", unique = false,nullable = true)
        private String conditions_and = null;

        @Column(name = "created_Date", unique = false,nullable = true)
        private Timestamp created_Date = null;

        @Column(name = "created_By", unique = false,nullable = true)
        private String created_By = null;

        @Column(name = "updated_Date", unique = false,nullable = true)
        private Timestamp updated_Date = null;

        @Column(name = "updated_By", unique = false,nullable = true)
        private String updated_By = null;

        @Column(name = "is_delete", unique = false,nullable = true)
        private Integer is_delete = 0;

        @Column(name = "is_enable", unique = false,nullable = true)
        private Integer is_enable = 1;

        @Column(name = "is_Delete_By", unique = false,nullable = true)
        private String is_Delete_By = null;

        @Column(name = "is_Delete_Date", unique = false,nullable = true)
        private Timestamp is_Delete_Date = null;

        @Column(name = "conditions_or_select", unique = false,nullable = true)
        private String conditions_or_select = null;

        @Column(name = "conditions_and_select", unique = false,nullable = true)
        private String conditions_and_select = null;

        
}
