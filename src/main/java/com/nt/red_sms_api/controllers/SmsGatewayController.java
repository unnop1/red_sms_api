package com.nt.red_sms_api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.red_sms_api.Auth.JwtHelper;
import com.nt.red_sms_api.Util.Calculate;
import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.dto.req.audit.AuditLog;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeStatusReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.SmsGatewayResponseTimeResp;
import com.nt.red_sms_api.dto.resp.VerifyAuthResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.ByResponseTime;
import com.nt.red_sms_api.service.AuditService;
import com.nt.red_sms_api.service.SmsGatewayService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/sms_gateways")
public class SmsGatewayController {
    @Autowired
    private SmsGatewayService smsGatewayService;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private AuditService auditService;

    @GetMapping
    @RequestMapping("/all_send")
    public ResponseEntity<DefaultControllerResp> getAllSendSmsGateways(
        HttpServletRequest request,
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
        String ipAddress = request.getRemoteAddr();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, startTime, endTime,  start, length, search, search_field);
            PaginationDataResp smsGws = smsGatewayService.ListSendSmsGateWays(req);
            
            response.setDraw(draw);
            response.setRecordsFiltered(smsGws.getCount());
            response.setRecordsTotal(smsGws.getCount());
            response.setCount(smsGws.getCount());
            response.setCountSend(smsGws.getCountSend());
            response.setCountUnSend(smsGws.getCountUnSend());
            response.setMessage("Success");
            response.setData(smsGws.getData());
            response.setStatusCode(200);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.setDraw(draw);
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
        HttpServletRequest request,    
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
        String ipAddress = request.getRemoteAddr();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
       
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, startTime, endTime, start, length, search, searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewaySendAndUnSend(req);
            
            response.setDraw(draw);
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
            response.setDraw(draw);
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
        HttpServletRequest request,   
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
        String ipAddress = request.getRemoteAddr();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
       
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, startTime, endTime, start, length, search, searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayMatchAndUnMatch(req);
            
            response.setDraw(draw);
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
            response.setDraw(draw);
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
        HttpServletRequest request,       
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
        String ipAddress = request.getRemoteAddr();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, startTime, endTime, start, length, search, searchField);
            SmsGatewayResponseTimeResp smsGateways = smsGatewayService.findSmsGatewayResponseTime(req);
            
            response.setDraw(draw);
            response.setRecordsFiltered(smsGateways.getCount());
            response.setRecordsTotal(smsGateways.getCount());
            response.setCount(smsGateways.getCount());
            response.setMessage("Success");
            response.setData(smsGateways.getData());
            response.setStatusCode(200);
            return new ResponseEntity<>( response, HttpStatus.OK);
        }catch (Exception e){
            response.setDraw(draw);
            response.setCount(0);
            response.setData(null);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error while getting : " + e.getMessage());
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @RequestMapping("/graph_response_time")
    public ResponseEntity<DefaultControllerResp> GraphSmsGatewaysByResponseTime(
        HttpServletRequest request,       
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
        String ipAddress = request.getRemoteAddr();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        DefaultControllerResp response = new DefaultControllerResp();
        try{

            HashMap<String, Object> dataResp = new HashMap<String, Object>();

            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, startTime, endTime, start, length, search, searchField);
            SmsGatewayResponseTimeResp smsGateways = smsGatewayService.findSmsGatewayResponseTime(req);

            // List<Double> graphRespTimes = new ArrayList<Double>();
            // for(ByResponseTime smsGateway : smsGateways.getData()){
            //     graphRespTimes.add(Double.valueOf(smsGateway.getRESPONSE_TIME()));
            // }

            // Find MED, Max, Min, Avg
            // Double medValue = Calculate.findMedian(graphRespTimes);
            // Double maxValue = Calculate.findMax(graphRespTimes);
            // Double minValue = Calculate.findMin(graphRespTimes);
            // Double avgValue = Calculate.findAverage(graphRespTimes);


            // Response Time data
            // dataResp.put("median_response_time", medValue);
            // dataResp.put("max_response_time", maxValue);
            // dataResp.put("min_response_time", minValue);
            // dataResp.put("average_response_time", avgValue);
            // dataResp.put("data", smsGateways.getData());
                        
            response.setDraw(draw);
            response.setRecordsFiltered(smsGateways.getCount());
            response.setRecordsTotal(smsGateways.getCount());
            response.setCount(smsGateways.getCount());
            response.setMessage("Success");
            response.setData(smsGateways.getData());
            response.setStatusCode(200);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.setDraw(draw);
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
        HttpServletRequest request,       
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
        String ipAddress = request.getRemoteAddr();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwOrderTypeStatusReq req = new SmsGwOrderTypeStatusReq(orderTypeMainID, isStatus,sortBy,sortName,startTime, endTime,start,length,search,searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayOrderTypeAndStatus(req);

            // ObjectMapper mapper = new ObjectMapper();
            // String jsonResponse;
            // try{
            //     jsonResponse = mapper.writeValueAsString(smsGateways.getData());
            // }catch (Exception e){
            //     response.setMessage(e.getMessage());
            //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            // }

            
            response.setDraw(draw);
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
            response.setDraw(draw);
            response.setCount(0);
            response.setData(null);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error while getting : " + e.getMessage());
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("by_id")
    public ResponseEntity<DefaultControllerResp> GetSmsGatewayMoreDetail(HttpServletRequest request, @RequestParam(name="id") Long id){
        String ipAddress = request.getRemoteAddr();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
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
