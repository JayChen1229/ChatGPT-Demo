package com.tw.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
public class User implements Serializable {
    @Id
    int id;
    String username;
    String email;
    String password;
    int frequency;
    int level;
    int money;
    @Column(name = "max_score")
    int maxScore;
}
