package com.test.repository;

import com.test.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
//    public Post findBy(Long id);
    public Post findByName(String name);
}