package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.dto.resp.OrderTypeResponseDto;
import com.nt.red_sms_api.enitiy.OrderTypeEntity;
import com.nt.red_sms_api.repo.OrderTypeRepo;
import com.nt.red_sms_api.service.OrderTypeService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderTypeImp implements OrderTypeService{

    @Autowired
    private OrderTypeRepo orderTypeRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderTypeEntity> ListAllOrderType() {
        List<OrderTypeEntity> orderTypeEntities = orderTypeRepo.findAll();
        // List<OrderTypeResponseDto> orderTypeResponseDtoList = orderTypeEntities.stream().map(orderType->this.orderTypeEntityToOrderTypeRespDto(orderType)).collect(Collectors.toList());
        return orderTypeEntities;
    }
}
