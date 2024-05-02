package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.Auth.JwtHelper;
import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.dto.req.audit.AuditLog;
import com.nt.red_sms_api.dto.req.ordertype.ListOrderTypeReq;
import com.nt.red_sms_api.dto.req.ordertype.UpdateOrderTypeReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.VerifyAuthResp;
import com.nt.red_sms_api.entity.AuditLogEntity;
import com.nt.red_sms_api.service.AuditService;
import com.nt.red_sms_api.service.OrderTypeService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/ordertype")
public class OrderTypeController {
    @Autowired
    private OrderTypeService orderTypeService;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private AuditService auditService;

    @GetMapping
    public ResponseEntity<PaginationDataResp> getAllOrderTypes(
        HttpServletRequest request,    
        @RequestParam(name = "page", defaultValue = "1")Integer page,
        @RequestParam(name = "limit", defaultValue = "10")Integer limit,
        @RequestParam(name = "order[0][dir]", defaultValue = "ASC")String sortBy,
        @RequestParam(name = "order[0][name]", defaultValue = "created_date")String sortName,
        @RequestParam(name = "start_time", defaultValue = "0")String startTime,
        @RequestParam(name = "end_time", defaultValue = "0")String endTime,
        @RequestParam(name = "Search", defaultValue = "")String search,
        @RequestParam(name = "Search_field", defaultValue = "")String searchField
    ){
        
        String ipAddress = request.getRemoteAddr();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);

        ListOrderTypeReq req = new ListOrderTypeReq(
            page,
            limit,
            sortBy,
            sortName,
            startTime,
            endTime,
            search,
            searchField
        );

        AuditLog auditLog = new AuditLog();
        auditLog.setAction("get");
        auditLog.setAuditable("order_type");
        auditLog.setUsername(vsf.getUsername());
        auditLog.setBrowser(vsf.getBrowser());
        auditLog.setDevice(vsf.getDevice());
        auditLog.setOperating_system(vsf.getSystem());
        auditLog.setIp_address(ipAddress);
        auditLog.setComment("getAllOrderTypes");
        auditLog.setCreated_date(DateTime.getTimeStampNow());
        auditService.AddAuditLog(auditLog);
        
        return new ResponseEntity<>( orderTypeService.ListAllOrderType(req), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<DefaultControllerResp> updateOrderTypeById(HttpServletRequest request, @RequestBody UpdateOrderTypeReq req) throws Exception{
        
        String ipAddress = request.getRemoteAddr();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);

        orderTypeService.UpdateOrderTypeById(req.getUpdateID(), req.getUpdateInfo(), vsf.getUsername());

        DefaultControllerResp response = new DefaultControllerResp();
        
        AuditLog auditLog = new AuditLog();
        auditLog.setAction("update");
        auditLog.setAuditable("order_type");
        auditLog.setAuditable_id(req.getUpdateID());
        auditLog.setUsername(vsf.getUsername());
        auditLog.setBrowser(vsf.getBrowser());
        auditLog.setDevice(vsf.getDevice());
        auditLog.setOperating_system(vsf.getSystem());
        auditLog.setIp_address(ipAddress);
        auditLog.setComment("updateOrderTypeById");
        auditLog.setCreated_date(DateTime.getTimeStampNow());
        auditService.AddAuditLog(auditLog);

        response.setCount(1);
        response.setMessage("Success");
        response.setData(req.getUpdateInfo());
        
        response.setStatusCode(200);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    


}
