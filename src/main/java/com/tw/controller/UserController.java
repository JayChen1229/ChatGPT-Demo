package com.tw.controller;


import com.tw.model.User;
import com.tw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUser();
    }

    // Get all users sorted by score
    @GetMapping("/score")
    public List<User> getUsersSortedByScore(){
        return userService.findAllUserOrderByMaxScore();
    }
    // Get all users sorted by level
    @GetMapping("/level")
    public List<User> getUsersSortedByLevel(){
        return userService.findAllUserOrderLevel();
    }

    // Get a single user by username
    @GetMapping("/{userName}")
    public List<User> getUserByUserName(@PathVariable String userName){
        return userService.findUserByUserName(userName);
    }

    // Save a user
    @PutMapping
    public boolean saveUser(@RequestBody User user) {
        userService.save(user);
        return true;
    }

    // Update a user by ID
    @PutMapping("/{userId}")
    public boolean updateUser(@PathVariable int userId, @RequestParam int money){
        User user = userService.findUserById(userId);
        user.setMoney(money);
        userService.save(user);
        return true;
    }
    // Update a user's maximum score
    @PutMapping("/score")
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
    // Delete a user by ID
    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable int userId){
        if(userService.findUserById(userId)!= null){
            userService.deleteUser(userId);
            return true;
        }
        return false;
    }
}
