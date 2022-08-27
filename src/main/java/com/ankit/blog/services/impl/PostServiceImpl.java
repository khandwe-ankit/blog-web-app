package com.ankit.blog.services.impl;

import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ankit.blog.entitys.Category;
import com.ankit.blog.entitys.Post;
import com.ankit.blog.entitys.User;
import com.ankit.blog.payloads.PostDto;
import com.ankit.blog.repositories.PostRepo;
import com.ankit.blog.services.CategoryService;
import com.ankit.blog.services.PostService;
import com.ankit.blog.services.UserService;

public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;

	@Override
	public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
		User userById = this.userService.userDto2User(this.userService.getUserById(userId));
		Category categoryById = this.categoryService.categoryDto2Category(this.categoryService.getCategoryById(
		        categoryId));
		Post post = this.postDto2Post(postDto);
		post.setImageName("default" + new Random().nextInt(100000) + ".png");
		post.setUser(userById);
		post.setCategory(categoryById);
		return this.post2PostDto(this.postRepo.save(post));

	}

	@Override
	public PostDto getPostById(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> getPostByCategoryId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> searchPostByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto post2PostDto(Post post) {
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public Post postDto2Post(PostDto postDto) {
		return this.modelMapper.map(postDto, Post.class);
	}

}
