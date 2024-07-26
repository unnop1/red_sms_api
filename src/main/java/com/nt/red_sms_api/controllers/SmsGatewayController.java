package com.nt.red_sms_api.controllers;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.red_sms_api.Auth.JwtHelper;
import com.nt.red_sms_api.Util.CustomServlet;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwConditionReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwListReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeRespTimeReq;
import com.nt.red_sms_api.dto.req.smsgw.SmsGwOrderTypeStatusReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.SmsGatewayResponseTimeReportResp;
import com.nt.red_sms_api.dto.resp.SmsGatewayResponseTimeResp;
import com.nt.red_sms_api.dto.resp.VerifyAuthResp;
import com.nt.red_sms_api.entity.SmsGatewayEntity;
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

    @GetMapping("/all_send")
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
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, null,startTime, endTime,  start, length, search, search_field);
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

    @GetMapping("/by_sending")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysBySending(
        HttpServletRequest request,    
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "created_date")String sortName,
        @RequestParam(name = "by_time", defaultValue = "date")String byTime,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField
    ) throws Exception{
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
       
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, byTime,startTime, endTime, start, length, search, searchField);
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


    @GetMapping("/by_condition")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysByCondition(
        HttpServletRequest request,   
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "created_date")String sortName,
        @RequestParam(name = "by_time", defaultValue = "date")String byTime,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField
    ) throws Exception{
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
       
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, byTime,startTime, endTime, start, length, search, searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayCondition(req);
            
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

    @GetMapping("/by_ordertype")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysByOrderType(
        HttpServletRequest request,   
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "created_date")String sortName,
        @RequestParam(name = "by_time", defaultValue = "date")String byTime,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField
    ) throws Exception{
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
       
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, byTime,startTime, endTime, start, length, search, searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayOrderType(req);
            
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


    @GetMapping("/by_response_time")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysByResponseTime(
        HttpServletRequest request,       
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "send_date")String sortName,
        @RequestParam(name = "by_time", defaultValue = "date")String byTime,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField,
        @RequestParam(name = "order_type_main_id")Long orderTypeMainID

    ) throws Exception{
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwOrderTypeRespTimeReq req = new SmsGwOrderTypeRespTimeReq(draw, sortBy, sortName, byTime,startTime, endTime, start, length, search, searchField, orderTypeMainID);
            
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayByResponseTime(req);
            
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

    @GetMapping("/graph_response_time")
    public ResponseEntity<DefaultControllerResp> GraphSmsGatewaysByResponseTime(
        HttpServletRequest request,       
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "send_date")String sortName,
        @RequestParam(name = "by_time", defaultValue = "date")String byTime,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField

    ) throws Exception{
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        DefaultControllerResp response = new DefaultControllerResp();
        try{

            HashMap<String, Object> dataResp = new HashMap<String, Object>();

            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, byTime, startTime, endTime, start, length, search, searchField);
            SmsGatewayResponseTimeReportResp smsGatewayReports = smsGatewayService.findSmsGatewayResponseTimeReport(req);

            response.setDraw(draw);
            response.setMessage("Success");
            response.setData(smsGatewayReports.getData());
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


    @GetMapping("/order_type_with_status")
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
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwOrderTypeStatusReq req = new SmsGwOrderTypeStatusReq(orderTypeMainID, isStatus,sortBy,sortName,startTime, endTime,start,length,search,searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayOrderTypeAndStatus(req);
            
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


    @GetMapping("/ordertypes")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysOrderTypes(
        HttpServletRequest request,
        @RequestParam(name = "order_type_main_id")Long orderTypeMainID,       
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
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwOrderTypeReq req = new SmsGwOrderTypeReq(orderTypeMainID,sortBy,sortName,startTime, endTime,start,length,search,searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayOrderTypeReportByID(req);
            
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


    @GetMapping("/conditions")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysConditions(
        HttpServletRequest request,       
        @RequestParam(name = "conditions_id")Long conditionsID,
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
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwConditionReq req = new SmsGwConditionReq(conditionsID,sortBy,sortName,startTime, endTime,start,length,search,searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewayConditions(req);
            
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


    @GetMapping("/sendings")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysSendings(
        HttpServletRequest request,       
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "send_date")String sortName,
        @RequestParam(name = "by_time", defaultValue = "date")String byTime,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField
    ) throws Exception{
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, byTime,startTime, endTime,start,length,search,searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewaySendings(req);
            
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

    @GetMapping("/response_times")
    public ResponseEntity<DefaultControllerResp> GetAllSmsGatewaysResponseTimes(
        HttpServletRequest request,       
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "send_date")String sortName,
        @RequestParam(name = "by_time", defaultValue = "date")String byTime,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField
    ) throws Exception{
        String ipAddress = CustomServlet.getClientIpAddress(request);
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            SmsGwListReq req = new SmsGwListReq(draw, sortBy, sortName, byTime,startTime, endTime,start,length,search,searchField);
            PaginationDataResp smsGateways = smsGatewayService.findSmsGatewaySendings(req);
            
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



    @GetMapping("/by_id")
    public ResponseEntity<DefaultControllerResp> GetSmsGatewayMoreDetail(HttpServletRequest request, @RequestParam(name="id") Long id){
        String ipAddress = CustomServlet.getClientIpAddress(request);
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
