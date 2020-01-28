package com.codeup.rogerspringblog.models;
import javax.persistence.*;

@Entity
//@Table(name = "products")
public class Product {
    @Id //sets as Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //close to autoincrement for PK
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String description;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
