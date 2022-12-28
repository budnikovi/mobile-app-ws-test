package com.example.controller;

import com.example.model.request.UserDetailsRequestModel;
import com.example.model.response.UserRest;
import com.example.service.impl.UserServiceImpl;
import com.example.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public String getUser() {
        return "get user was called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }


}
