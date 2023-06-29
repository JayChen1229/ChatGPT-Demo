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

    // 獲得所有使用者資料
    @GetMapping("/users")
    public List<User> getUser(){
        return userService.findAllUser();
    }

    // 獲得所有使用者資料(用分數排序)
    @GetMapping("/users/scoreRank")
    public List<User> getUserByScore(){
        return userService.findAllUserOrderByMaxScore();
    }
    // 獲得所有使用者資料(用等級排序)
    @GetMapping("/users/levelRank")
    public List<User> getUserByLevel(){
        return userService.findAllUserOrderLevel();
    }

    // 獲得單一使用者資料
    @GetMapping("/users/userName")
    public List<User> getUserByUserName(@RequestParam String userName){
        return userService.findUserByUserName(userName);
    }

    // 儲存使用者資料
    @PutMapping("/users")
    public boolean updateUser(@RequestBody User user){
        userService.save(user);
        return true;
    }

    // 修改使用者資料
    @PutMapping("/users/{userId}")
    public boolean updateUser(@PathVariable int userId, @RequestParam int money){
        User user = userService.findUserById(userId);
        user.setMoney(money);
        userService.save(user);
        return true;
    }
    // 更新使用者最高分數
    @PutMapping("/usersScore")
    public boolean updateUserMaxScore(@RequestBody User user){
        User theuser = userService.findUser(user);
        System.out.println(user.getMaxScore());
        if(theuser.getMaxScore() < user.getMaxScore()){
            user.setPlayTimes(user.getPlayTimes()+1);
            userService.save(user);
            return true;
        }
        theuser.setPlayTimes(user.getPlayTimes()+1);
        userService.save(theuser);
        return false;
    }
    // 刪除使用者
    @DeleteMapping("/users/{userId}")
    public boolean deleteUser(@PathVariable int userId){
        if(userService.findUserById(userId)!= null){
            userService.deleteUser(userId);
            return true;
        }
        return false;
    }
}
