package com.nt.red_sms_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table (name = "data_model_template", schema="reddbsms")
public class DataModelEntity {
        
        @Id
        @Column(name = "id")
        private Long id = null;

        @Column(name = "model_name", unique = false,nullable = true)
        private String model_name = null;

        @Column(name = "data_type", unique = false,nullable = true)
        private String data_type = null;

        @Column(name = "format", unique = false,nullable = true)
        private String format = null;

        @Column(name = "group_data_type", unique = false,nullable = true)
        private String group_data_type = null;

}