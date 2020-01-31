drop database rogers_spring_db;
use rogers_spring_db;




# seed post details
insert into post_details (history_of_post, is_awesome, topic_description) values
('post 1 history', true, 'topic 1'),
('post 2 history', true, 'topic 2'),
('post 3 history', true, 'topic 3'),
('post 4 history', true, 'topic 4'),
('post 5 history', true, 'topic 5');

# seed users
insert into users (username, email, password) values
('testUser1','randomemail1@email.com','password1'),
('testUser2','randomemail2@email.com','password2'),
('testUser3','randomemail3@email.com','password3'),
('testUser4','randomemail4@email.com','password4');


# seed posts
insert into posts (title, description, post_details_id, user_id) values
('post 1', 'This is the test post 1 description', 1, 1),
('post 2', 'This is the test post 2 description', 2, 1),
('post 3', 'This is the test post 3 description', 3, 1),
('post 4', 'This is the test post 4 description', 4, 1),
('post 5', 'This is the test post 4 description', 5, 1);


#seed image
INSERT INTO post_images (image_title, image_url, post_id) VALUES
('hotdog', 'https://www.golfbigfish.com/wp-content/uploads/2019/07/bigstock-Hot-Dog-5056616.jpg', 1 ),
('hotdog', 'https://www.golfbigfish.com/wp-content/uploads/2019/07/bigstock-Hot-Dog-5056616.jpg', 2 ),
('hotdog', 'https://www.golfbigfish.com/wp-content/uploads/2019/07/bigstock-Hot-Dog-5056616.jpg', 3 );

