package com.student.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.sms.entity.UserLog;


@Repository
public interface UserRepo extends JpaRepository<UserLog, String>{

}