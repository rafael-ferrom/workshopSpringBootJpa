package com.ProjectSpringB.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjectSpringB.course.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}//UserRepository
