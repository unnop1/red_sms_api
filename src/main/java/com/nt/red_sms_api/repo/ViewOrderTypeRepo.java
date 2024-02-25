package com.nt.red_sms_api.repo;

import com.nt.red_sms_api.enitiy.OrderTypeEntity;
import com.nt.red_sms_api.enitiy.ViewOrderTypeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ViewOrderTypeRepo extends JpaRepository<ViewOrderTypeEntity,Long> {
    
    @Query(value = "SELECT odt.*, (SELECT COUNT(sms_gw.GID) FROM sms_gateway AS sms_gw WHERE odt.MainID = sms_gw.order_type_MainID) as TotleMsg, (SELECT COUNT(sms_gw.GID) FROM sms_gateway AS sms_gw WHERE odt.MainID = sms_gw.order_type_MainID AND IsStatus = 1) as TotleSend FROM order_type AS odt WHERE IsDelete = 0 LIMIT ?1, ?2", nativeQuery = true)
    public List<ViewOrderTypeEntity> findAll(Integer page, Integer limit);

}
