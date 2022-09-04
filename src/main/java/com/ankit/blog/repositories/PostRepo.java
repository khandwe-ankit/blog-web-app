package com.ankit.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.blog.entitys.Category;
import com.ankit.blog.entitys.Post;
import com.ankit.blog.entitys.User;

public interface PostRepo extends JpaRepository<Post, Long> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String postTitle);

}
