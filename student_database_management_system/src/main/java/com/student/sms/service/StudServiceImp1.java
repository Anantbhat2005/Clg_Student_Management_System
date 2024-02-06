package com.student.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.student.sms.entity.StudDet;
import com.student.sms.repository.StudRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class StudServiceImp1 implements StudService
{

	@Autowired
	private StudRepository studRepo;
	
	@Override
	public StudDet saveStud(StudDet stud) {
		// TODO Auto-generated method stub
		StudDet newStud=studRepo.save(stud);
		return newStud;
	}

	@Override
	public List<StudDet> getAllStud() {
		// TODO Auto-generated method stub
		return studRepo.findAll();
	}

	@Override
	public StudDet getStudById(int id) {
		// TODO Auto-generated method stub
		return studRepo.findById(id).get();
	}

	@Override
	public boolean deleteStud(int id) {
		// TODO Auto-generated method stub
		StudDet stud=studRepo.findById(id).get();
		if(stud!=null)
		{
			studRepo.delete(stud);
			return true;
		}
		return false;
	}
	
	public void removeSessionMessage()
	{
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}
	

}
