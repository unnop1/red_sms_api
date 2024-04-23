package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.config.AuthConfig;
import com.nt.red_sms_api.dto.req.user.ListUserReq;
import com.nt.red_sms_api.dto.req.user.UpdateUserDto;
import com.nt.red_sms_api.dto.req.user.UserRequestDto;
import com.nt.red_sms_api.dto.resp.PaginationDataResp;
import com.nt.red_sms_api.dto.resp.UserInfoResp;
import com.nt.red_sms_api.dto.resp.UserResp;
import com.nt.red_sms_api.entity.UserEntity;
import com.nt.red_sms_api.entity.view.user.ListUser;
import com.nt.red_sms_api.exp.UserAlreadyExistsException;
import com.nt.red_sms_api.repo.UserRepo;
import com.nt.red_sms_api.repo.view.user.ListUserRepo;
import com.nt.red_sms_api.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.nt.red_sms_api.Util.DateTime;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserImp implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ListUserRepo listUserRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthConfig authConfig;

    @Override
    public UserResp findUserById(Long userID) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByID(userID);
        UserResp userResp = userEntityToUserRespDto(user);
        return userResp;
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        return user;
    }

    @Override
    public PaginationDataResp getAllUser(ListUserReq req) {
        PaginationDataResp resp = new PaginationDataResp();
        Integer offset = req.getStart();
        Integer limit = req.getLength();
        Integer page = offset / limit;
        String sortName = req.getSortName();
        String sortBy = req.getSortBy();
        String search = req.getSearch();
        String searchField = req.getSearchField();
        

        if ( search.isEmpty()){
            List<ListUser> userResponseDtoList = listUserRepo.getAllUser(PageRequest.of(page, limit, Sort.Direction.fromString(sortBy), sortName ));
            Integer count = listUserRepo.getTotalCount();
            resp.setData(userResponseDtoList);
            resp.setCount(count);
            return resp;
        }else {
            if( !req.getSearchField().isEmpty()){
                List<ListUser> smsConditionEntities = listUserRepo.getListUserAllSearch(search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy)));
                Integer count = listUserRepo.getListUserAllSearchTotalCount(search);
                resp.setCount(count);
                resp.setData(smsConditionEntities);
                return resp;
            }

            List<ListUser> smsConditionEntities = listUserRepo.getListUserOneSearch(searchField, search, PageRequest.of(page, limit, Sort.Direction.fromString(sortBy)));
            
            if (searchField.equals("permission_name")){
                searchField = "sa_pm.permission_name";
            }
            Integer count = listUserRepo.getListUserOneSearchTotalCount(search);
            resp.setCount(count);
            resp.setData(smsConditionEntities);
            return resp;
        }


    }
    @Override
    public UserResp createUser(UserRequestDto userRequestDto, String createdBy) {
        UserEntity foundUser = this.userRepo.loadByUniqueUser(userRequestDto.getEmail(), userRequestDto.getUsername());
        if (foundUser == null) {
            // Creat a new user
            Timestamp timeNow = DateTime.getTimeStampNow();
            UserEntity user = this.userReqDtoToUserEntity(userRequestDto);
            user.setCreated_by(createdBy);
            user.setCreated_Date(timeNow);
            // Encode password  
            user.setPassword(authConfig.passwordEncoder().encode(user.getPassword()));
            // Set permissions
            user.setSa_menu_permission_id(userRequestDto.getPermissionID());
            

            UserEntity createdUser = userRepo.save(user);

            return this.userEntityToUserRespDto(createdUser);
        } else {
            // User already exists, throw an exception
            throw new UserAlreadyExistsException("User with email " + userRequestDto.getUsername() + " already exists");
        }
    }

    public UserEntity userReqDtoToUserEntity(Object userReqDto){
        UserEntity user = this.modelMapper.map(userReqDto,UserEntity.class);
        return user;
    }
    
    public UserResp userEntityToUserRespDto(UserEntity user){
        UserResp userRespDto = this.modelMapper.map(user,UserResp.class);
        return userRespDto;
    }

    @Override
    public UserEntity findUserLogin(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findLoginUser(username);
        return user;
    }

    @Override
    public void updateUserLogLogin(Long userID, HashMap<String, Object> updateInfo) {
        UserEntity foundUser = this.userRepo.findByID(userID);
        System.out.println("foundUser:"+foundUser.getUsername());
        if (foundUser.getUsername() != null) {
            for (Map.Entry<String, Object> entry : updateInfo.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();
                System.out.println("before fieldName:"+fieldName+" value:"+fieldValue);
                // Update the field if it exists in the UserEntity class
                try {
                    Field field = foundUser.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(foundUser, fieldValue);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
                System.out.println("after fieldName:"+fieldName+" value:"+fieldValue);
            }
            
            userRepo.save(foundUser);
        }
    }

    @Override
    public void updateUser(UpdateUserDto req, String updatedBy) {
        UserEntity foundUser = this.userRepo.findByID(req.getId());
        Timestamp timeNow = DateTime.getTimeStampNow();
        if (foundUser != null){
            System.out.println("found user: " + foundUser.getId());

            if (!req.getName().isEmpty()){
                foundUser.setName(req.getName());
            }
            if (!req.getDepartmentName().isEmpty()){
                foundUser.setDepartmentname(req.getDepartmentName());
            }
            if (!req.getPhonenumber().isEmpty()){
                foundUser.setPhoneNumber(req.getPhonenumber());
            }
            if (!req.getEmail().isEmpty()){
                foundUser.setEmail(req.getEmail());
            }
            if (!req.getPassword().isEmpty()){
                foundUser.setPassword(authConfig.passwordEncoder().encode(req.getPassword()));
            }
            if (req.getPermissionID() != null){
                foundUser.setSa_menu_permission_id(req.getPermissionID());
            }
            
            foundUser.setUpdated_Date(timeNow);
            foundUser.setUpdated_by(updatedBy);
            
            userRepo.save(foundUser);
        }else{
            System.out.println("not found user");
        }

        return;
        
    }
    
}