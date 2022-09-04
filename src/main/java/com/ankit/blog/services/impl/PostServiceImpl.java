package com.ankit.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ankit.blog.entitys.Category;
import com.ankit.blog.entitys.Post;
import com.ankit.blog.entitys.User;
import com.ankit.blog.exceptions.ResourceNotFoundException;
import com.ankit.blog.payloads.PostDto;
import com.ankit.blog.payloads.PostResponse;
import com.ankit.blog.repositories.PostRepo;
import com.ankit.blog.services.CategoryService;
import com.ankit.blog.services.PostService;
import com.ankit.blog.services.UserService;

@Service
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
		post.setUser(userById);
		post.setCategory(categoryById);
		post.setDate(new Date());
		return this.post2PostDto(this.postRepo.save(post));

	}

	@Override
	public PostDto getPostById(Long id) {
		return this.post2PostDto(this.postRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", id)));
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sort, String sortBy) {
		Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, sort.equalsIgnoreCase("desc") ? Direction.DESC
		        : Direction.ASC, sortBy);
		Page<Post> allPosts = this.postRepo.findAll(pageableRequest);
		List<Post> posts = allPosts.getContent();
		long totalPost = allPosts.getTotalElements();
		int totalPages = allPosts.getTotalPages();
		int currentPage = allPosts.getNumber();
		List<PostDto> postList = posts.stream().map(this::post2PostDto).collect(Collectors.toList());

//		Map<String, Object> map = new LinkedHashMap<>();
//		map.put("total-post", totalPost);
//		map.put("total-pages", totalPages);
//		map.put("current-page", currentPage + 1);
//		map.put("page-size", pageSize);
//		map.put("last-page", Objects.equals(totalPages, currentPage + 1));
//		map.put("data", postList);
//		return map;

		PostResponse postResponse = new PostResponse();
		postResponse.setTotalPosts(totalPost);
		postResponse.setTotalPage(totalPages);
		postResponse.setCurrentPage(currentPage);
		postResponse.setPageSize(pageSize);
		postResponse.setLastPage(totalPages == currentPage + 1);
		postResponse.setData(postList);
		return postResponse;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		Post oldPost = this.postDto2Post(this.getPostById(id));
		Post newPost = this.postDto2Post(postDto);
		oldPost.setContent(newPost.getContent());
		oldPost.setImageName(newPost.getImageName());
		oldPost.setDate(new Date());
		oldPost.setTitle(newPost.getTitle());
		return this.post2PostDto(this.postRepo.save(oldPost));

	}

	@Override
	public void deletePost(Long id) {
		this.postRepo.delete(this.postDto2Post(this.getPostById(id)));
	}

	@Override
	public List<PostDto> getPostByCategoryId(Long id) {
		Category category = this.categoryService.categoryDto2Category(
		        this.categoryService.getCategoryById(id));
		return this.postRepo.findByCategory(category)
		        .stream()
		        .map(this::post2PostDto)
		        .collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostByUserId(Long id) {
		User user = this.userService.userDto2User(
		        this.userService.getUserById(id));
		return this.postRepo
		        .findByUser(user)
		        .stream()
		        .map(this::post2PostDto)
		        .collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPostByPostTitle(String postTitle) {
		return this.postRepo.findByTitleContaining(postTitle)
		        .stream()
		        .map(this::post2PostDto)
		        .collect(Collectors.toList());
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
