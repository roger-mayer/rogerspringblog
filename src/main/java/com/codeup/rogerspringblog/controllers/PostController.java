package com.codeup.rogerspringblog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String index(){
        return "posts index page";

    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individual(@PathVariable String id){
        return "posts index page" + id;

    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String create(){
        return "posts index page";

    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String createdPost(){
        return "new post created";

    }

}
