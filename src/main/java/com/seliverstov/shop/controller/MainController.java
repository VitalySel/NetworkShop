package com.seliverstov.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

@Controller
public class MainController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value="/")
    public String home(Model model){
        return "home";
    }

    @RequestMapping(value="/user")
    public String user(){
        return "user";
    }
}
