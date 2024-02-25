package com.nt.red_sms_api.controllers;

import com.nt.red_sms_api.dto.req.UpdateByIdReq;
import com.nt.red_sms_api.dto.resp.DefaultControllerResp;
import com.nt.red_sms_api.dto.resp.OrderTypeResponseDto;
import com.nt.red_sms_api.enitiy.ViewOrderTypeEntity;
import com.nt.red_sms_api.service.OrderTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/ordertype")
public class OrderTypeController {
    @Autowired
    private OrderTypeService orderTypeService;
    @GetMapping
    public ResponseEntity<List<ViewOrderTypeEntity>> getAllSmsConditions(Integer page, Integer limit){
        return new ResponseEntity<>( orderTypeService.ListAllOrderType(page, limit), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<DefaultControllerResp> updateOrderTypeById(@RequestBody UpdateByIdReq req) throws Exception{
        
        orderTypeService.updateOrderTypeById(req.getUpdateID(), req.getUpdateInfo());

        DefaultControllerResp response = new DefaultControllerResp();
        
        response.setCount(1);
        response.setMessage("Success");
        response.setData(req.getUpdateInfo());
        
        response.setStatusCode(200);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    

}
