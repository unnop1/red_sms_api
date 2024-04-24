package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.Auth.JwtHelper;
import com.nt.red_sms_api.dto.req.smscondition.AddSmsConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.ListConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.SmsConditionMoreDetailReq;
import com.nt.red_sms_api.dto.req.smscondition.UpdateBySmsConditionReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.VerifyAuthResp;
import com.nt.red_sms_api.entity.ConfigConditionsEntity;
import com.nt.red_sms_api.service.SmsConditionService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/sms_conditions")
public class SmsConditionController {
    @Autowired
    private SmsConditionService smsConditionService;

    @Autowired
    private JwtHelper helper;

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
        ConfigConditionsEntity smsDetail = smsConditionService.getSmsConditionMoreDetail(req);
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

    @PostMapping("/create")
    public ResponseEntity<DefaultControllerResp> addSmsCondition(HttpServletRequest request, @RequestBody AddSmsConditionReq  addSmsConditionReq){
        DefaultControllerResp resp = new DefaultControllerResp();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = helper.verifyToken(requestHeader);
        try {
            smsConditionService.addSmsCondition(addSmsConditionReq, vsf.getUsername());
            resp.setCount(1);
            resp.setData(addSmsConditionReq);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Successfully added");

            return new ResponseEntity<>( resp, HttpStatus.OK);
        }catch (Exception e){
            resp.setCount(0);
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setData(null);
            resp.setMessage("Error while adding : " + e.getMessage());
            return new ResponseEntity<>( resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<DefaultControllerResp> updateSmsCondition(HttpServletRequest request, @RequestBody UpdateBySmsConditionReq  updateReq){
        DefaultControllerResp resp = new DefaultControllerResp();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = helper.verifyToken(requestHeader);
        try {
            smsConditionService.updateSmsConditionById(updateReq.getUpdateID(), updateReq.getUpdateInfo(), vsf.getUsername());
            resp.setCount(1);
            resp.setData(updateReq);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Successfully updated");

            return new ResponseEntity<>( resp, HttpStatus.OK);
        }catch (Exception e){
            resp.setCount(0);
            resp.setData(null);
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Error while updating : " + e.getMessage());
            return new ResponseEntity<>( resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("by_id")
    public ResponseEntity<DefaultControllerResp> DeleteSmsCondition(@RequestParam(name = "sms_condition_id") Long smsConditionID){
        DefaultControllerResp resp = new DefaultControllerResp();
        try {
            smsConditionService.removeSmsCondition(smsConditionID);
            resp.setCount(1);
            resp.setData(smsConditionID);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Successfully deleted");

            return new ResponseEntity<>( resp, HttpStatus.OK);
        }catch (Exception e){
            resp.setCount(0);
            resp.setData(null);
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Error while deleting : " + e.getMessage());
            return new ResponseEntity<>( resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
