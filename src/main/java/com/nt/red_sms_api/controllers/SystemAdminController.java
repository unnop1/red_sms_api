package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.Auth.JwtHelper;
import com.nt.red_sms_api.dto.req.AddPermissionReq;
import com.nt.red_sms_api.dto.req.UpdateByIdReq;
import com.nt.red_sms_api.dto.req.Vue.PermissionListReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.VerifyAuthResp;
import com.nt.red_sms_api.service.PermissionMenuService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/system_admin")
public class SystemAdminController {
    @Autowired
    private PermissionMenuService permissionMenuService;

    @Autowired
    private JwtHelper helper;

    @PostMapping("/permissions")
    public ResponseEntity<DefaultControllerResp> getAllSaMenuPermission(@RequestBody PermissionListReq req){
        DefaultControllerResp resp = new DefaultControllerResp();
        try {
            PaginationDataResp listSaMnPm = permissionMenuService.ListMenuPermission(req);
            resp.setRecordsFiltered(listSaMnPm.getCount());
            resp.setRecordsTotal(listSaMnPm.getCount());
            resp.setCount(listSaMnPm.getCount());
            resp.setData(listSaMnPm.getData());
            resp.setDraw(req.getDraw());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Successfully");

            return new ResponseEntity<>( resp, HttpStatus.OK);
        }catch (Exception e){
            resp.setCount(0);
            resp.setData(null);
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Error while getting : " + e.getMessage());
            return new ResponseEntity<>( resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/permission")
    public ResponseEntity<DefaultControllerResp> addSaMenuPermission(HttpServletRequest request, @RequestBody AddPermissionReq  addPermissionReq){
        DefaultControllerResp resp = new DefaultControllerResp();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = helper.verifyToken(requestHeader);
        try {
            permissionMenuService.addSaMenuPermission(addPermissionReq, vsf.getUsername());
            resp.setCount(1);
            resp.setData(addPermissionReq);
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

    @PutMapping("/permission")
    public ResponseEntity<DefaultControllerResp> updateSaMenuPermission(HttpServletRequest request, @RequestBody UpdateByIdReq  updateReq){
        DefaultControllerResp resp = new DefaultControllerResp();
        String requestHeader = request.getHeader("Authorization");
            
        VerifyAuthResp vsf = helper.verifyToken(requestHeader);
        try {
            permissionMenuService.updatePermission(updateReq.getUpdateID(), updateReq.getUpdateInfo(), vsf.getUsername());
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

    @DeleteMapping("/permission")
    public ResponseEntity<DefaultControllerResp> DeleteSaMenuPermission(@RequestParam(name = "permission_id") Long permissionID){
        DefaultControllerResp resp = new DefaultControllerResp();
        try {
            permissionMenuService.removePermission(permissionID);
            resp.setCount(1);
            resp.setData(permissionID);
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
