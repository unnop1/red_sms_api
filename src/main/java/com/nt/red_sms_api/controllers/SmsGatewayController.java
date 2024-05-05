package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.Auth.JwtHelper;
import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.dto.req.audit.AuditLog;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOdtReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeStatusReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.VerifyAuthResp;
import com.nt.red_sms_api.entity.AuditLogEntity;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
import com.nt.red_sms_api.entity.view.sms_gateway.SmsGatewayDetail;
import com.nt.red_sms_api.service.AuditService;
import com.nt.red_sms_api.service.SmsGatewayService;

import jakarta.servlet.http.HttpServletRequest;

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
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("get");
            auditLog.setAuditable("sms_gateway");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setIp_address(ipAddress);
            auditLog.setComment("getAllSendSmsGateways");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);
            
            
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
            
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("get");
            auditLog.setAuditable("sms_gateway");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setIp_address(ipAddress);
            auditLog.setComment("GetAllSmsGatewaysBySending");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);
            
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
            
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("get");
            auditLog.setAuditable("sms_gateway");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setIp_address(ipAddress);
            auditLog.setComment("GetAllSmsGatewaysByCondition");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);
            
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
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayResponseTime(req);
            
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("get");
            auditLog.setAuditable("sms_gateway");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setIp_address(ipAddress);
            auditLog.setComment("GetAllSmsGatewaysByResponseTime");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);
            
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
            
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("get");
            auditLog.setAuditable("sms_gateway");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setIp_address(ipAddress);
            auditLog.setComment("GetAllSmsGatewaysOrderTypeAndStatus");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);
            
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
        AuditLog auditLog = new AuditLog();
        auditLog.setAction("get");
        auditLog.setAuditable("sms_gateway");
        auditLog.setAuditable_id(id);
        auditLog.setUsername(vsf.getUsername());
        auditLog.setBrowser(vsf.getBrowser());
        auditLog.setDevice(vsf.getDevice());
        auditLog.setOperating_system(vsf.getSystem());
        auditLog.setIp_address(ipAddress);
        auditLog.setComment("GetSmsGatewayMoreDetail");
        auditLog.setCreated_date(DateTime.getTimeStampNow());
        auditService.AddAuditLog(auditLog);
        
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
