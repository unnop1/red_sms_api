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
@Table (name = "DATA_MODEL_TEMPLATE", schema = "${replace_schema}")
public class DataModelEntity {
        
        @Id
        @Column(name = "ID")
        private Long id = null;

        @Column(name = "MODEL_NAME", unique = false,nullable = true)
        private String model_name = null;

        @Column(name = "DATA_TYPE", unique = false,nullable = true)
        private String data_type = null;

        @Column(name = "FORMAT", unique = false,nullable = true)
        private String format = null;

        @Column(name = "GROUP_DATA_TYPE", unique = false,nullable = true)
        private String group_data_type = null;

}