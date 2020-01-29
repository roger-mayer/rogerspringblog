package com.codeup.rogerspringblog.controllers;

import com.codeup.rogerspringblog.exception.PostException;
import com.codeup.rogerspringblog.models.Post;
import com.codeup.rogerspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    //! SHOW ALL
    @GetMapping("/posts")
    public String all(Model model){
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }


    //! SHOW ONE
    @GetMapping("/posts/{id}")
    public String showUserById(
            @PathVariable long id,
            Model model
    ) throws PostException {
        Post found = postDao.findById(id)
                .orElseThrow(()-> new PostException());

        model.addAttribute("post", found);
        return "singlePost";

    }

    //!CREATE
    @GetMapping("/posts/create")
    public String showForm(){
        return "create";
    }
    //
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

    //! EDIT
    @GetMapping("/posts/edit/{id}")
    public String showEdit(
            @PathVariable long id,
            Model model
    ) throws PostException {
        Post post = postDao.findById(id)
                .orElseThrow(()-> new PostException());
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String editPost(
            @RequestParam long id,
            @RequestParam String title,
            @RequestParam String description,
            Model model
    ) {
        if(title.isEmpty() || description.isEmpty()){
            model.addAttribute("alert", true);
            return "redirect:/posts/edit/"+id;
        }
        Post post = new Post(id, title, description);
        postDao.save(post);
        return "redirect:/posts/"+id;

    }

    //! Delete
    @GetMapping("/posts/delete/{id}")
    public String showDelete(
            @PathVariable long id,
            Model model
    ) throws PostException {
        postDao.findById(id)
                .orElseThrow(()-> new PostException());
        model.addAttribute("id", id);
        return "delete";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(
            @PathVariable long id
    ) throws PostException {
        postDao.findById(id)
                .orElseThrow(()-> new PostException());
        postDao.deleteById(id);
        return "redirect:/posts";
    }



}