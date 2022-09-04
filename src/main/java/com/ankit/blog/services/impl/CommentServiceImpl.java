package com.ankit.blog.services.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.blog.entitys.Comment;
import com.ankit.blog.entitys.Post;
import com.ankit.blog.entitys.User;
import com.ankit.blog.exceptions.ResourceNotFoundException;
import com.ankit.blog.payloads.CommentDto;
import com.ankit.blog.repositories.CommentRepo;
import com.ankit.blog.services.CommentService;
import com.ankit.blog.services.PostService;
import com.ankit.blog.services.UserService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepo commentRepo;
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CommentDto addComment(CommentDto commentDto, Long postId, Long userId) {
		Post post = this.postService.postDto2Post(this.postService.getPostById(postId));
		User user = this.userService.userDto2User(this.userService.getUserById(userId));
		Comment comment = this.commentDto2Comment(commentDto);
		comment.setPost(post);
		comment.setUser(user);
		comment.setDate(new Date());
		return this.comment2CommentDto(this.commentRepo.save(comment));
	}

	@Override
	public void deleteComment(Long commentId) {
		Comment comment = this.commentRepo.findById(commentId)
		        .orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment id", commentId));
		this.commentRepo.delete(comment);
	}

	public CommentDto comment2CommentDto(Comment comment) {
		return this.modelMapper.map(comment, CommentDto.class);
	}

	public Comment commentDto2Comment(CommentDto commentDto) {
		return this.modelMapper.map(commentDto, Comment.class);
	}
}
