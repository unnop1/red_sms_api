package com.nt.red_sms_api.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.red_sms_api.enitiy.OrderTypeEntity;
import com.nt.red_sms_api.enitiy.ViewOrderTypeEntity;

public interface OrderTypeRepo extends JpaRepository<OrderTypeEntity,Long> {

    @SuppressWarnings("null")
    @Query(value = "SELECT * FROM order_type WHERE TYPEID=?1", nativeQuery = true)
    public Optional<OrderTypeEntity> findById(Long orderTypeID);
}
