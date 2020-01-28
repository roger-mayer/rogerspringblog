package com.codeup.rogerspringblog.controllers;

import com.codeup.rogerspringblog.models.Post;
import com.codeup.rogerspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }


    @GetMapping("/posts")
    public String postIndex(Model model){
        ArrayList<Post> postList = new ArrayList<Post>();
        postList.add(new Post(2, "Title 2", "Description 2"));
        postList.add(new Post(3, "Title 3", "Description 3"));
        model.addAttribute("posts", postList);
        return "posts/index";

    }
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable long id, Model model){
        Post post1 = new Post(id, "title 1", "description 1");
        model.addAttribute("title", post1.getTitle());
        model.addAttribute("body", post1.getBody());
        return "posts/show";

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

    //JPA view all adds

    @GetMapping("/posts/all")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    //de

}
