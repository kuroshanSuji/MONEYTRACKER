package com.kuroshan.MONEYTRACKER.controller;

import com.kuroshan.MONEYTRACKER.request.Registration;
import com.kuroshan.MONEYTRACKER.request.UserInfoUpdate;
import com.kuroshan.MONEYTRACKER.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exp")
@Slf4j
public class UserController {
    // this controller class which hold's user reg,login,update
    //login will be based on UserDetails service( spring security)
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void userRegistration(@RequestBody @Valid Registration registration)
    {
        if(userService.chckCredential(registration))
        {
            userService.addUser(registration);
        }

    }

    @GetMapping("/upInfo")
    public void updateUserInfo(@RequestBody @Valid UserInfoUpdate userInfoUpdate)
    {
        userService.updateUserInfo(userInfoUpdate);
    }
}

