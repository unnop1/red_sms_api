package com.nt.red_sms_api.dto.req.audit;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuditLog {
    @Column(name = "ACTION")
    private String action=null;

    @Column(name = "AUDITABLE_ID")
    private Long auditable_id=null;

    @Column(name = "AUDITABLE")
    private String auditable=null;

    @Column(name = "Comment")
    private String comment=null;

    @Column(name = "IP_ADDRESS")
    private String ip_address=null;

    @Column(name = "USERNAME")
    private String username=null;

    @Column(name = "DEVICE")
    private String device=null;

    @Column(name = "BROWSER")
    private String browser=null;

    @Column(name = "OPERATING_SYSTEM")
    private String operating_system=null;

    @Column(name = "CREATED_DATE")
    private Timestamp created_date=null;
}
