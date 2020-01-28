package com.codeup.rogerspringblog.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ad {

   private String title;
   private String Description;
   private Long id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
