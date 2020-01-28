package com.codeup.rogerspringblog.models;
import javax.persistence.*;

//CREATE TABLE `dogs` (
//        `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
//        `age` tinyint(3) unsigned NOT NULL,
//        `name` varchar(200) NOT NULL,
//        `reside_state` char(2) DEFAULT 'XX',
//        PRIMARY KEY (`id`),
//        UNIQUE KEY `UK_?????????????????` (`age`)
//        ) ENGINE=MyISAM DEFAULT CHARSET=utf8;

@Entity
@Table(name = "dogs")
public class Dog {
    @Id //sets as Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //close to autoincrement for PK
    @Column(nullable = false, columnDefinition = ("INT(11) unsigned"))
    private long id;

    @Column(nullable = false, unique = true, columnDefinition = ("TINYINT(3) UNSIGNED"))
    private int age;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(columnDefinition = ("char(2) DEFAULT 'XX'"))
    private String resideState;

    public Dog() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResideState() {
        return resideState;
    }

    public void setResideState(String resideState) {
        this.resideState = resideState;
    }
}
