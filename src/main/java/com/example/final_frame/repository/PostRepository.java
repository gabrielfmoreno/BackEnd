package com.example.final_frame.repository;

import com.example.final_frame.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> { }
