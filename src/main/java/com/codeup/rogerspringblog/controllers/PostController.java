package com.codeup.rogerspringblog.controllers;

import com.codeup.rogerspringblog.models.Post;
import com.codeup.rogerspringblog.models.User;
import com.codeup.rogerspringblog.repositories.PostImageRepository;
import com.codeup.rogerspringblog.repositories.PostRepository;
import com.codeup.rogerspringblog.repositories.UserRepository;
import com.codeup.rogerspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final PostImageRepository postImageDao;
    private final EmailService emailServiceDao;


    public PostController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao, EmailService emailServiceDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.postImageDao = postImageDao;
        this.emailServiceDao = emailServiceDao;

    }

    //! SHOW ALL
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


    //!CREATE
    @GetMapping("/posts/create")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        User user = userDao.getOne(1L); //assign id to user object using user repo
        post.setUser(user); //Post model- set user to specific post
        postDao.save(post); //post repo extends jpa repo
        emailServiceDao.prepareAndSend(post,"You just made a post","you just made a post"); //EmailService.java model
        return "redirect:/posts";
    }

    //!DELETE
    @PostMapping("/posts/delete")
    public String deletePostById(@RequestParam Long postId, Model model) {
        postDao.deleteById(postId);
        return "redirect:/posts";
    }

    @GetMapping("/posts/details/{id}")
    public String returnOneToOneView(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.findById(id));
        return "posts/show";
    }

    //!EMAIL
//    @GetMapping("/posts/email")
//    public String createEmail(@PathVariable long id, Model model) {
//        model.addAttribute("email", emailServiceDao.findById(id));
//        return "profile";
//    }


//    @GetMapping("/posts/email")
//    @ResponseBody
//    public String sendEmail(@RequestParam long id, Model model){
//        emailService.prepareAndSend(emailServiceDao.getOne(1L), "subject","description");
//        return "Sending email...";
//    }

}