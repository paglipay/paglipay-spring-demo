package com.example.demo.web.dto;

import java.util.Objects;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	
	@Size(min=4, max=20)
	private String username;
	
	@Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
			message="Passwords must have a minimum of 8 characters, one number, one letter and one special character")
	private String password;

	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public UserRequest(String firstName, String lastName, String email, String username, String password) {
	public UserRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRequest [username=" + username + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRequest other = (UserRequest) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
	

}

