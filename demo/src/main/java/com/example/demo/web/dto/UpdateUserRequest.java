package com.example.demo.web.dto;

public class UpdateUserRequest {

	private String userId;
	
//	private String firstName;
//	private String lastName;
//	private String email;
	private String password;
	public UpdateUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateUserRequest(String userId, String password) {
		super();
		this.userId = userId;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
		this.password = password;
	}
	public UpdateUserRequest(String password) {
		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UpdateUserRequest [userId=" + userId + ", password=" + password + "]";
	}



	
}
