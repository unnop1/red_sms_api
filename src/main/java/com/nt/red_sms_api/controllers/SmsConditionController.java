package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.dto.req.SmsConditionMoreDetailReq;
import com.nt.red_sms_api.dto.req.UpdateByIdReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.enitiy.ConfigConditionsEntity;
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
    public ResponseEntity<List<ConfigConditionsEntity>> getAllSmsConditions(Integer page, Integer limit){
        return new ResponseEntity<>( smsConditionService.ListAllSmsCondition(page, limit), HttpStatus.OK);
    }

    @PostMapping("/by_id")
    public ResponseEntity<DefaultControllerResp> GetSmsConditionMoreDetail(@RequestBody SmsConditionMoreDetailReq req) throws Exception{
        ConfigConditionsEntity smsDetail = smsConditionService.getSmsConditionMoreDetail(req.getSmsID());
        DefaultControllerResp response = new DefaultControllerResp();
        if(smsDetail.getConditionsID() != null){ 
            response.setCount(1);
            response.setMessage("Success");
            response.setData(smsDetail);
            response.setStatusCode(200);
        }else{
            response.setCount(0);
            response.setMessage("Error");
            response.setData(smsDetail);
            response.setStatusCode(400);
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<DefaultControllerResp> updateSmsConditionById(@RequestBody UpdateByIdReq req) throws Exception{
        
        smsConditionService.updateSmsConditionById(req.getUpdateID(), req.getUpdateInfo());

        DefaultControllerResp response = new DefaultControllerResp();
        
        response.setCount(1);
        response.setMessage("Success");
        response.setData(req.getUpdateInfo());
        response.setStatusCode(200);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
