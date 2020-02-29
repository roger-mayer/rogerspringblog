//package com.codeup.rogerspringblog.services;
//
//import com.codeup.rogerspringblog.models.Post;
//import com.codeup.rogerspringblog.models.User;
//import com.codeup.rogerspringblog.repositories.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PostService {
//    private UserRepository userDao;
//
//    public PostService(UserRepository userDao) {
//        this.userDao = userDao;
//    }
//
//    //!Get user by username
//    public User getUserByUsername(String username){
//        List<User> users = userDao.findAll();
//        User found = new User();
//        for (User user : users) {
//            if(user.getUsername().toLowerCase().equals(username.toLowerCase())){
//                found.setId(user.getId());
//                found.setUsername(username);
//                found.setEmail(user.getEmail());
//                found.setPassword(user.getPassword());
//            }
//        }
//        return found;
//    }
//
//
//
//
////    //! Add user to categories
////    public void addPostToCategories(Post post){
////        System.out.println("AddPostToCategories");
////        for (Categories category : post.getCategories()) {
////            category.addPost(post);
////        }
////    }
//}