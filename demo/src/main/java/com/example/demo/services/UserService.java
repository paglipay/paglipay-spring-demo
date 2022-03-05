package com.example.demo.services;

import java.util.UUID;
import java.util.function.Predicate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.UserDAO;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.ResourcePersistenceException;
import com.example.demo.models.User;
import com.example.demo.web.dto.UpdateUserRequest;
import com.example.demo.web.dto.UserRequest;

@Service
public class UserService {

private final UserDAO userDao;
	
	// DI - Dependency Injection of the DAO
	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDao = userDAO;
	}
	
	@Transactional
	public boolean registerNewUser(UserRequest userRequest) {
		
		User newUser = new User(
				userRequest.getUsername(),
				userRequest.getPassword()
				);

		boolean usernameAvailable = userDao.findUserByUsername(newUser.getUsername()).isPresent();
//		boolean emailAvailable = userDao.findUserByEmail(newUser.getEmail()).isPresent();

		if(!usernameAvailable) {
			if(usernameAvailable) {
				throw new ResourcePersistenceException("The provided email was already taken in the database");
			} 
		}
		
		newUser.setUserId(UUID.randomUUID().toString());
//		newUser.setMadnessLevel(User.MadnessLevel.SANE);
		User persistedUser = userDao.save(newUser);
		
		if(persistedUser == null) {
			throw new ResourcePersistenceException("The user could not be persisted");
		}
		
		return true;
	}
	
	@Transactional
	public void updateUser(UpdateUserRequest updateUserRequest) {
		try {
			
			User original = userDao.findById(updateUserRequest.getUserId()).orElseThrow(ResourceNotFoundException::new);
			
			Predicate<String> notNullorEmpty = str -> str != null && !str.equals("");
			
//			if(notNullorEmpty.test(updateUserRequest.getFirstName())) {
//				original.setFirstName(updateUserRequest.getFirstName());
//			} else if(notNullorEmpty.test(updateUserRequest.getLastName())) {
//				original.setLastName(updateUserRequest.getLastName());
//			} else if(notNullorEmpty.test(updateUserRequest.getEmail())) {
//				if(userDao.findUserByEmail(updateUserRequest.getEmail()).isPresent()) {
//					throw new ResourcePersistenceException("The provided email is already in use");
//				}
//				original.setEmail(updateUserRequest.getEmail());
//			} else 
				
			if(notNullorEmpty.test(updateUserRequest.getPassword())) {
				original.setPassword(updateUserRequest.getPassword());
			}
			
		} catch (ResourcePersistenceException e) {
			throw e;
		} catch (Exception e) {
			throw new ResourcePersistenceException("Could not update user to due our exception checking");
		}
	}
	
	
	@Transactional
	public boolean isUsernameAvailable(String username) {
		return userDao.findUserByUsername(username).isEmpty();
	}
}
