package com.codeup.rogerspringblog.models;
import javax.persistence.*;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(columnDefinition = ("INT UNSIGNED"))
   private long id;

   @Column(nullable = false, length = 50)
   private String title;

   @Column(length = 1000, nullable = false)
   private String description;


   //relates to PostDetail Model
   @OneToOne
   private PostDetails postDetails;

   //relates to User Model
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   //relates to PostImage Model
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
   private List<PostImage> images;



   public Post(){};

   public Post(String title, String description, User user){
      this.user = user;
      this.title = title;
      this.description = description;
   }

   public Post(long id, String title, String description){
      this.id = id;
      this.title = title;
      this.description = description;
   }

   public Post(long id, String title, String description, PostDetails postDetails) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.postDetails = postDetails;
   }

   public Post(long id, PostDetails postDetails, User user, List<PostImage> images) {
      this.id = id;
      this.postDetails = postDetails;
      this.user = user;
      this.images = images;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public PostDetails getPostDetails() {
      return postDetails;
   }

   public void setPostDetails(PostDetails postDetails) {
      this.postDetails = postDetails;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public List<PostImage> getImages() {
      return images;
   }

   public void setImages(List<PostImage> images) {
      this.images = images;
   }
}

