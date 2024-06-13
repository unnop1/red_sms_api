package com.nt.red_sms_api.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.nt.red_sms_api.entity.OrderTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTypeRepo extends JpaRepository<OrderTypeEntity,Long> {

    @Query(value = "SELECT * FROM order_type WHERE TYPEID=?1", nativeQuery = true)
    public Optional<OrderTypeEntity> findById(Long orderTypeID);

    @Query(value = "SELECT * FROM order_type WHERE MAINID=?1", nativeQuery = true)
    public OrderTypeEntity findByMainId(Long orderTypeMainID);
}
