package com.synchrony.assessment.userimagelibrary.controller;

import com.synchrony.assessment.userimagelibrary.dto.User;
import com.synchrony.assessment.userimagelibrary.exception.InvalidUserException;
import com.synchrony.assessment.userimagelibrary.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

   @Autowired
   private  final UserDetailsServiceImpl userDetailsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * UserLoginController Constructor
     * @param loginService
     */
    public UserController(UserDetailsServiceImpl loginService) {
        this.userDetailsService = loginService;
    }

    /**
     * Check if User exists in the database
     * @param user
     * @return
     */
    @PostMapping("/login")
    public UserDetails isUserAuthenticated(User user){
        return userDetailsService.loadUserByUsername(user.getUserName());
    }

    @PostMapping("/crete")
    public String createUser(User user) throws InvalidUserException {
        return userDetailsService.createUser(user);
    }


}
