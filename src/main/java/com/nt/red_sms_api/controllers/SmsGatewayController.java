package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOdtReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeStatusReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
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

    @GetMapping
    @RequestMapping("/all_send")
    public ResponseEntity<DefaultControllerResp> getAllSendSmsGateways(
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
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, startTime, endTime,  start, length, search, search_field);
            PaginationDataResp smsGws = smsGatewayService.ListSendSmsGateWays(req);
            response.setRecordsFiltered(smsGws.getCount());
            response.setRecordsTotal(smsGws.getCount());
            response.setCount(smsGws.getCount());
            response.setMessage("Success");
            response.setData(smsGws.getData());
            response.setStatusCode(200);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.setCount(0);
            response.setData(null);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error while getting : " + e.getMessage());
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping
    @RequestMapping("/by_sending")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysBySending(
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "created_date")String sortName,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField
    ) throws Exception{
        
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, startTime, endTime, start, length, search, searchField);
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


    @GetMapping
    @RequestMapping("/by_condition")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysByCondition(
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "created_date")String sortName,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField
    ) throws Exception{
        
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, startTime, endTime, start, length, search, searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayMatchAndUnMatch(req);
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


    @GetMapping
    @RequestMapping("/by_response_time")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysByResponseTime(
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "send_date")String sortName,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField

    ) throws Exception{
        
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, startTime, endTime, start, length, search, searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayResponseTime(req);
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


    @GetMapping
    @RequestMapping("/order_type_with_status")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysOrderTypeAndStatus(
        @RequestParam(name = "order_type_main_id")Long orderTypeMainID,
        @RequestParam(name = "is_status")Integer isStatus,
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "send_date")String sortName,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField
    ) throws Exception{
        
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwOrderTypeStatusReq req = new SmsGwOrderTypeStatusReq();
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayOrderTypeAndStatus(req);
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


    @GetMapping("by_id")
    public ResponseEntity<DefaultControllerResp> GetSmsGatewayMoreDetail(@RequestParam(name="id") Long id){
        SmsGatewayEntity smsGwDetail = smsGatewayService.findSmsGatewayByID(id);
        DefaultControllerResp response = new DefaultControllerResp();
        if( smsGwDetail != null){
            if(smsGwDetail.getGID() != null){ 
                response.setCount(1);
                response.setRecordsFiltered(1);
                response.setRecordsTotal(1);
                response.setMessage("Success");
                response.setData(smsGwDetail);
                response.setStatusCode(200);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                response.setCount(0);
                response.setMessage("Fail");
                response.setData(smsGwDetail);
                response.setStatusCode(400);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }else{
            response.setCount(0);
            response.setRecordsFiltered(0);
            response.setRecordsTotal(0);
            response.setMessage("NOT FOUND");
            response.setData(smsGwDetail);
            response.setStatusCode(400);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    
    }



}
