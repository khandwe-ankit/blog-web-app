package com.ankit.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ankit.blog.config.AppConstant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoWithPassword {

	private Long id;

	@NotBlank
	@Size(min = AppConstant.USER_NAME_MIN_LENGTH, message = AppConstant.USER_NAME_VALIDATION_FAILED_MSG)
	private String name;

	@Email(message = AppConstant.USER_EMAIL_VALIDATION_FAILED_MSG)
	private String email;

	@NotBlank
//	@Size(min = AppConstant.PASSWORD_MIN_LENGTH, max = AppConstant.PASSWORD_MAX_LENGTH)
	@Pattern(regexp = AppConstant.PASSWORD_REGEX, message = AppConstant.USER_PASSWORD_VALIDATION_FAILED_MSG)
	private String password;

	@NotBlank
	private String about;

}
