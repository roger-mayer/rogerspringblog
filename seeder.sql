# drop database rogers_spring_db;
# show databases;
# use rogers_spring_db;
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
# # CREATE TABLE ads(
# #                     id int(11) unsigned NOT NULL,
# #                     title varchar(100) NOT NULL,
# #                     description varchar(500) NOT NULL,
# #                     primary key(id)
# # );
#
# SELECT * FROM ads;
# truncate table ads;
#
# # seed ads
# insert into ads (title, description) values
# ('B', 'This is the test ad 1 description'),
# ('A', 'This is the test ad 2 description'),
# ('D', 'This is the test ad 3 description'),
# ('C', 'This is the test ad 4 description'),
# ('Very Long Ad 5 Title', 'This is the test ad 5 description');
#
# SELECT title FROM ads WHERE LENGTH(title) < 10