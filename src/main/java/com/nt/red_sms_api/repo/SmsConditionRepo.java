package com.nt.red_sms_api.repo;

import com.nt.red_sms_api.enitiy.SmsConditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SmsConditionRepo extends JpaRepository<SmsConditionEntity,Long> {

    @SuppressWarnings("null")
    @Query(value = "SELECT * FROM sms_conditions WHERE SMSID=?1", nativeQuery = true)
    public Optional<SmsConditionEntity> findById(Long smsID);

    @SuppressWarnings("null")
    @Query(value = "SELECT * FROM sms_conditions LIMIT ?1, ?2", nativeQuery = true)
    public List<SmsConditionEntity> findAll(Integer page, Integer limit);

}
