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

    @GetMapping("/users/scoreRank")
    public List<User> getUserByScore(){
        return userService.findAllUserOrderByMaxScore();
    }
    @GetMapping("/users/levelRank")
    public List<User> getUserByLevel(){
        return userService.findAllUserOrderLevel();
    }
    @GetMapping("/users/userName")
    public List<User> getUserByUserName(@RequestParam String userName){
        return userService.findUserByUserName(userName);
    }




    @PutMapping("/users")
    public Boolean updateUser(@RequestBody User user){
        userService.save(user);
        return true;
    }

    @PutMapping("/users/{userId}")
    public Boolean updateUser(@PathVariable int userId, @RequestParam int money){
        User user = userService.findUserById(userId);
        user.setMoney(money);
        userService.save(user);
        return true;
    }
    @PutMapping("/usersScore")
    public Boolean updateUserMaxScore(@RequestBody User user){
        User theuser = userService.findUser(user);
        System.out.println(user.getMaxScore());
        if(theuser.getMaxScore() < user.getMaxScore()){
            userService.save(user);
            return true;
        }
        return false;
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
