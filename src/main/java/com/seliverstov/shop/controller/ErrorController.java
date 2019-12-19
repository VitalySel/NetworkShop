package com.seliverstov.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }
}
