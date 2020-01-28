package com.codeup.rogerspringblog.models;

public class Post {
   private String title;
   private String body;
   private long id;

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
