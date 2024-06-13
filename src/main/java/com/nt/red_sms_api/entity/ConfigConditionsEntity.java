package com.nt.red_sms_api.entity;


import java.sql.Clob;
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
@Table (name = "config_conditions", schema="reddbsms")
public class ConfigConditionsEntity {
        
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_conditions_seq")
        @SequenceGenerator(name = "config_conditions_seq", allocationSize = 1)
        @Column(name = "CONDITIONS_ID")
        private Long conditionsID;

        @Column(name = "ORDER_TYPE_MAINID", unique = false,nullable = true)
        private Long order_type_MainID = null;
        
        @Column(name = "ORDERTYPE", unique = false,nullable = true)
        private String orderType = null;

        @Column(name = "REFID", unique = false,nullable = true)
        private String refID = null;

        @Column(name = "DATE_START", unique = false,nullable = true)
        private Timestamp date_Start = null;

        @Column(name = "DATE_END", unique = false,nullable = true)
        private Timestamp date_End = null;

        @Column(name = "MESSAGE", unique = false,nullable = true)
        private String message = null;

        @Column(name = "CONDITIONS_OR", unique = false,nullable = true)
        private Clob conditions_or = null;

        @Column(name = "CONDITIONS_AND", unique = false,nullable = true)
        private Clob conditions_and = null;

        @Column(name = "created_Date", unique = false,nullable = true)
        private Timestamp created_Date = null;

        @Column(name = "CREATED_BY", unique = false,nullable = true)
        private String created_By = null;

        @Column(name = "UPDATED_DATE", unique = false,nullable = true)
        private Timestamp updated_Date = null;

        @Column(name = "UPDATED_BY", unique = false,nullable = true)
        private String updated_By = null;

        @Column(name = "IS_DELETE", unique = false,nullable = true)
        private Integer is_delete = 0;

        @Column(name = "IS_ENABLE", unique = false,nullable = true)
        private Integer is_enable = 1;

        @Column(name = "IS_DELETE_BY", unique = false,nullable = true)
        private String is_Delete_By = null;

        @Column(name = "IS_DELETE_DATE", unique = false,nullable = true)
        private Timestamp is_Delete_Date = null;

        @Column(name = "conditions_or_select", unique = false,nullable = true)
        private Clob conditions_or_select = null;

        @Column(name = "conditions_and_select", unique = false,nullable = true)
        private Clob conditions_and_select = null;

        
}
