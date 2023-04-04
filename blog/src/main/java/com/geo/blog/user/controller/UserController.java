package com.geo.blog.user.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geo.blog.dto.ResponseDto;
import com.geo.blog.model.User;
import com.geo.blog.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

 	@PostMapping("/api/user/signUp")
	public ResponseDto<String> save(@RequestBody User user) {
		System.out.println("UserController : save 호출");
		ResponseDto<String> result = userService.signUp(user);

		return result ;

	}

 	@PostMapping("/api/user/signIn")
 	public ResponseDto<String> signIn(@RequestBody User user){
 		ResponseDto<String> result = userService.signIn(user);

 		return result;

 	}
}
