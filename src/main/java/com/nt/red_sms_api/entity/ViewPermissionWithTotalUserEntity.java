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
@Table (name = "sa_menu_permission")
public class ViewPermissionWithTotalUserEntity {
        @Id
        @Column(name = "id")
        private Long id;

        @Column(name = "permission_Name", unique = false,nullable = true)
        private String permissionName=null;

        @Column(name = "permission_json", unique = false,nullable = true)
        private String permission_json=null;

        @Column(name = "created_Date", unique = false,nullable = true)
        private Timestamp createdDate=null;
        
        @Column(name = "created_By", unique = false,nullable = true)
        private String createdBy=null;

        @Column(name = "updated_Date", unique = false,nullable = true)
        private Timestamp updatedDate=null;

        @Column(name = "updated_By", unique = false,nullable = true)
        private String updatedBy=null;

        @Column(name = "totalUser", unique = false,nullable = true)
        private Integer totalUser=0;
}
