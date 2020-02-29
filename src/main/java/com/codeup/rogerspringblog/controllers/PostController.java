package com.codeup.rogerspringblog.controllers;

import com.codeup.rogerspringblog.exception.PostException;
import com.codeup.rogerspringblog.models.Post;
import com.codeup.rogerspringblog.models.User;
import com.codeup.rogerspringblog.repositories.PostImageRepository;
import com.codeup.rogerspringblog.repositories.PostRepository;
import com.codeup.rogerspringblog.repositories.UserRepository;
//import com.codeup.rogerspringblog.services.EmailService;
//import com.codeup.rogerspringblog.services.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final PostImageRepository postImageDao;
//    private final EmailService emailService;
//    private final PostService postService;
//    private PostService postService;

    public PostController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.postImageDao = postImageDao;
    }


//    public PostController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao, EmailService emailService) {
//        this.postDao = postDao;
//        this.userDao = userDao;
//        this.postImageDao = postImageDao;
//        this.emailService = emailService;

//    }

//    public PostController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao, EmailService emailService, PostService postService) {
//        this.postDao = postDao;
//        this.userDao = userDao;
//        this.postImageDao = postImageDao;
//        this.emailService = emailService;
//        this.postService = postService;
//    }

    //SHOW ALL
    @GetMapping("/posts")
    public String all(Model model) {
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return "posts/index";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPostForm
            (@PathVariable Long id,
           Model model){
        model.addAttribute("post", postDao.getOne(id));

        return "posts/edit";
    }


    @PostMapping("/posts/edit")
    public String editPost(@ModelAttribute Post post){
        postDao.save(post);
        return "redirect:/posts";
    }


    //CREATE
    @GetMapping("/posts/create")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user); //Post model- set user to specific post
        postDao.save(post); //post repo extends jpa repo
//        emailService.prepareAndSend(post,"You just made a post","you just made a post"); //EmailService.java model
        return "redirect:/posts";
    }

    //DELETE

    @GetMapping("/posts/delete/{id}")
    public String showDelete(
            @PathVariable long id,
            Model model) {
        model.addAttribute("id", id);
        return "/delete";
    }


    @PostMapping("/posts/delete/{id}")
    public String deletePost(
            @PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }




    @GetMapping("/posts/details/{id}")
    public String returnOneToOneView(@PathVariable long id, Model model){
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userId", u.getId());
        model.addAttribute("post", postDao.findById(id));
        return "posts/show";
    }

}