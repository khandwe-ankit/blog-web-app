package com.ankit.blog.payloads;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private Long id;
	private String content;
	private Date date;
	private Timestamp lastUpdatedOn;
	private UserDto user;
}
