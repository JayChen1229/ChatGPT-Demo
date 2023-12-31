package com.tw.controller;


import com.tw.model.User;
import com.tw.service.UserService;
import com.tw.session.UserSession;
import jakarta.servlet.http.HttpSession;
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

    // Get user
    @GetMapping()
    public User getUser(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user;
    }


    // Get all users
    @GetMapping("/all")
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

    @PutMapping("/money-50")
    public boolean UserMoneyDown(HttpSession session) {
        User user = (User) session.getAttribute("user");
        user.setMoney(user.getMoney()-50);
        userService.save(user);
        return true;
    }

    @PutMapping("/levelUp")
    public boolean UserLevelUp(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int requiredExp = 100;
        for (int i = 2; i <= user.getLevel(); i++) {
            requiredExp += 10 * (i - 1);
        }
        user.setMoney(user.getMoney()-requiredExp);

        if(user.getMoney() > 0){
            user.setLevel(user.getLevel()+1);
            userService.save(user);
            return true;
        }
        return false;
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
    public boolean updateUserMaxScore(@RequestParam int score, HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println(score);
        if(user.getMaxScore() < score){
            user.setMaxScore(score);
        }
        int money = user.getMoney();
        user.setMoney(money+score*20-50);
        user.setPlayTimes(user.getPlayTimes()+1);
        userService.save(user);
        return true;

//        User theuser = userService.findUser(user);
//        System.out.println(user.getMaxScore());
//        if(theuser.getMaxScore() < user.getMaxScore()){
//            user.setPlayTimes(user.getPlayTimes()+1);
//            userService.save(user);
//            return true;
//        }
//        theuser.setPlayTimes(user.getPlayTimes()+1);
//        userService.save(theuser);
//        return false;
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
