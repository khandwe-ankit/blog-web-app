package com.ankit.blog.services;

import java.util.List;

import com.ankit.blog.entitys.User;
import com.ankit.blog.payloads.UserDto;
import com.ankit.blog.payloads.UserDtoWithPassword;

public interface UserService {

	UserDto createUser(UserDtoWithPassword user);

	UserDto updateUser(UserDtoWithPassword user, Long id);

	UserDto getUserById(Long id);

	List<UserDto> getAllUsers();

	void deleteUserById(Long id);

	User userDto2User(UserDto userDto);

	UserDto user2UserDto(User user);

}
