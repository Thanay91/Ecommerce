package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam (required = false) String  sort,
                                                  @RequestParam (required = false) Integer limit
                                                  ){
        List<User> users = userService.getAllUsers(limit, sort);
        ResponseEntity response = new ResponseEntity<>(users, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user =  userService.getUserById(id);
        ResponseEntity response = new ResponseEntity(user, HttpStatus.OK);
        return response;
    }


}
