package com.codeup.rogerspringblog.models;

import javax.persistence.*;

@Entity
@Table(name = "post_images")
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = ("INT(11) UNSIGNED"))
    private long id;

    @Column
    private String imageTitle;

    @Column(name = "image_url", length = 1000)
    private String url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostImage(){}

    public PostImage(String url, Post post) {
        this.url = url;
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public PostImage(long id, String imageTitle, String url, Post post) {
        this.id = id;
        this.imageTitle = imageTitle;
        this.url = url;
        this.post = post;
    }

    public PostImage(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
}


//    1. In the relationship-practice branch of your Spring Blog's, add an entity of PostImage with the following fields:
//    id (typical primary key annotations)
//    image_title VARCHAR(100) NOT NULL
//    url VARCHAR(1000) NOT NULL - the url is for simple hot linking of images for now
//    post_id (a FK that will be created with the proper One-To-Many mapping)