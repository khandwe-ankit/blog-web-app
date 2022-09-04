package com.ankit.blog.services;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ankit.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto addComment(@RequestBody CommentDto commentDto, @RequestParam Long postId, @RequestParam Long userId);

	void deleteComment(@RequestParam(name = "id") Long commentId);
}
