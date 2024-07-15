package com.ProjectSpringB.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ProjectSpringB.course.entities.User;
import com.ProjectSpringB.course.repositories.UserRepository;
import com.ProjectSpringB.course.services.exceptions.DatabaseException;
import com.ProjectSpringB.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}//findAll
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}//findById
	
	public User insert(User obj) {
		return repository.save(obj);
	}//insert
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}//try
		catch(EmptyResultDataAccessException e){
			throw new ResourceNotFoundException(id);
		}//catch
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}//catch
	}//delete
	
	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id);
		updateData(entity,obj);
		return repository.save(entity);
	}//update

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}//updateData
	
	
}//UserService
