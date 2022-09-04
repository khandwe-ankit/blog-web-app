package com.ankit.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.blog.payloads.ApiResponse;
import com.ankit.blog.payloads.CommentDto;
import com.ankit.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/")
	public ResponseEntity<CommentDto> addComment(
	        @RequestBody CommentDto commentDto,
	        @RequestParam Long postId,
	        @RequestParam Long userId) {
		CommentDto addedComment = this.commentService.addComment(commentDto, postId, userId);
		return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
	}

	@DeleteMapping("")
	public ResponseEntity<ApiResponse> deleteComment(@RequestParam(name = "id") Long commentId) {
		this.commentService.deleteComment(commentId);
		ApiResponse apiResponse = new ApiResponse("Comment deleted sucessfully", true);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}
