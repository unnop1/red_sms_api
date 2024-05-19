package com.nt.red_sms_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table (name = "data_model_template")
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

}