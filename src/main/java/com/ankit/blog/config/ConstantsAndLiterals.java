package com.ankit.blog.config;

public class ConstantsAndLiterals {

	private ConstantsAndLiterals() {

	}

	public static final int PASSWORD_MIN_LENGTH = 5;
	public static final int PASSWORD_MAX_LENGTH = 10;
	public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{"+PASSWORD_MIN_LENGTH+","+PASSWORD_MAX_LENGTH+"}$";
	public static final int USER_NAME_MIN_LENGTH = 4;
	public static final String USER_EMAIL_VALIDATION_FAILED_MSG = "Email address is not valid";
	public static final String USER_NAME_VALIDATION_FAILED_MSG = "Name must have atleast 4 characters";
	public static final String USER_PASSWORD_VALIDATION_FAILED_MSG = "Password not according to password policy. "
			+ "Password must contains at least 5, at most 10 characters"
	        + ", at least one digit"
	        + ", one upper case"
	        + ", one lower case alphabet"
	        + ", one special character which includes { !@#$%&*()-+=^ }"
	        + ", and doesn’t contain any white space. ";
	
	/*Category*/
	public static final String CATEGORY_LOWERCASE="category";
	public static final String CATEGORY_ID="category id";

}
