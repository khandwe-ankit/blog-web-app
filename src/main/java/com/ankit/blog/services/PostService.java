package com.ankit.blog.services;

import java.util.List;

import com.ankit.blog.entitys.Category;
import com.ankit.blog.entitys.Post;
import com.ankit.blog.payloads.PostDto;

public interface PostService {

	// CRUD
	PostDto createPost(PostDto postDto, Long userId, Long categoryId);

	PostDto getPostById(Long Id);

	List<PostDto> getAllPosts();

	PostDto updatePost(PostDto postDto, Long id);

	void deletePost(Long id);

	List<PostDto> getPostByCategoryId(Long id);

	List<PostDto> getPostByUserId(Long id);

	List<PostDto> searchPostByCategory(Category category);

	PostDto post2PostDto(Post post);

	Post postDto2Post(PostDto postDto);

}
