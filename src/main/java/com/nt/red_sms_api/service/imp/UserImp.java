package com.nt.red_sms_api.service.imp;

import com.nt.red_sms_api.config.AuthConfig;
import com.nt.red_sms_api.dto.req.UserRequestDto;
import com.nt.red_sms_api.dto.resp.UserResp;
import com.nt.red_sms_api.entity.UserEntity;
import com.nt.red_sms_api.exp.UserAlreadyExistsException;
import com.nt.red_sms_api.repo.UserRepo;
import com.nt.red_sms_api.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ModelMapper modelMapper;
    @Autowired
    private AuthConfig authConfig;
    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        return user;
    }

    @Override
    public List<UserResp> getAllUser() {
        List<UserEntity> userEnitiys = userRepo.findAll();
        List<UserResp> userResponseDtoList = userEnitiys.stream().map(user->this.userEntityToUserRespDto(user)).collect(Collectors.toList());
        return userResponseDtoList;


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

    @Override
    public void updateUser(Long userID, HashMap<String, Object> updateInfo) {
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


    public UserEntity userReqDtoToUserEntity(UserRequestDto userReqDto){
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
    
}