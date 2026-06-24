package com.net.OrderSystem.services;

import com.net.OrderSystem.entity.User;
import com.net.OrderSystem.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setTime(LocalDateTime.now());
            user.setRole("USER");
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.out.println("Exception in the save new user "+ e);
            return false;
        }
    }
    public boolean saveUser(User user){
        userRepository.save(user);
        return true;
    }
    public User findByUserName(String userName){
        return userRepository.findByuserName(userName);
    }

    public boolean deleteByuserName(String userName){
         userRepository.deleteByUserName(userName);
        return true;
    }
    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole( "ADMIN");
        userRepository.save(user);
    }
}
