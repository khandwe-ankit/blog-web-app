package com.ankit.blog.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String password;
	private String about;

}
