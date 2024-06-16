package com.nt.red_sms_api.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Clob;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table (name = "SA_MENU_PERMISSION", schema="reddbsms")
public class PermissionMenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sa_menu_permission_seq")
    @SequenceGenerator(name = "sa_menu_permission_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PERMISSION_NAME", unique = false,nullable = true)
    private String permission_Name=null;

    @Lob
    @Column(name = "PERMISSION_JSON", unique = false,nullable = true)
    private String permission_json=null;

    @Column(name = "CREATED_DATE", unique = false,nullable = true)
    private Timestamp created_Date=null;
    
    @Column(name = "CREATED_BY", unique = false,nullable = true)
    private String created_By=null;

    @Column(name = "UPDATED_DATE", unique = false,nullable = true)
    private Timestamp updated_Date=null;

    @Column(name = "UPDATED_BY", unique = false,nullable = true)
    private String updated_By=null;
}
