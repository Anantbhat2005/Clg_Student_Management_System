package com.student.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.sms.entity.StudDet;

public interface StudRepository extends JpaRepository<StudDet, Integer>{
	
	List<StudDet> findByBranch(String branch);

}
