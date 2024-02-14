package net.javaguides.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.javaguides.sms.model.StudentDetails;
import net.javaguides.sms.repository.StudentRepository;

public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public StudentDetails createUser(StudentDetails studentDetails) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryptdPwd = bcrypt.encode(studentDetails.getPassword());
		String encryptConfirmPwd = bcrypt.encode(studentDetails.getConfirmPassword());
		studentDetails.setPassword(encryptdPwd);
		studentDetails.setPassword(encryptConfirmPwd);
		return studentRepo.save(studentDetails);
	}

	@Override
	public boolean checkEmail(String email) {
		
		return studentRepo.existsByEmail(email);
	}

}
