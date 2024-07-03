package com.ProjectSpringB.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectSpringB.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	
}//UserRepository
