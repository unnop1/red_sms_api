package com.nt.red_sms_api.enitiy;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@Table (name = "SA_LOG_LOGIN")
public class LogLoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @Column(name = "log_id")
    private Long log_id;

    @Column(name = "username", unique = false,nullable = true)
    private String username;

    @Column(name = "password", unique = false,nullable = true)
    private String password;

    @Column(name = "ip_address", unique = false,nullable = true)
    private String ip_address;

    @Column(name = "device", unique = false,nullable = true)
    private String device;
    
    @Column(name = "browser", unique = false,nullable = true)
    private String browser;

    @Column(name = "system", unique = false,nullable = true)
    private String system;

    @Column(name = "isDisable", unique = false,nullable = true)
    private Integer isDisable=0;

    @Column(name = "login_datetime", unique = false,nullable = true)
    private Timestamp login_datetime;

    @Column(name = "create_date", unique = false,nullable = true)
    private Timestamp create_date;

}
