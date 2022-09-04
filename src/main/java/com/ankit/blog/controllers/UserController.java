package com.ankit.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.blog.payloads.ApiResponse;
import com.ankit.blog.payloads.UserDto;
import com.ankit.blog.payloads.UserDtoWithPassword;
import com.ankit.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUser() {
		List<UserDto> allUsers = this.userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
		UserDto userById = this.userService.getUserById(id);
		return new ResponseEntity<>(userById, HttpStatus.FOUND);
	}

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDtoWithPassword userDto) {
		UserDto createdUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(
	        @PathVariable(name = "id") Long id,
	        @Valid @RequestBody UserDtoWithPassword userDto) {
		UserDto updatedUser = this.userService.updateUser(userDto, id);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable(name = "id") Long id) {
		this.userService.deleteUserById(id);
		return new ResponseEntity<>(new ApiResponse("User deleted successfully", true),
		        HttpStatus.OK);
	}
}
