package com.nt.red_sms_api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.red_sms_api.entity.LogLoginEntity;

public interface LogLoginRepo extends JpaRepository<LogLoginEntity,Long> {
    // List<LogLoginEntity> getLogLogin();
}
