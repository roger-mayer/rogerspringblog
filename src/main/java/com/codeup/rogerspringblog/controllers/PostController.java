package com.codeup.rogerspringblog.controllers;

import com.codeup.rogerspringblog.exception.PostException;
import com.codeup.rogerspringblog.models.Post;
import com.codeup.rogerspringblog.models.User;
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

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }
    //! SHOW ALL
    @GetMapping("/posts")
    public String all(Model model) {
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
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

    //! SHOW ONE
//    @GetMapping("/posts/{id}")
//    public String showUserById(
//            @PathVariable long id,
//            Model model
//    ) throws PostException {
//        Post found = postDao.findById(id)
//                .orElseThrow(()-> new PostException());

//        model.addAttribute("post", found);
//        return "singlePost";

//    }

    //!CREATE
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
        Post post = new Post(title, description);
        postDao.save(post);
        return "redirect:/posts";
    }


    @PostMapping("/posts/create")
    public String submitPost(@RequestParam String title, @RequestParam String body) {
        long random = (long) ((Math.random() * 3) + 1);
        Post newPost = new Post(title, body, userDao.findById(random));
        User user = userDao.findById(random);
        user.getPosts().add(newPost);
        postDao.save(newPost);
        return "redirect:/posts";
    }


    //! EDIT
//    @GetMapping("/posts/edit/{id}")
//    public String showEdit(
//            @PathVariable long id,
//            Model model
//    ) throws PostException {
//        Post post = postDao.findById(id)
//                .orElseThrow(()-> new PostException());
//        model.addAttribute("post", post);
//        return "edit";
//    }

//    @PostMapping("/posts/edit/{id}")
//    public String editPost(
//            @RequestParam long id,
//            @RequestParam String title,
//            @RequestParam String description,
//            Model model
//    ) {
//        if(title.isEmpty() || description.isEmpty()){
//            model.addAttribute("alert", true);
//            return "redirect:/posts/edit/"+id;
//        }
//        Post post = new Post(id, title, description);
//        postDao.save(post);
//        return "redirect:/posts/"+id;

//    }

    //! Delete
//    @GetMapping("/posts/delete/{id}")
//    public String showDelete(
//            @PathVariable long id,
//            Model model
//    ) throws PostException {
//        postDao.findById(id)
//                .orElseThrow(()-> new PostException());
//        model.addAttribute("id", id);
//        return "delete";
//    }
//
//    @PostMapping("/posts/delete/{id}")
//    public String deletePost(
//            @PathVariable long id
//    ) throws PostException {
//        postDao.findById(id)
//                .orElseThrow(()-> new PostException());
//        postDao.deleteById(id);
//        return "redirect:/posts";
//    }
}