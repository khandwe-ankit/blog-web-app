package com.ankit.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.blog.payloads.ApiResponse;
import com.ankit.blog.payloads.PostDto;
import com.ankit.blog.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("/")
	public ResponseEntity<PostDto> createPost(
	        @RequestBody PostDto postDto,
	        @RequestParam Long userId,
	        @RequestParam Long categoryId) {
		return new ResponseEntity<>(this.postService.createPost(postDto, userId, categoryId),
		        HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<PostDto>> getAllPost() {
		return new ResponseEntity<>(this.postService.getAllPosts(), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<PostDto> getPostById(@RequestParam Long postId) {
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<>(postById, HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<List<PostDto>> getpostByUserId(@RequestParam Long userId) {
		return new ResponseEntity<>(this.postService.getPostByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/category")
	public ResponseEntity<List<PostDto>> getpostByCategoryId(@RequestParam Long categoryId) {
		return new ResponseEntity<>(this.postService.getPostByCategoryId(categoryId), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<PostDto> updatePostByid(@RequestBody PostDto postDto, @RequestParam Long postId) {
		return new ResponseEntity<>(this.postService.updatePost(postDto, postId), HttpStatus.OK);
	}

	@DeleteMapping("")
	public ResponseEntity<ApiResponse> deletepostById(@RequestParam Long postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<>(new ApiResponse("Post deleted successfully ", true),
		        HttpStatus.OK);
	}
}
