package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.dto.req.AddUserPermissionReq;
import com.nt.red_sms_api.dto.resp.UserResp;
import com.nt.red_sms_api.dto.resp.UserResponseDto;
import com.nt.red_sms_api.entity.PermissionMenuEntity;
import com.nt.red_sms_api.service.PermissionMenuService;
import com.nt.red_sms_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/system_admin")
public class SystemAdminController {
    @Autowired
    private PermissionMenuService permissionMenuService;

    @GetMapping("/permissions")
    public ResponseEntity<List<PermissionMenuEntity>> getAllSaMenuPermission(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "limit", defaultValue = "10") Integer limit){
        return new ResponseEntity<>( permissionMenuService.ListMenuPermission(page, limit), HttpStatus.OK);
    }

    @PostMapping("/user_permission")
    public ResponseEntity<Object> addUserSaMenuPermission(@RequestBody AddUserPermissionReq  addUserPermissionReq){
        return new ResponseEntity<>( permissionMenuService.addUserSaMenuPermission(addUserPermissionReq.getUserID(), addUserPermissionReq.getPermissionID()), HttpStatus.OK);
    }

}
