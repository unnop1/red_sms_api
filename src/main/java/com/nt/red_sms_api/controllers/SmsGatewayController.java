package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.dto.req.SmsGwOdtReq;
import com.nt.red_sms_api.dto.resp.OrderTypeResponseDto;
import com.nt.red_sms_api.enitiy.SmsGatewayEntity;
import com.nt.red_sms_api.service.OrderTypeService;
import com.nt.red_sms_api.service.SmsGatewayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/sms_gateways")
public class SmsGatewayController {
    @Autowired
    private SmsGatewayService smsGatewayService;
    @PostMapping("/order_type_with_status")
    public ResponseEntity<List<SmsGatewayEntity>> getAllSmsConditions(@RequestBody SmsGwOdtReq req){
        return new ResponseEntity<>( smsGatewayService.findSmsGatewayMatchAndUnMatch(req.getOrderTypeID(), req.getIsStatus()), HttpStatus.OK);
    }

}
