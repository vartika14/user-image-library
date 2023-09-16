package com.synchrony.assessment.userimagelibrary.service;

import com.synchrony.assessment.userimagelibrary.dto.User;
import com.synchrony.assessment.userimagelibrary.entity.UserEntity;
import com.synchrony.assessment.userimagelibrary.exception.InvalidUserException;
import com.synchrony.assessment.userimagelibrary.jpa.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private static final String SUCCESS_STATUS = "Success";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    public UserDetailsServiceImpl(UserRepository individualRepository) {
        this.userRepository = individualRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);
        LOGGER.info("Find if user exists int the database : {}", username );
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

    public String createUser(User user) throws InvalidUserException {
       if (null == user.getUserName()) {
           throw new InvalidUserException("User name must not be empty");
       }
       else{
           UserEntity userEntity = new UserEntity();
           mapUsertoUserEntity(user,userEntity);
           try {
               userRepository.save(userEntity);
               return SUCCESS_STATUS;
           }catch (Exception e) {
               throw new InvalidUserException("Error occurred while saving the user in database");
           }

       }
    }

    private void mapUsertoUserEntity(User user, UserEntity userEntity) {
        userEntity.setUserName(user.getUserName());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPassword(user.getPassword());
    }


}
