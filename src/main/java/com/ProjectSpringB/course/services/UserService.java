package com.ProjectSpringB.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectSpringB.course.entities.User;
import com.ProjectSpringB.course.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}//findAll
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}//findById
	
	public User insert(User obj) {
		return repository.save(obj);
	}//insert
	
}//UserService
