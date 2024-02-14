package com.nt.red_sms_api.repo;

import com.nt.red_sms_api.enitiy.SmsConditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmsConditionRepo extends JpaRepository<SmsConditionEntity,Long> {

    public List<SmsConditionEntity> findAll();

}
