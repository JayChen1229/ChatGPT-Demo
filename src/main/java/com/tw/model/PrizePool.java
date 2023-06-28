package com.tw.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PrizePool {
    @Id
    int id;
    double lumpSum;

}
