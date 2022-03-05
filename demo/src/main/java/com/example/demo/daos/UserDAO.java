package com.example.demo.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;


@Repository
public interface UserDAO extends CrudRepository<User, String>{
	
//	Optional<User> findUserByEmail(String email);
	Optional<User> findUserByUsername(String username);
	
	@Query("from User s where s.username = :username and s.password = :password")
	User findUserByUsernameAndPassword(String username, String password);
	
}

