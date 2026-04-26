package com.springboot.simple_project.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.simple_project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
