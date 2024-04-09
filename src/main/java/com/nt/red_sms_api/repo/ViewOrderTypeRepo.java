package com.nt.red_sms_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nt.red_sms_api.entity.ViewOrderTypeEntity;
import java.util.List;

public interface ViewOrderTypeRepo extends JpaRepository<ViewOrderTypeEntity,Long> {
    
    @Query(value = "SELECT  odt.*,  (SELECT COUNT(sms_gw.GID)   FROM sms_gateway sms_gw   WHERE odt.MainID = sms_gw.order_type_ID) as TotleMsg,  (SELECT COUNT(sms_gw.GID)   FROM sms_gateway sms_gw   WHERE odt.MainID = sms_gw.order_type_ID   AND Is_Status = 1) as TotleSend  FROM  order_type odt  WHERE  Is_Delete = 0 OFFSET 1 ROWS FETCH NEXT 8 ROWS ONLY", nativeQuery = true)
    public List<ViewOrderTypeEntity> findAll(Integer page, Integer limit);

}
