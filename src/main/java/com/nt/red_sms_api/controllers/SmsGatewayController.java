package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.dto.req.SmsGwConditionReq;
import com.nt.red_sms_api.dto.req.SmsGwOdtReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.DefaultServiceResp;
import com.nt.red_sms_api.dto.resp.OrderTypeResponseDto;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
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

    @GetMapping
    @RequestMapping("/order_types")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysByOrderType(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "limit", defaultValue = "10") Integer limit) throws Exception{

        PaginationDataResp smsGateways = smsGatewayService.findSmsGatewaySendAndUnSend(page, limit);

        DefaultControllerResp response = new DefaultControllerResp();
        response.setCount(smsGateways.getCount());
        response.setMessage("Success");
        response.setData(smsGateways.getData());
        response.setStatusCode(200);

        // ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        // String json = ow.writeValueAsString(receiveSmsPayload);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/order_type_with_status")
    public ResponseEntity<PaginationDataResp> getAllSmsConditions(@RequestBody SmsGwOdtReq req){
        return new ResponseEntity<>( smsGatewayService.findSmsGatewayMatchAndUnMatch(req.getPage(), req.getLimit(),req.getOrderTypeMainID(), req.getIsStatus()), HttpStatus.OK);
    }

    
    // @PostMapping
    // @RequestMapping("/sms_conditions")
    // public ResponseEntity<DefaultControllerResp> GetAllSmsGatewayAndCondition(@RequestBody SmsGwConditionReq req) throws Exception{
    //     DefaultServiceResp data = smsGatewayService.getSmsGatewaysAndCondition(req.getGID(), req.getOrderTypeID());
    //     DefaultControllerResp response = new DefaultControllerResp();
    //     response.setCount(data.getCount());
    //     response.setData(data.getResult());
    //     response.setMessage(data.getMessage());
    //     if( data.getError() != null ){
    //         response.setStatusCode(400);
    //     }else{
    //         response.setStatusCode(200);
    //     }
        
    //     return ResponseEntity.status(HttpStatus.OK).body(response);
    // }

}
