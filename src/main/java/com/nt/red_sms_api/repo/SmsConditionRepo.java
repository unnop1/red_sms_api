package com.nt.red_sms_api.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.ConfigConditionsEntity;

import java.util.List;
import java.util.Optional;

public interface SmsConditionRepo extends JpaRepository<ConfigConditionsEntity,Long> {

    @Query(value = "SELECT * FROM config_conditions WHERE CONDITIONS_ID=?1 ", nativeQuery = true)
    public ConfigConditionsEntity findSmsConditionByID(Long smsID);

    @Query(value = "SELECT * FROM config_conditions WHERE CONDITIONS_ID=?1 AND IS_ENABLE=?2 ", nativeQuery = true)
    public ConfigConditionsEntity findByIdAndEnable(Long smsID, Integer isEnable);

    @Query(value = "SELECT * FROM config_conditions OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY ", nativeQuery = true)
    public List<ConfigConditionsEntity> findAll(Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM config_conditions ", nativeQuery = true)
    public Integer getTotalCount();

    @Query(value = "SELECT * FROM ( SELECT * FROM config_conditions ORDER BY CONDITIONS_ID DESC ) WHERE ROWNUM = 1 ", nativeQuery = true)
    public ConfigConditionsEntity getLastID();
    

}
