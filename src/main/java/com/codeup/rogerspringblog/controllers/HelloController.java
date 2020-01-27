package com.codeup.rogerspringblog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

//    @GetMapping("/hello/{name}")
//    @ResponseBody
//    public String hello(@PathVariable String name){
//        return "Hello " + name;
//    }
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }
}