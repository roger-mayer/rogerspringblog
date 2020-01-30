drop database rogers_spring_db;
show databases;
create database rogers_spring_db;
use rogers_spring_db;
#
#
#
# show tables;
# select * from ads;
#
#
# #
# # CREATE TABLE dogs (
# #                       id int(11) unsigned NOT NULL,
# #                       name varchar(200) NOT NULL,
# #                       reside_state char(2) DEFAULT 'XX',
# #                       PRIMARY KEY(id),
# #                       UNIQUE KEY UK_????????????????? (age)
# # );
#
Drop table posts;
CREATE TABLE posts(
                    posts_id int(11) unsigned NOT NULL AUTO_INCREMENT,
                    title varchar(100) NOT NULL,
                    description varchar(500) NOT NULL,
                    primary key(posts_id)
);
insert into posts (title, description) values
('post 1', 'This is the test post 1 description'),
('post 2', 'This is the test post 2 description'),
('post 3', 'This is the test post 3 description'),
('post 4', 'This is the test post 4 description');

DROP TABLE users;
CREATE TABLE users(
                      id int(11) unsigned NOT NULL AUTO_INCREMENT,
                      email varchar(100) NOT NULL,
                      password varchar(500) NOT NULL,
                      user_id BIGINT NOT NULL,
                      PRIMARY KEY (id),
                      FOREIGN KEY (user_id) REFERENCES posts(id)
);
