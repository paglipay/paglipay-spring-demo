package com.example.demo.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "users")
public class User implements Serializable {
	
	@Id
	@Column(name="user_id")
	private String userId;

	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String usertId) {
		this.userId = usertId;
	}

	@Column(unique = true, nullable = false, columnDefinition = "VARCHAR CHECK (username <> '')")
	private String username;

	@Column(unique = true, nullable = false, columnDefinition = "VARCHAR CHECK (password <> '')")
	private String password;
	
	

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

	public User(String usertId, String username, String password) {
		super();
		this.userId = usertId;
		this.username = username;
		this.password = password;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [usertId=" + userId + ", username=" + username + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, username, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username)
				&& Objects.equals(userId, other.userId);
	}
	
	

}
