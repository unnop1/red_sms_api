package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.Auth.JwtHelper;
import com.nt.red_sms_api.Util.Convert;
import com.nt.red_sms_api.Util.DateTime;
import com.nt.red_sms_api.dto.req.audit.AuditLog;
import com.nt.red_sms_api.dto.req.auth.JwtRequest;
import com.nt.red_sms_api.dto.req.user.UserRequestDto;
import com.nt.red_sms_api.dto.resp.AuthSuccessResp;
import com.nt.red_sms_api.dto.resp.JwtErrorResp;
import com.nt.red_sms_api.dto.resp.LoginResp;
import com.nt.red_sms_api.dto.resp.UserResp;
import com.nt.red_sms_api.dto.resp.VerifyAuthResp;
import com.nt.red_sms_api.entity.AuditLogEntity;
import com.nt.red_sms_api.entity.AuditLogEntity;
import com.nt.red_sms_api.entity.LogLoginEntity;
import com.nt.red_sms_api.entity.PermissionMenuEntity;
import com.nt.red_sms_api.entity.UserEntity;
import com.nt.red_sms_api.exp.UserAlreadyExistsException;
import com.nt.red_sms_api.log.LogFlie;
import com.nt.red_sms_api.service.AuditService;
import com.nt.red_sms_api.service.LogLoginService;
import com.nt.red_sms_api.service.PermissionMenuService;
import com.nt.red_sms_api.service.UserService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashMap;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private AuditService auditService;

    @Autowired
    private UserService userService;

    @Autowired
    private LogLoginService logloginService;

    @Autowired
    private PermissionMenuService permissionMenuService; 

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");    


    @PostMapping("/create")
    public ResponseEntity<AuthSuccessResp> createUser(HttpServletRequest request, @RequestBody UserRequestDto userRequestDto) {
        String requestHeader = request.getHeader("Authorization");
        String ipAddress = request.getRemoteAddr();
        VerifyAuthResp vsf = helper.verifyToken(requestHeader);
        try {
            UserResp userResponseDto = userService.createUser(userRequestDto, vsf.getUsername());
            UserEntity userDetails = userService.loadUserByUsername(userResponseDto.getUsername());
            
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("create");
            auditLog.setAuditable("user_db");
            auditLog.setUsername(vsf.getUsername());
            auditLog.setDevice(vsf.getDevice());
            auditLog.setOperating_system(vsf.getSystem());
            auditLog.setBrowser(vsf.getBrowser());
            auditLog.setIp_address(ipAddress);
            auditLog.setAuditable_id(userDetails.getId());
            auditLog.setComment("createUser");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);

            JwtRequest jwtReq = new JwtRequest();
            jwtReq.setUsername(vsf.getUsername());
            jwtReq.setBrowser(vsf.getBrowser());
            jwtReq.setDevice(vsf.getDevice());
            jwtReq.setSystem(vsf.getSystem());

            LogFlie.logMessage(
                "AuthController", 
                "audit_logs",
                String.format(
                    "%s %s %s %s %s %s %s %s %s",
                    df.format(new Date()),
                    "insert",
                    "createUser",
                    "user_db",
                    vsf.getUsername(),
                    ipAddress,
                    vsf.getDevice(),
                    vsf.getBrowser(),
                    vsf.getSystem()
                )
            );

            String token = this.helper.generateToken(jwtReq, userDetails.getEmail());
            return new ResponseEntity<>(new AuthSuccessResp(token), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            // Handle the exception and return an appropriate response
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthSuccessResp("User already exists: " + ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody JwtRequest jwtRequest, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        try{
            // Get the IP address from the request
            LoginResp loginResp = new LoginResp();
            // String userAgent = request.getHeader("User-Agent");
            // String deviceInfo = parseUserAgent(userAgent);
            // String systemInfo = parseUserAgentForSystem(userAgent);
            // String browserInfo = parseUserAgentForBrowser(userAgent);
            System.out.println("IP Address: " + ipAddress);
        
            // Log login
            Timestamp loginDateTime = new Timestamp(Instant.now().toEpochMilli());
            LogLoginEntity loglogin = new LogLoginEntity();
            loglogin.setBrowser(jwtRequest.getBrowser());
            loglogin.setDevice(jwtRequest.getDevice());
            loglogin.setSystem(jwtRequest.getSystem());
            loglogin.setIp_address(ipAddress);
            loglogin.setLogin_datetime(loginDateTime);
            loglogin.setCreate_date(loginDateTime);
            loglogin.setUsername(jwtRequest.getUsername());
            System.out.println("jwtUsername:"+jwtRequest.getUsername());
            UserEntity userDetails = userService.findUserLogin(jwtRequest.getUsername());
            if( userDetails == null ){
                return new ResponseEntity<>(loginResp, HttpStatus.BAD_REQUEST);
            }

            this.doAuthenticate(userDetails.getUsername(), jwtRequest.getPassword(), loglogin);
            
            System.out.println("getEmail:"+userDetails.getEmail());
            System.out.println("getUsername:"+userDetails.getUsername());
            String token = this.helper.generateToken(jwtRequest, userDetails.getEmail());

            HashMap<String, Object> updateInfo = new HashMap<String, Object>();
            updateInfo.put("currentToken", token);
            updateInfo.put("last_login", loginDateTime);
            updateInfo.put("last_login_ipaddress", ipAddress);

            this.userService.updateUserLogLogin(userDetails.getId(), updateInfo);

            
            UserResp userInfo = new UserResp();

            // User
            userInfo.setId(userDetails.getId());
            userInfo.setAbout_Me(userDetails.getAbout_me());
            userInfo.setName(userDetails.getName());
            userInfo.setUsername(userDetails.getUsername());
            userInfo.setPhoneNumber(userDetails.getPhoneNumber());
            userInfo.setEmail(userDetails.getEmail());
            userInfo.setLast_login(userDetails.getLast_login());
            userInfo.setLast_login_ipaddress(ipAddress);
            userInfo.setCreated_by(userDetails.getCreated_by());
            userInfo.setCreated_Date(userDetails.getCreated_Date());
            userInfo.setIs_Enable(userDetails.getIs_Enable());
            userInfo.setIs_Delete_by(userDetails.getIs_Delete_by());
            userInfo.setIs_Delete(userDetails.getIs_Delete());
            userInfo.setUpdated_Date(userDetails.getUpdated_Date());
            userInfo.setUpdated_by(userDetails.getUpdated_by());
            loginResp.setUserLogin(userInfo);
            loginResp.setJwtToken(token);

            // permissionMenu
            PermissionMenuEntity permissionMenuEntity = permissionMenuService.getMenuPermission(userDetails.getSa_menu_permission_id());
            String permissionJSonStr;
            if(permissionMenuEntity!=null){
                // permissionJSonStr = Convert.clobToString(permissionMenuEntity.getPermission_json());
                loginResp.setPermissionJson(permissionMenuEntity.getPermission_json());
                loginResp.setPermissionName(permissionMenuEntity.getPermission_Name());
            }

            // System.out.println("token:"+token);
            AuditLog auditLog = new AuditLog();
            auditLog.setAction("login");
            auditLog.setAuditable_id(userDetails.getId());
            auditLog.setAuditable("user_db");
            auditLog.setIp_address(ipAddress);
            auditLog.setUsername(userDetails.getUsername());
            auditLog.setDevice(jwtRequest.getDevice());
            auditLog.setBrowser(jwtRequest.getBrowser());
            auditLog.setOperating_system(jwtRequest.getSystem());
            auditLog.setComment("authentication login");
            auditLog.setCreated_date(DateTime.getTimeStampNow());
            auditService.AddAuditLog(auditLog);

            LogFlie.logMessage(
                "AuthController", 
                "success",
                String.format(
                    "%s %s %s %s %s %s",
                    df.format(new Date()),
                    jwtRequest.getUsername(),
                    ipAddress,
                    jwtRequest.getDevice(),
                    jwtRequest.getBrowser(),
                    jwtRequest.getSystem()
                )
            );

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(loginResp);
        }catch (Exception e){
            LogFlie.logMessage(
                "AuthController", 
                "fail",
                String.format(
                    "%s %s %s %s %s %s",
                    df.format(new Date()),
                    jwtRequest.getUsername(),
                    ipAddress,
                    jwtRequest.getDevice(),
                    jwtRequest.getBrowser(),
                    jwtRequest.getSystem()
                )
            );

            return ResponseEntity.internalServerError()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(HttpServletRequest request) {
        // Get the IP address from the request
        try{
            LoginResp userResp = new LoginResp();
            System.out.println("request:"+request.getHeaderNames());
            String requestHeader = request.getHeader("Authorization");
            String token = requestHeader.substring(7);
                
            VerifyAuthResp vsf = this.helper.verifyToken(requestHeader);
            if (vsf.getError() == null) {
                UserEntity userDetails = vsf.getUserInfo();
                if( userDetails == null ){
                    return new ResponseEntity<>(userResp, HttpStatus.BAD_REQUEST);
                }
                UserResp userInfo = new UserResp();
                // PermissionMenu permissionMenu = 

                // User
                userInfo.setId(userDetails.getId());
                userInfo.setAbout_Me(userDetails.getAbout_me());
                userInfo.setName(userDetails.getName());
                userInfo.setPhoneNumber(userDetails.getPhoneNumber());
                userInfo.setIs_Enable(userDetails.getIs_Enable());
                userInfo.setEmail(userDetails.getEmail());
                userInfo.setLast_login(userDetails.getLast_login());
                userInfo.setCreated_by(userDetails.getCreated_by());
                userInfo.setCreated_Date(userDetails.getCreated_Date());
                userInfo.setIs_Delete_by(userDetails.getIs_Delete_by());
                userInfo.setIs_Delete(userDetails.getIs_Delete());
                userInfo.setUpdated_Date(userDetails.getUpdated_Date());
                userInfo.setSa_menu_permission_id(userDetails.getSa_menu_permission_id());
                userInfo.setUpdated_by(userDetails.getUpdated_by());
                userResp.setUserLogin(userInfo);
                userResp.setJwtToken(token);

                // permissionMenu
                PermissionMenuEntity permissionMenuEntity = permissionMenuService.getMenuPermission(userDetails.getSa_menu_permission_id());
                String permissionJSonStr;
                if(permissionMenuEntity!=null){
                    // permissionJSonStr = Convert.clobToString(permissionMenuEntity.getPermission_json());
                    userResp.setPermissionJson(permissionMenuEntity.getPermission_json());
                    userResp.setPermissionName(permissionMenuEntity.getPermission_Name());
                }
                return new ResponseEntity<>(userResp, HttpStatus.OK);
            }
            

            return new ResponseEntity<>(userResp, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private void doAuthenticate(String username, String password, LogLoginEntity loglogin) {
        System.out.println("Login Info");
        System.out.println(username);
        System.out.println(password);
        System.out.println("------");
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
            manager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Authentication successful for user: " + username);
            loglogin.setIs_login(1);
            this.logloginService.createLog(loglogin);

        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            System.out.println("Authentication not-successful for user: " + username);
            loglogin.setPassword(password);
            loglogin.setIs_login(0);
            System.out.println(loglogin.toString());
            this.logloginService.createLog(loglogin);
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<JwtErrorResp> exceptionHandler(BadCredentialsException ex) {
        return new ResponseEntity<>(new JwtErrorResp(400, "Credentials Invalid !!"), HttpStatus.BAD_REQUEST);
    }
}
