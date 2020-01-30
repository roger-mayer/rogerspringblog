package com.codeup.rogerspringblog.repositories;

import com.codeup.rogerspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<Post, Long> {
    Post findById(long id);
}