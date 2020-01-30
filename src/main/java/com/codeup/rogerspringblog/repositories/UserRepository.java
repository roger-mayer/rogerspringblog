package com.codeup.rogerspringblog.repositories;

import com.codeup.rogerspringblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
}