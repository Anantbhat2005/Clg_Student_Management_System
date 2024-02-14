package net.javaguides.sms.service;

import net.javaguides.sms.model.StudentDetails;

public interface StudentService {

		public StudentDetails createUser(StudentDetails studentDetails);
		public boolean checkEmail(String email);
}
