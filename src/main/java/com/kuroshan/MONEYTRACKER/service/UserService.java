package com.kuroshan.MONEYTRACKER.service;

import com.kuroshan.MONEYTRACKER.model.User;
import com.kuroshan.MONEYTRACKER.request.Registration;
import com.kuroshan.MONEYTRACKER.request.UserInfoUpdate;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public boolean chckCredential(@Valid Registration registration) {
         /*chck the user credential with the user database, chck if
           user emial alredy present
                if present return false
            if not present return true*/
        return true;
    }

    public void addUser(@Valid Registration registration) {
         User newUser=registration.toUser();
         log.info(newUser.getName());
    }

    public void updateUserInfo(@Valid UserInfoUpdate userInfoUpdate) {
        /* in this method we need to update the user info present in the db ( should not delete the info
           just update the info)*/
    }
}
