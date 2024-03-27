package com.student.sms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.student.sms.entity.StudDet;
import com.student.sms.entity.User;
import com.student.sms.repository.StudRepository;
import com.student.sms.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class StudServiceImp1 implements StudService
{
	@Autowired
	private StudRepository studRepo;
	
	@Autowired
	private UserRepository userRepo;
	
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
	
	@Override
	public User createUser(User user) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryptdPwd = bcrypt.encode(user.getPassword());
		String encryptConfirmPwd = bcrypt.encode(user.getConfirmPassword());
		user.setPassword(encryptdPwd);
		user.setConfirmPassword(encryptConfirmPwd);
	
		return userRepo.save(user); 
	}

	@Override
	public boolean checkEmail(String email) {
		
		return userRepo.existsByEmail(email);
	}
	
	public void removeSessionMessage()
	{
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}

	



	@Override
	public List<StudDet> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return studRepo.findByKeyword(keyword);
	}

	@Override
	public boolean ValidatePassword(User user) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		Optional<User> opUser = userRepo.findByEmail(user.getEmail());
		if(opUser.isPresent()) {
			User dbUser = opUser.get();
			if(bcrypt.matches(user.getPassword(), dbUser.getPassword())) {
		
				return true;
			}
			else {
				return false;
			}
		}
	return false;
	}

	



	

}
