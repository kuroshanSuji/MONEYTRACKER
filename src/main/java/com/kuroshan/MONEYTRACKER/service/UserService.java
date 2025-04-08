package com.kuroshan.MONEYTRACKER.service;

import com.kuroshan.MONEYTRACKER.model.User;
import com.kuroshan.MONEYTRACKER.repository.UserRepository;
import com.kuroshan.MONEYTRACKER.request.Registration;
import com.kuroshan.MONEYTRACKER.request.UserInfoUpdate;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean chckCredential(@Valid Registration registration) {
        return userRepository.findByEmail(registration.getEmail()) == null;
    }

    public void addUser(@Valid Registration registration) {
         User newUser=registration.toUser();
         userRepository.save(newUser);
         log.info(newUser.getName());
    }

    public void updateUserInfo(@Valid UserInfoUpdate userInfoUpdate) {
        /* in this method we need to update the user info present in the db ( should not delete the info
           just update the info)*/
            Optional<User> optional=userRepository.findById(userInfoUpdate.getUserId());
            if(optional.isPresent())
            {
                 User existingUser=optional.get();
                if(userInfoUpdate.getPassword()!=null)
                {
                    existingUser.setPassword(userInfoUpdate.getPassword());
                }
                if(userInfoUpdate.getName()!=null)
                {
                    existingUser.setName(userInfoUpdate.getName());
                }
                if(userInfoUpdate.getEmail()!=null)
                {
                    existingUser.setEmail(userInfoUpdate.getEmail());
                }
                userRepository.save(existingUser);
                // this save method , chck whether the data already present in the database if yes, 
                 // it will update the info , it will not save another data.
            }
            // here we need to update the exception case
    }
}
