package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.enitiy.SmsConditionEntity;
import com.nt.red_sms_api.service.SmsConditionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/sms_conditions")
public class SmsConditionController {
    @Autowired
    private SmsConditionService smsConditionService;
    @GetMapping
    public ResponseEntity<List<SmsConditionEntity>> getAllSmsConditions(){
        return new ResponseEntity<>( smsConditionService.ListAllSmsCondition(), HttpStatus.OK);
    }

}
