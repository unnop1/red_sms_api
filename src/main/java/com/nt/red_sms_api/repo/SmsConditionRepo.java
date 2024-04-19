package com.nt.red_sms_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.ConfigConditionsEntity;

import java.util.List;
import java.util.Optional;

public interface SmsConditionRepo extends JpaRepository<ConfigConditionsEntity,Long> {

    @SuppressWarnings("null")
    @Query(value = "SELECT * FROM config_conditions WHERE conditions_ID=?1 ", nativeQuery = true)
    public Optional<ConfigConditionsEntity> findById(Long smsID);

    @SuppressWarnings("null")
    @Query(value = "SELECT * FROM config_conditions OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY ", nativeQuery = true)
    public List<ConfigConditionsEntity> findAll(Integer page, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM config_conditions ", nativeQuery = true)
    public Integer getTotalCount();

}
