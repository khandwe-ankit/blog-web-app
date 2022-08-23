package com.ankit.blog.services;

import java.util.List;

import com.ankit.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Long id);

	UserDto getUserById(Long id);

	List<UserDto> getAllUsers();

	void deleteUserById(Long id);

}
