package com.codeup.rogerspringblog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postIndex(){
        return "posts index page";

    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewPost(@PathVariable String id){
        return "posts individual post of " + id;

    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String create(){
        return "create post form";

    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String submitPost(){
        return "Creating a new post";

    }

}
