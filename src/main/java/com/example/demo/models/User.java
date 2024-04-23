package com.example.demo.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@Entity
public class User extends BaseModel{

    @OneToOne
    Address address;
    String email;
    String userName;
    String Password;
    String phone;

    @Embedded
    FullName fullName;


}
