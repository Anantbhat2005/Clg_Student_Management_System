package com.student.sms.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.student.sms.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	public boolean existsByEmail(String email);
	User findByEmail(String email);
}
