package com.codeup.rogerspringblog.controllers;

import com.codeup.rogerspringblog.exception.PostException;
import com.codeup.rogerspringblog.models.Post;
import com.codeup.rogerspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
//    Post post1 = new Post("Title1", "Body1");
//    Post post2 = new Post("Title2", "Body2");
//    Post post3 = new Post("Title3", "Body3");
//    Post post4 = new Post("Title4", "Body4");
//
//    @GetMapping("/posts")
//    public String postsIndex(Model model){
//        ArrayList<Post> posts = new ArrayList<>();
//        posts.add(post1);
//        posts.add(post2);
//        posts.add(post3);
//        posts.add(post4);
//        model.addAttribute("posts", posts);
//      return "posts/index";
//    }
//
//    @GetMapping("/posts/{id}")
//    public String viewPost(@PathVariable int id, Model model){
//        ArrayList<Post> posts = new ArrayList<>();
//        posts.add(post1);
//        posts.add(post2);
//        posts.add(post3);
//        posts.add(post4);
//        Post postToShow = posts.get(id - 1);
//        model.addAttribute("post", postToShow);
//        return "posts/show";
//    }
//
//    @GetMapping("posts/create")
//    @ResponseBody
//    public String createNewPost(){
//        return "view form for creating posts";
//    }
//
//    @PostMapping("posts/create")
//    @ResponseBody
//    public String publishPost(){
//        return "Publishing new post";
//    }

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

//    @GetMapping("/posts")
//    public String index(Model model) {
//
//        model.addAttribute("posts", postDao.findAll());
//        return "posts/index";
//    }

    //! SHOW ALL
    @GetMapping("/posts")
    public String all(Model model){
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

//    @PostMapping("/posts/delete")
//    public String deletePost(@RequestParam long postId){
//        postDao.deleteById(postId);
//        return "posts/index";
//    }

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
        return "redirect:/posts/index";
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