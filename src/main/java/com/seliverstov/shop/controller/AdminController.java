package com.seliverstov.shop.controller;

import com.seliverstov.shop.models.Logging;
import com.seliverstov.shop.repository.LoggingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.seliverstov.shop.models.User;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    DataSource dataSource;
    @Autowired
    LoggingRepositoryImpl loggingRepository;

    @RequestMapping(value="/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping(value = {"/makeadmin"}, method = RequestMethod.GET)
    public String showMakeAdmin(Model model) {
        User user = new User(dataSource);
        model.addAttribute("users",user.getAllUsers());
        return "makeadmin";
    }
    @RequestMapping(value = {"/makeadmin"}, method = RequestMethod.POST)
    public String makeOrder(@RequestParam String userid) {
        User user = new User(dataSource);
        user.makeAdmin(userid);
        return "redirect:/";
    }

    @RequestMapping(value={"/logging"} ,method = RequestMethod.GET)
    public String getLogg(Model model){
        List logg = loggingRepository.getLogging();
        model.addAttribute("loggs",logg);
        return "logging";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String getUsers(Model model) {
        User user = new User(dataSource);
        model.addAttribute("users",user.getAllUsers());
        return "user";
    }
}
