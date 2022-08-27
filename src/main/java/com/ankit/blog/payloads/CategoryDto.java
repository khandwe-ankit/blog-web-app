package com.ankit.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

	private Long id;

	@NotBlank
	@Size(min = 4, max = 30)
	private String title;

	private String description;
}
