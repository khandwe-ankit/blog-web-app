package com.ankit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.blog.entitys.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
