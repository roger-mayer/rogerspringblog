package com.codeup.rogerspringblog.repositories;



import com.codeup.rogerspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    Post findById(long id);
    Post findByTitle(String title);
}
