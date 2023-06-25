package com.tw.controller;


import com.tw.model.User;
import com.tw.repository.UserRepository;
import com.tw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User login(@RequestBody User user){
        User theUser = userService.findUser(user);
        if(theUser != null){
            return theUser;
        }
        return null;
    }

    @PostMapping("/register")
    public Boolean register(@RequestBody User user){
        User theUser = userService.findUser(user);
        if(theUser == null){
            userService.save(user);
            return true;
        }
        return false;
    }

}
