package com.tw.controller;


import com.tw.model.User;
import com.tw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<User> getUser(){
        return userService.findAllUser();
    }

    @PutMapping("/users")
    public Boolean updateStudent(@RequestBody User user){
        userService.save(user);
        return true;
    }

    @PutMapping("/users/{userId}")
    public Boolean updateStudent(@PathVariable int userId, @RequestParam int money){
        User user = userService.findUserById(userId);
        user.setMoney(money);
        userService.save(user);
        return true;
    }
    @DeleteMapping("/users/{userId}")
    public Boolean deleteUser(@PathVariable int userId){
        if(userService.findUserById(userId)!= null){
            userService.deleteUser(userId);
            return true;
        }
        return false;
    }
}
