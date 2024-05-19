package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.Auth.JwtHelper;
import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.dto.req.audit.AuditLog;
import com.nt.red_sms_api.dto.req.smscondition.AddSmsConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.ListConditionReq;
import com.nt.red_sms_api.dto.req.smscondition.SmsConditionMoreDetailReq;
import com.nt.red_sms_api.dto.req.smscondition.UpdateBySmsConditionReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.VerifyAuthResp;
import com.nt.red_sms_api.entity.AuditLogEntity;
import com.nt.red_sms_api.entity.ConfigConditionsEntity;
import com.nt.red_sms_api.service.AuditService;
import com.nt.red_sms_api.service.SmsConditionService;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private AuditService auditService;

    @GetMapping
    public ResponseEntity<DefaultControllerResp> getAllSmsConditions(
        HttpServletRequest request,
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "created_date")String sortName,
        @RequestParam(name = "start_time", defaultValue = "")String startTime,
        @RequestParam(name = "end_time", defaultValue = "")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "10")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String search_field
    ){
        DefaultControllerResp response = new DefaultControllerResp();
        try{
            ListConditionReq req = new ListConditionReq(draw, sortBy, sortName, startTime, endTime,  start, length, search, search_field);
            PaginationDataResp smsConditions = smsConditionService.ListAllSmsCondition(req);
            
            String ipAddress = request.getRemoteAddr();
            String requestHeader = request.getHeader("Authorization");
                
            VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
            System.out.println("vsf:"+vsf.toString());
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("Get");
            auditLog.setAuditable("config_conditions");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setIp_address(ipAddress);
            auditLog.setComment("getAllSmsConditions");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);
            
            
            response.setDraw(draw);
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
            response.setDraw(draw);
            response.setCount(0);
            response.setData(null);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error while getting : " + e.getMessage());
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PostMapping("/by_id")
    public ResponseEntity<DefaultControllerResp> GetSmsConditionMoreDetail(
        HttpServletRequest request,    
        @RequestBody SmsConditionMoreDetailReq req
    ) throws Exception{
        ConfigConditionsEntity smsDetail = smsConditionService.getSmsConditionMoreDetail(req);
        DefaultControllerResp response = new DefaultControllerResp();
        String ipAddress = request.getRemoteAddr();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
        AuditLog auditLog = new AuditLog();
        auditLog.setAction("get");
        auditLog.setAuditable("config_conditions");
        auditLog.setUsername(vsf.getUsername());
        auditLog.setBrowser(vsf.getBrowser());
        auditLog.setDevice(vsf.getDevice());
        auditLog.setAuditable_id(req.getConditionsID());
        auditLog.setOperating_system(vsf.getSystem());
        auditLog.setIp_address(ipAddress);
        auditLog.setComment("GetSmsConditionMoreDetail");
        auditLog.setCreated_date(DateTime.getTimeStampNow());
        auditService.AddAuditLog(auditLog);
        

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
        String ipAddress = request.getRemoteAddr();
            
        VerifyAuthResp vsf = helper.verifyToken(requestHeader);
        try {
            Long createdlastID = smsConditionService.addSmsCondition(addSmsConditionReq, vsf.getUsername());
            
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("create");
            auditLog.setAuditable("config_conditions");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setAuditable_id(createdlastID+1);
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setIp_address(ipAddress);
            auditLog.setComment("addSmsCondition");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);
            

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

    @PostMapping("/duplicate")
    public ResponseEntity<DefaultControllerResp> duplicateSmsCondition(HttpServletRequest request, @RequestParam(value = "condition_id") Long conditionID){
        DefaultControllerResp resp = new DefaultControllerResp();
        String requestHeader = request.getHeader("Authorization");
        String ipAddress = request.getRemoteAddr();
            
        VerifyAuthResp vsf = helper.verifyToken(requestHeader);
        try {
            Long createdlastID = smsConditionService.duplicateSmsCondition(conditionID, vsf.getUsername());
            
            if(createdlastID != null){
                AuditLog auditLog = new AuditLog();
                auditLog.setAction("create");
                auditLog.setAuditable("config_conditions");
                auditLog.setUsername(vsf.getUsername());
                auditLog.setBrowser(vsf.getBrowser());
                auditLog.setDevice(vsf.getDevice());
                auditLog.setAuditable_id(createdlastID+1);
                auditLog.setOperating_system(vsf.getSystem());
                auditLog.setIp_address(ipAddress);
                auditLog.setComment("duplicateSmsCondition");
                auditLog.setCreated_date(DateTime.getTimeStampNow());
                auditService.AddAuditLog(auditLog);
            }
            

            resp.setCount(1);
            resp.setData(createdlastID+1);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Successfully");

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
        String ipAddress = request.getRemoteAddr();
            
        VerifyAuthResp vsf = helper.verifyToken(requestHeader);
        try {
            smsConditionService.updateSmsConditionById(updateReq.getUpdateID(), updateReq.getUpdateInfo(), vsf.getUsername());
            
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("update");
            auditLog.setAuditable("config_conditions");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setAuditable_id(updateReq.getUpdateID());
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setIp_address(ipAddress);
            auditLog.setComment("updateSmsCondition");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);
            
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
    public ResponseEntity<DefaultControllerResp> DeleteSmsCondition(HttpServletRequest request, @RequestParam(name = "sms_condition_id") Long smsConditionID){
        String requestHeader = request.getHeader("Authorization");
        String ipAddress = request.getRemoteAddr();
            
        VerifyAuthResp vsf = helper.verifyToken(requestHeader);
        DefaultControllerResp resp = new DefaultControllerResp();
        try {
            smsConditionService.removeSmsCondition(smsConditionID);
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("delete");
            auditLog.setAuditable("config_conditions");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setAuditable_id(smsConditionID);
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setIp_address(ipAddress);
            auditLog.setComment("DeleteSmsCondition");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);
            
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
