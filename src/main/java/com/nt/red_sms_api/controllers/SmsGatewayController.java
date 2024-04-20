package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.dto.req.SmsGwOdtReq;
import com.nt.red_sms_api.dto.req.Vue.SmsGwListReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.service.SmsGatewayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/sms_gateways")
public class SmsGatewayController {
    @Autowired
    private SmsGatewayService smsGatewayService;

    @PostMapping
    @RequestMapping("/by_condition")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysByOrderType(@RequestBody SmsGwListReq req) throws Exception{
        
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewaySendAndUnSend(req);
            response.setRecordsFiltered(smsGateways.getCount());
            response.setRecordsTotal(smsGateways.getCount());
            response.setCount(smsGateways.getCount());
            response.setMessage("Success");
            response.setData(smsGateways.getData());
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
