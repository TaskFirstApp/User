package com.users.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.UserRepository;
import com.users.entity.User;

@RestController
@RequestMapping(path="/users")
public class UserController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository; 
	
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody(required = true) User user) {
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("APP_USER"));
		User userSaved = userRepository.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccessControlAllowOrigin("*");
		ResponseEntity<User> responseEntity = new ResponseEntity<>(userSaved, headers, HttpStatus.CREATED);
		return responseEntity;
	}
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> findAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccessControlAllowOrigin("*");
		ResponseEntity<List<User>> responseEntity = new ResponseEntity<List<User>>(userRepository.findAll(), headers,
				HttpStatus.OK);
		return responseEntity;
	}
	
	@PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> searchUsers(@RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccessControlAllowOrigin("*");
		ResponseEntity<User> responseEntity = new ResponseEntity<User>(userRepository.findByUsername(user.getUsername()), headers,
				HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccessControlAllowOrigin("*");
		ResponseEntity<User> responseEntity = new ResponseEntity<User>(userRepository.findByUsername(username), headers,
				HttpStatus.OK);
		return responseEntity;
	}
}
