package com.codeup.rogerspringblog.models;
import javax.persistence.*;


@Entity
@Table(name = "post")
public class Post {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(columnDefinition = "int(11) UNSIGNED")
   private long id;

   @Column(nullable = false, length = 50)
   private String title;
   @Column(nullable = false, length = 250)
   private String body;

   public Post(){}

   public Post(long id, String title, String body){
      this.id = id;
      this.title = title;
      this.body = body;
   }

   public String getTitle() {
      return title;
   }
   public String getBody() {
      return body;
   }

   public void setTitle(String title) {
      this.title = title;
   }
   public void setBody(String body) {
      this.body = body;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }
}
