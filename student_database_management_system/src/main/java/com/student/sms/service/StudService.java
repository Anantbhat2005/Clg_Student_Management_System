package com.student.sms.service;

import java.util.List;


import com.student.sms.entity.StudDet;
import com.student.sms.entity.User;

public interface StudService {
	public StudDet saveStud(StudDet stud);
	public List<StudDet> getAllStud();
	public StudDet getStudById(int id);
	public boolean deleteStud(int id);
	public User createUser (User user);
	public boolean checkEmail(String email);
	User findByEmail(String email);
	
	public List<StudDet> findByKeyword(String keyword);
	


}
