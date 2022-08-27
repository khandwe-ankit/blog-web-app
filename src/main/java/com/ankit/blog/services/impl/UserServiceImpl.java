package com.ankit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.blog.entitys.User;
import com.ankit.blog.exceptions.ResourceNotFoundException;
import com.ankit.blog.payloads.UserDto;
import com.ankit.blog.repositories.UserRepo;
import com.ankit.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.userDto2User(userDto);
		User savedUser = this.userRepo.save(user);
		return this.user2UserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", id));
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());

		return this.user2UserDto(this.userRepo.save(user));
	}

	@Override
	public UserDto getUserById(Long id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "userId", id));
		return this.user2UserDto(this.userRepo.save(user));
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		return users.stream()
		        .map(this::user2UserDto)
		        .collect(Collectors.toList());

	}

	@Override
	public void deleteUserById(Long id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "userId", id));

		this.userRepo.delete(user);

	}

	public UserDto user2UserDto(User user) {
		return this.modelMapper.map(user, UserDto.class);

		/*
		 * UserDto userDto = new UserDto(); userDto.setId(user.getId());
		 * userDto.setAbout(user.getAbout()); userDto.setEmail(user.getEmail());
		 * userDto.setName(user.getName()); userDto.setPassword(user.getPassword());
		 * return userDto;
		 */
	}

	public User userDto2User(UserDto userDto) {
		return this.modelMapper.map(userDto, User.class);
	}
}
