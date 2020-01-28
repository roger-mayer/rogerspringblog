package com.codeup.rogerspringblog.repositories;



import com.codeup.rogerspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByOrderByTitle();

    Post findByTitle(String Title);




    @Query("select title from Post where LENGTH(title) < 10")
    List<String> getPostsOfCertainTitleLength();

}
