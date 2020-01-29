package com.codeup.rogerspringblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")

    public String showLanding(){
        return "home";

    }

}
