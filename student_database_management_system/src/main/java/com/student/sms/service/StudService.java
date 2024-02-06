package com.student.sms.service;

import java.util.List;

import com.student.sms.entity.StudDet;

public interface StudService {
	public StudDet saveStud(StudDet stud);
	public List<StudDet> getAllStud();
	public StudDet getStudById(int id);
	public boolean deleteStud(int id);
	


}
