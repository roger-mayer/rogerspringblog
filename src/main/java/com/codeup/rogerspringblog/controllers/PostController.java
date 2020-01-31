package com.codeup.rogerspringblog.controllers;

import com.codeup.rogerspringblog.exception.PostException;
import com.codeup.rogerspringblog.models.Post;
import com.codeup.rogerspringblog.models.User;
import com.codeup.rogerspringblog.repositories.PostImageRepository;
import com.codeup.rogerspringblog.repositories.PostRepository;
import com.codeup.rogerspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final PostImageRepository postImageDao;

    public PostController(PostRepository postDao, UserRepository userDao, PostImageRepository postImageDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.postImageDao = postImageDao;
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

    @GetMapping("/posts/edit")
    public String editPostForm(
            @RequestParam String postTitle,
            @RequestParam String postBody,
            @RequestParam Long postId, Model model) {
        model.addAttribute("postTitle", postTitle);
        model.addAttribute("postBody", postBody);
        model.addAttribute("postId", postId);
        return "posts/edit";

    }

    @PostMapping("/posts/edit")
    public String editPost(
            @RequestParam String newTitle,
            @RequestParam String newBody,
            @RequestParam long sameId) {
        Post updatePost = new Post(sameId, newTitle, newBody);
        postDao.save(updatePost);
        return "redirect:/posts";
    }


    @PostMapping("/posts/delete")
    public String deletePostById(@RequestParam Long postId, Model model) {
        postDao.deleteById(postId);
        return "redirect:/posts";
    }

    //!CREATE
    //model param
//    @GetMapping("/posts/create")
//    public String createForm(Model model) {
//        model.addAttribute("post", new Post());
//        return "create";
//    }

    @GetMapping("/posts/create")
    public String createForm() {
        return "create";
    }


    @PostMapping("/posts/create")
    public String createPost(
            @RequestParam(name = "title") String title,
            @RequestParam String description,
            Model model
    ) {
//        Post post = new Post(title, description, userDao.getB
        Post post = new Post(title, description);
        User user = userDao.getOne(1L);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }


//    @PostMapping("/posts/create")
//    public String submitPost(@RequestParam String title, @RequestParam String description) {
//        long random = (long) ((Math.random() * 3) + 1);
//        Post newPost = new Post(title, description, userDao.findById(random));
//        User user = userDao.findById(random);
//        user.getPosts().add(newPost);
//        postDao.save(newPost);
//        return "redirect:/posts";
//    }

    @GetMapping("/posts/{id}/details")
    public String returnOneToOneView(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.findById(id));
        return "posts/show";
    }

}