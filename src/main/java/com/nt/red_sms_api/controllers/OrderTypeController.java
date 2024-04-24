package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.service.OrderTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/ordertype")
public class OrderTypeController {
    @Autowired
    private OrderTypeService orderTypeService;
    @GetMapping
    public ResponseEntity<PaginationDataResp> getAllSmsConditions(@RequestParam(name="page", defaultValue = "1") Integer page,@RequestParam(name="limit", defaultValue = "10") Integer limit){
        return new ResponseEntity<>( orderTypeService.ListAllOrderType(page, limit), HttpStatus.OK);
    }
    
}
