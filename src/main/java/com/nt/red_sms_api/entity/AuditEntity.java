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
@Table (name = "audit")
public class AuditEntity {
        
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_conditions_seq")
        @SequenceGenerator(name = "config_conditions_seq", allocationSize = 1)
        @Column(name = "conditions_ID")
        private Long conditionsID;

        @Column(name = "order_type_MainID", unique = false,nullable = true)
        private Long order_type_MainID = null;
        
        @Column(name = "orderType", unique = false,nullable = true)
        private Timestamp orderType = null;
}