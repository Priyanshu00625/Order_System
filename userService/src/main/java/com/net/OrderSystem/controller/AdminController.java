package com.net.OrderSystem.controller;

import com.net.OrderSystem.entity.User;
import com.net.OrderSystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/create_admin")
    public ResponseEntity<User> createAdmin(@RequestBody User user){
        try{
            userService.saveAdmin(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e){
            log.error("error to save admin" , e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all_user")
    public ResponseEntity<List<User>> getAllUser(){
        try{
            List<User> allUser = userService.findAllUser();
            return new ResponseEntity<>(allUser , HttpStatus.OK);
        }catch (Exception e){
            log.error("error in to find all user" , e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
