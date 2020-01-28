package com.codeup.rogerspringblog.repositories;

import com.codeup.rogerspringblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    Ad findByTitle (String title);

//    @Query("from Ad a where a.Id like ?1")
//    Ad getAdById(long id);
}
