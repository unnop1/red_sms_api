package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.dto.req.UpdateByIdReq;
import com.nt.red_sms_api.dto.req.smscondition.ListConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.SmsConditionMoreDetailReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.ConfigConditionsEntity;
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
    public ResponseEntity<DefaultControllerResp> getAllSmsConditions(
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "created_date")String sortName,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String search_field
    ){
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            ListConditionReq req = new ListConditionReq(draw, sortBy, sortName, startTime, endTime,  start, length, search, search_field);
            PaginationDataResp smsConditions = smsConditionService.ListAllSmsCondition(req);
            response.setRecordsFiltered(smsConditions.getCount());
            response.setRecordsTotal(smsConditions.getCount());
            response.setCount(smsConditions.getCount());
            response.setMessage("Success");
            response.setData(smsConditions.getData());
            response.setStatusCode(200);

            // ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            // String json = ow.writeValueAsString(receiveSmsPayload);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.setCount(0);
            response.setData(null);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error while getting : " + e.getMessage());
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PostMapping("/by_id")
    public ResponseEntity<DefaultControllerResp> GetSmsConditionMoreDetail(@RequestBody SmsConditionMoreDetailReq req) throws Exception{
        ConfigConditionsEntity smsDetail = smsConditionService.getSmsConditionMoreDetail(req.getSmsID());
        DefaultControllerResp response = new DefaultControllerResp();
        if( smsDetail != null){
            if(smsDetail.getConditionsID() != null){ 
                response.setCount(1);
                response.setRecordsFiltered(1);
                response.setRecordsTotal(1);
                response.setMessage("Success");
                response.setData(smsDetail);
                response.setStatusCode(200);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                response.setCount(0);
                response.setMessage("Fail");
                response.setData(smsDetail);
                response.setStatusCode(400);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }else{
            response.setCount(0);
            response.setRecordsFiltered(0);
            response.setRecordsTotal(0);
            response.setMessage("NOT FOUND");
            response.setData(smsDetail);
            response.setStatusCode(400);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

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
