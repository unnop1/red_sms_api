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
@Table (name = "config_conditions")
public class ConfigConditionsEntity {
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "conditionsID")
        private Long conditionsID = null;

        @Column(name = "order_type_MainID", unique = false,nullable = true)
        private Long order_type_MainID = null;
        
        @Column(name = "orderType", unique = false,nullable = true)
        private String orderType = null;

        @Column(name = "refID", unique = false,nullable = true)
        private String refID = null;

        @Column(name = "dateStart", unique = false,nullable = true)
        private Timestamp dateStart = null;

        @Column(name = "dateEnd", unique = false,nullable = true)
        private Timestamp dateEnd = null;

        @Column(name = "message", unique = false,nullable = true)
        private String message = null;

        @Column(name = "messageRaw", unique = false,nullable = true)
        private String messageRaw = null;

        @Column(name = "conditions_or", unique = false,nullable = true)
        private String conditions_or = null;

        @Column(name = "conditions_and", unique = false,nullable = true)
        private String conditions_and = null;

        @Column(name = "createdDate", unique = false,nullable = true)
        private Timestamp createdDate = null;

        @Column(name = "createdBy_userID", unique = false,nullable = true)
        private Long createdBy_userID = null;

        @Column(name = "updatedDate", unique = false,nullable = true)
        private Timestamp updatedDate = null;

        
}
