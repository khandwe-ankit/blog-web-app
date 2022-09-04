package com.ankit.blog.payloads;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

	private long id;

	@NotBlank
	@Size(max = 100)
	private String title;

	@NotBlank
	@Size(max = 1000)
	private String content;

	private String imageName;

	private Date date;

	private Timestamp lastUpdatedOn;

	private CategoryDto category;

	private UserDto user;

	private Set<CommentDto> comments;

}
