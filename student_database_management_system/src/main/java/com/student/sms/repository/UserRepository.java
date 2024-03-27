package com.student.sms.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.sms.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	public boolean existsByEmail(String email);
	
	public Optional<User> findByEmail(String email);
}
