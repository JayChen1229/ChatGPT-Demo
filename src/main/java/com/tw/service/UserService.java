package com.tw.service;


import com.tw.model.User;
import com.tw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(User user){
        return userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
    }
    public User findUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }
    public List<User> findAllUserOrderByMaxScore(){
        return userRepository.findAllByOrderByMaxScoreDesc();
    }
    public List<User> findAllUserOrderLevel(){
        return userRepository.findAllByOrderByLevelDesc();
    }

    public List<User> findUserByUserName(String userName){
        return userRepository.findByUserNameOrderByMaxScoreDesc(userName);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}
