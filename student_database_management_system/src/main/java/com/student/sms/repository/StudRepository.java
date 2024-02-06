package com.student.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.sms.entity.StudDet;

public interface StudRepository extends JpaRepository<StudDet, Integer>{

}
