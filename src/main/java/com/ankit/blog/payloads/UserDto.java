package com.ankit.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ankit.blog.config.ConstantsAndLiterals;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Long id;

	@NotBlank
	@Size(min = ConstantsAndLiterals.USER_NAME_MIN_LENGTH, message = ConstantsAndLiterals.USER_NAME_VALIDATION_FAILED_MSG)
	private String name;

	@Email(message = ConstantsAndLiterals.USER_EMAIL_VALIDATION_FAILED_MSG)
	private String email;

	@NotBlank
//	@Size(min = ConstantsAndLiterals.PASSWORD_MIN_LENGTH, max = ConstantsAndLiterals.PASSWORD_MAX_LENGTH)
	@Pattern(regexp = ConstantsAndLiterals.PASSWORD_REGEX, message = ConstantsAndLiterals.USER_PASSWORD_VALIDATION_FAILED_MSG)
	private String password;

	@NotBlank
	private String about;

}
