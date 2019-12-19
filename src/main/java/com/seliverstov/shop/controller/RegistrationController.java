package com.seliverstov.shop.controller;

import com.seliverstov.shop.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.seliverstov.shop.models.User;

import javax.sql.DataSource;

@Controller
public class RegistrationController {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepositoryImpl userRepo;

    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }

    @GetMapping(value="/register")
    public String register(){
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(@RequestParam String username,@RequestParam String password,@RequestParam String email){

        if (userRepo.findByUsername(username) != null || username == "" || password=="" || userRepo.findByEmail(email)!=null || email == ""){
            return "406";
        }
        User user = new User(dataSource);
        user.create(username,password,email);
        return "redirect:/";
    }
}
