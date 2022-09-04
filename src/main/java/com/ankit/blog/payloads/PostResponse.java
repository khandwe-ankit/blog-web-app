package com.ankit.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {

	private long totalPosts;
	private int totalPage;
	private int currentPage;
	private int pageSize;
	private boolean lastPage;
	private List<PostDto> data;

}
