package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.dto.resp.OrderTypeResponseDto;
import com.nt.red_sms_api.enitiy.OrderTypeEntity;
import com.nt.red_sms_api.service.OrderTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/order_types")
public class OrderTypeController {
    @Autowired
    private OrderTypeService orderTypeService;
    @GetMapping
    public ResponseEntity<List<OrderTypeEntity>> getAllSmsConditions(){
        return new ResponseEntity<>( orderTypeService.ListAllOrderType(), HttpStatus.OK);
    }

}
