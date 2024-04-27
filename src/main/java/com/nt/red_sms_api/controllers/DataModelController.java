package com.nt.red_sms_api.controllers;

import org.springframework.web.bind.annotation.*;

import com.nt.red_sms_api.Auth.JwtHelper;
import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.dto.req.audit.AuditLog;
import com.nt.red_sms_api.dto.req.datamodel.ListDataModelReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.VerifyAuthResp;
import com.nt.red_sms_api.entity.AuditLogEntity;
import com.nt.red_sms_api.service.AuditService;
import com.nt.red_sms_api.service.DataModelService;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/data_model")
public class DataModelController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private DataModelService dataModelService;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private AuditService auditService;


    @GetMapping("list")
    public ResponseEntity<DefaultControllerResp> ListDataModel(
        HttpServletRequest request,
        @RequestParam(name = "draw", defaultValue = "11")Integer draw,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "id")String sortName,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "start", defaultValue = "0")Integer start,
        @RequestParam(name = "length", defaultValue = "0")Integer length,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField
    ) {
        DefaultControllerResp response = new DefaultControllerResp();
        try {
            String ipAddress = request.getRemoteAddr();
            String requestHeader = request.getHeader("Authorization");
                
            VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);

            ListDataModelReq req = new ListDataModelReq(draw, sortBy, sortName,  start, length, search, searchField);
            PaginationDataResp listDataModel = dataModelService.ListAllDataModel(req);

            AuditLog auditLog = new AuditLog();
            auditLog.setAction("get");
            auditLog.setAuditable("data_model_template");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setIp_address(ipAddress);
            auditLog.setComment("ListDataModel");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);

            response.setRecordsFiltered(listDataModel.getCount());
            response.setRecordsTotal(listDataModel.getCount());
            response.setCount(listDataModel.getCount());
            response.setMessage("Success");
            response.setData(listDataModel.getData());
            response.setStatusCode(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setCount(0);
            response.setData(null);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error while getting : " + e.getMessage());
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
