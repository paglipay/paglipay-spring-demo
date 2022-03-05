package com.example.demo.web.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.cloudfoundry.CloudFoundryAuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.AuthenticationException;
import com.example.demo.exceptions.AuthorizationException;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.web.dto.UpdateUserRequest;
import com.example.demo.web.dto.UserRequest;

@RestController
@RequestMapping("/users")
public class UserServlet {
	private final UserService userService;
	
	@Autowired
	public UserServlet(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody @Valid UserRequest userRequest) {
		userService.registerNewUser(userRequest);
	}
	
//	@GetMapping("/email")
//	public ResponseEntity<Void> checkEmail(@RequestParam String email){
//		return userService.isEmailAvailable(email) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);
//	}
	
	@GetMapping("/username")
	public ResponseEntity<Void> checkUsername(@RequestParam String username){
		return userService.isUsernameAvailable(username) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PatchMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUserInfo(@RequestBody UpdateUserRequest updateUserRequest, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			throw new AuthenticationException("No session found");
		}
		
		User requestingUser = (User) session.getAttribute("authUser");
		
		boolean requestEditSelf = requestingUser.getUserId().equals(updateUserRequest.getUserId());
		
		if(!requestEditSelf) {
			throw new AuthorizationException("forbidden request");
		}
		
		userService.updateUser(updateUserRequest);
	}
}
