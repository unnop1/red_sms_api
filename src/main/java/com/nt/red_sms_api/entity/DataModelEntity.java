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
@Table (name = "data_model_template")
public class DataModelEntity {
        
        @Id
        @Column(name = "id")
        private Long id = null;

        @Column(name = "model_name", unique = false,nullable = true)
        private String model_name = null;

}