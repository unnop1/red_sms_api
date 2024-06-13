package com.nt.red_sms_api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.DataModelEntity;

public interface DataModelRepo extends JpaRepository<DataModelEntity,Long> {
    
    @Query(value = "SELECT * FROM data_model_template ORDER BY id ASC", nativeQuery = true)
    public List<DataModelEntity> ListAll();

}
