package com.codeup.rogerspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NavbarController {

//    @GetMapping("/home")
//    @ResponseBody
//    public String navBarHome(){
//        return "this is a home page for the blog";
//    }

    @GetMapping("/about")
    public String navBarAbout(){
        return "/about";
    }
//
//    @GetMapping("/contact")
//    @ResponseBody
//    public String navBarContact(){
//        return "this is a ' contact form' page for the blog";
//    }
}