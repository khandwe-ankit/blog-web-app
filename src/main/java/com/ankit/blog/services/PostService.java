package com.ankit.blog.services;

import java.util.List;

import com.ankit.blog.entitys.Post;
import com.ankit.blog.payloads.PostDto;
import com.ankit.blog.payloads.PostResponse;

public interface PostService {

	// CRUD
	PostDto createPost(PostDto postDto, Long userId, Long categoryId);

	PostDto getPostById(Long id);

	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sort, String sortBy);

	PostDto updatePost(PostDto postDto, Long id);

	void deletePost(Long id);

	List<PostDto> getPostByCategoryId(Long id);

	List<PostDto> getPostByUserId(Long id);

	PostDto post2PostDto(Post post);

	Post postDto2Post(PostDto postDto);

	List<PostDto> searchPostByPostTitle(String postTitle);

}
