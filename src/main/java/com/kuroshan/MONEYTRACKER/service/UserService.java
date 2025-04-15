package com.kuroshan.MONEYTRACKER.service;

import com.kuroshan.MONEYTRACKER.exception.UserNotAddedException;
import com.kuroshan.MONEYTRACKER.exception.UserNotFoundException;
import com.kuroshan.MONEYTRACKER.model.User;
import com.kuroshan.MONEYTRACKER.repository.UserRepository;
import com.kuroshan.MONEYTRACKER.request.Registration;
import com.kuroshan.MONEYTRACKER.request.UserInfoUpdate;
import com.kuroshan.MONEYTRACKER.response.GenericResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public boolean chckCredential(Registration registration) {
        return userRepository.findByEmail(registration.getEmail()) == null;
    }

    public GenericResponse<?> addUser(Registration registration) {
        User newUser;
        try {
            newUser = registration.toUser();
            userRepository.save(newUser);
            log.info(newUser.getName());
        } catch (Exception e) {
            throw UserNotAddedException.builder().registration(registration).build();
        }
        return GenericResponse.builder().httpStatusCode(HttpStatus.valueOf(200)).httpStatus(HttpStatus.CREATED).object(newUser).build();
    }

    public GenericResponse<?> updateUserInfo(UserInfoUpdate userInfoUpdate) {
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
                }else {
                    throw UserNotFoundException.builder().userId(userInfoUpdate.getUserId()).build();
                }
                return GenericResponse.builder().httpStatus(HttpStatus.ACCEPTED).httpStatusCode(HttpStatusCode.valueOf(200)).build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByName(username);
        return user;
    }
}
