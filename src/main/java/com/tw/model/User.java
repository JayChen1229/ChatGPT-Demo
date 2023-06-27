package com.tw.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    int id;
    String username;
    String email;
    String password;
    int frequency;
    int level;
    int money;
}
