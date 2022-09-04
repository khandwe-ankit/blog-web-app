package com.ankit.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ankit.blog.config.AppConstant;
import com.ankit.blog.payloads.ApiResponse;
import com.ankit.blog.payloads.PostDto;
import com.ankit.blog.payloads.PostResponse;
import com.ankit.blog.services.FileService;
import com.ankit.blog.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	PostService postService;

	@Value("${project.filepath.images}")
	String projectImagePath;

	@Autowired
	private FileService fileService;

	@PostMapping("/")
	public ResponseEntity<PostDto> createPost(
	        @RequestBody PostDto postDto,
	        @RequestParam Long userId,
	        @RequestParam Long categoryId) {
		return new ResponseEntity<>(this.postService.createPost(postDto, userId, categoryId),
		        HttpStatus.CREATED);
	}

	@PostMapping("/image")
	public ResponseEntity<PostDto> uploadFileToFolder(
	        @RequestParam(value = "image") MultipartFile uploadedImage,
	        @RequestParam(value = "postId") Long postId) throws IOException {
		PostDto postById = this.postService.getPostById(postId);
		String imageName = postById.getImageName();
		if (imageName != null && !imageName.isBlank())
			this.fileService.deleteFile(projectImagePath, imageName);
		String uploadImageName = this.fileService.uploadFile(projectImagePath, uploadedImage);
		postById.setImageName(uploadImageName);
		PostDto updatedPost = this.postService.updatePost(postById, postId);
		return new ResponseEntity<>(updatedPost, HttpStatus.ACCEPTED);

	}

	@GetMapping(value = "/image/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		InputStream inputStream = this.fileService.getResources(projectImagePath, fileName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
		inputStream.close();

	}

	@GetMapping("/page")
	public ResponseEntity<PostResponse> getAllPost(
	        @RequestParam(value = "pno", defaultValue = AppConstant.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
	        @RequestParam(value = "ps", defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
	        @RequestParam(value = "sort", defaultValue = AppConstant.DEFAULT_PAGE_SORT_DIR, required = false) String sort,
	        @RequestParam(value = "sortBy", defaultValue = AppConstant.DEFAULT_PAGE_POST_SORT_BY, required = false) String sortBy) {
		return new ResponseEntity<>(this.postService.getAllPosts(pageNumber - 1, pageSize, sort, sortBy),
		        HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<PostDto> getPostById(@RequestParam(value = "id") Long postId) {
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<>(postById, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@RequestParam(value = "title") String postTitle) {
		List<PostDto> postRes = this.postService.searchPostByPostTitle(postTitle);
		return new ResponseEntity<>(postRes, HttpStatus.OK);
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
