package net.javaguides.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.sms.model.StudentDetails;

public interface StudentRepository extends JpaRepository<StudentDetails,Integer>{
	public boolean existsByEmail(String email);
}
