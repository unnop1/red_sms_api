package com.nt.red_sms_api.repo;

import com.nt.red_sms_api.enitiy.ConfigConditionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SmsConditionRepo extends JpaRepository<ConfigConditionsEntity,Long> {

    @SuppressWarnings("null")
    @Query(value = "SELECT * FROM config_conditions WHERE conditionsID=?1", nativeQuery = true)
    public Optional<ConfigConditionsEntity> findById(Long smsID);

    @SuppressWarnings("null")
    @Query(value = "SELECT * FROM config_conditions LIMIT ?1, ?2", nativeQuery = true)
    public List<ConfigConditionsEntity> findAll(Integer page, Integer limit);

}
