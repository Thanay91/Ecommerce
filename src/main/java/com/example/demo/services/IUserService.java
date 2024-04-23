package com.example.demo.services;

import com.example.demo.models.User;

import java.util.List;

public interface IUserService {

    public List<User> getAllUsers(Integer limit, String sortType);

    public User getUserById(Long id);

}
