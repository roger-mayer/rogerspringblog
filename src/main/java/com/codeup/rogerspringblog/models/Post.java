package com.codeup.rogerspringblog.models;
import javax.persistence.*;


@Entity
@Table(name = "post")
public class Post {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id = 0;

   @Column(nullable = false, unique = true)
   private String title;

   @Column(nullable = false)
   private String body;
   public Post() {
   }
   public Post(String title, String body){
      this.title = title;
      this.body = body;
   }
   public Post(long id, String title, String body){
      this.id = id;
      this.title = title;
      this.body = body;
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

   public String getBody() {
      return body;
   }

   public void setBody(String body) {
      this.body = body;
   }

   @Override
   public String toString() {
      return "Post{" +
              "id=" + id +
              ", title='" + title + '\'' +
              ", description='" + body + '\'' +
              '}';
   }
}