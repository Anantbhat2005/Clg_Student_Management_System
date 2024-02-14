package net.javaguides.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import net.javaguides.sms.model.StudentDetails;
import net.javaguides.sms.service.StudentService;


@Controller
public class LoginController {
	
	@Autowired(required=false)
	private StudentService studentService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/register")
	public String register(Model model, HttpSession session) {
		model.addAttribute("studentDetails", new StudentDetails());
		model.addAttribute("msg", session.getAttribute("msg"));
		model.addAttribute("errorMsg", session.getAttribute("errorMsg"));
		session.removeAttribute("msg");
		session.removeAttribute("errorMsg");
		return "register";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@PostMapping("/createUser")
	public String createUser(@Valid @ModelAttribute("studentDetails") StudentDetails studentDetails, BindingResult result, Model  model, HttpSession session) {
		
		
		if (result.hasErrors()) {
			System.out.println(result);
			model.addAttribute("studentDetails", studentDetails);
			return "register";
		}

		boolean emailExist = studentService.checkEmail(studentDetails.getEmail());

		if (emailExist) {
			session.setAttribute("errorMsg", "Email id already exists");
			model.addAttribute("studentDetails", studentDetails);
			session.removeAttribute("msg");

		} else {

			if (!studentDetails.getPassword().equals(studentDetails.getConfirmPassword())) {
				session.setAttribute("errorMsg", "Passwords do not match. Please try again.");
				model.addAttribute("studentDetails", studentDetails);

			}

			else {
				StudentDetails usrDtls = studentService.createUser(studentDetails);
				
				if (usrDtls != null) {
					session.setAttribute("msg", "Registration successful");
					return "login";
					
				} else {
					session.setAttribute("errorMsg", "Something went wrong");
				}
			}
		}
		return "redirect:/register";

	}
}