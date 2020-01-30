package com.codeup.rogerspringblog.models;
import javax.persistence.*;



import javax.persistence.*;

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

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

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

   public Post(String title, String description) {

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

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   @Override
   public String toString() {
      return "Post{" +
              "id=" + id +
              ", title='" + title + '\'' +
              ", description='" + description + '\'' +
              '}';
   }
}

