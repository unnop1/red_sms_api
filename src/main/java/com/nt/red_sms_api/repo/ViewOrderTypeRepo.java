package com.nt.red_sms_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nt.red_sms_api.entity.ViewOrderTypeEntity;
import java.util.List;

public interface ViewOrderTypeRepo extends JpaRepository<ViewOrderTypeEntity,Long> {
    
    @Query(value = "SELECT  odt.*,  (SELECT COUNT(sms_gw.GID)   FROM sms_gateway sms_gw   WHERE odt.MainID = sms_gw.order_type_mainID AND ( sms_gw.CREATED_DATE >= trunc(sysdate)  OR sms_gw.CREATED_DATE IS NULL) ) as TotleMsg,  (SELECT COUNT(sms_gw.GID)   FROM sms_gateway sms_gw   WHERE odt.MainID = sms_gw.order_type_mainID   AND Is_Status = ?3 AND ( sms_gw.CREATED_DATE >= trunc(sysdate)  OR sms_gw.CREATED_DATE IS NULL)) as TotleSend  FROM  order_type odt  WHERE  Is_Delete = 0 OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY", nativeQuery = true)
    public List<ViewOrderTypeEntity> findAll(Integer offset, Integer limit, Integer isStatus);

}
