package com.student.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.student.sms.entity.StudDet;
import com.student.sms.entity.User;
import com.student.sms.service.StudService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class StudController 
{
	
	@Autowired
	private StudService studService;
	private Object id;
	
	
	@GetMapping("/register")
	public String register(Model model, HttpSession session) {
		model.addAttribute("user", new User());
		model.addAttribute("succMsg", session.getAttribute("succMsg"));
		model.addAttribute("errorMsg", session.getAttribute("errorMsg"));
		session.removeAttribute("succMsg");
		session.removeAttribute("errorMsg");
		return "register";
	}
	

	@PostMapping("/createUser")
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model  model, HttpSession session) {
		
		if (result.hasErrors()) {
			System.out.println(result);
			model.addAttribute("user", user);
			return "register";
		}

		boolean emailExist = studService.checkEmail(user.getEmail());

		if (emailExist) {
			session.setAttribute("errorMsg", "Email id already exists");
			model.addAttribute("user", user);


		} else {

			if (!user.getPassword().equals(user.getConfirmPassword())) {
				session.setAttribute("errorMsg", "Passwords do not match. Please try again.");
				model.addAttribute("user", user);
				
			}

			else {
			
				User userDtls = studService.createUser(user);

				if (userDtls != null) {
					session.setAttribute("succMsg", "Registration successful");
					System.out.println();
					return "login";
					
				} else {
					session.setAttribute("errorMsg", "Something went wrong");
				}
			}
		}
		return "redirect:/register";

	}
	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		model.addAttribute("user", new User());
		model.addAttribute("succMsg", session.getAttribute("succMsg"));
		model.addAttribute("errorMsg", session.getAttribute("errorMsg"));
		session.removeAttribute("succMsg");
		session.removeAttribute("errorMsg");
		
		return "login";
	}
	
	
	@PostMapping("/authenticateUser")
	public String authenticateUser(@Valid @ModelAttribute("user") User user, BindingResult bresult, Model  model, HttpSession session) {
			
		 
		 if(!studService.checkEmail(user.getEmail())) {
			 bresult.rejectValue("email","","Username is not existed");
		 }
		 else if(!studService.ValidatePassword(user)){
			 bresult.rejectValue("password","","Invalid password");
			
		 }
		 else {
			 session.setAttribute("succMsg", "Login successfully");
			 return "home";
		 }
		 session.removeAttribute("succMsg");
	return "login";
		
	}
	

	
	

	@GetMapping("/home")
	public String homePage()
	{
		return "home";
	}
	
	@GetMapping("/aboutus")
	public String about()
	{
		return "aboutus";
	}
	
	@GetMapping("/editStud")
	public String index(Model m,String keyword)
	{
		
		if(keyword!=null)
		{
			m.addAttribute("studList",studService.findByKeyword(keyword));
		}
		else
		{
			
		List<StudDet> list=studService.getAllStud();
		m.addAttribute("studList",list);
		}
		return "index";
	}
	
	
	
	@GetMapping("/loadStudSave")
	public String loadStudSave()
	{
		return "stud_save";
	}
	
	@GetMapping("/editStud/{id}")
	public String EditStud(@PathVariable int id,Model m)
	{
		StudDet stud= studService.getStudById(id);
		m.addAttribute("stud", stud);
		return "edit_stud";
	}
	
	
	
	@PostMapping("/saveStud")
	public String saveStud(@ModelAttribute StudDet stud,HttpSession session)
	{
		StudDet newStud=studService.saveStud(stud);
		if(newStud!=null)
		{
			session.setAttribute("msg","Details Added Successfully");
		}
		else
		{
			session.setAttribute("msg","Something went wrong");
		}
		return "redirect:/loadStudSave";
	}
	
	@PostMapping("/updateStudDtls")
	public String updateStud(@ModelAttribute StudDet stud,HttpSession session)
	{
		StudDet updateStud=studService.saveStud(stud);
		if(updateStud!=null)
		{
			session.setAttribute("msg","Details Updated Successfully");
		}
		else
		{
			session.setAttribute("msg","Something went wrong");
		}
		return "redirect:/editStud";
	}
	
	
	@GetMapping("/deleteStud/{id}")
	public String loadStudSave(@PathVariable int id,HttpSession session)
	{
		boolean f = studService.deleteStud(id);
		if(f)
		{
			session.setAttribute("msg","Details Deleted Successfully");
		}
		else
		{
			session.setAttribute("msg","Something went wrong");
		}
		 
		return "redirect:/editStud";
	}
	
	@GetMapping("/contact")
	public String contactUs()
	{
		return "contact";
	}
	
	@GetMapping("/userprof")
	public String userprof() {
		return "userprof";
	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		model.addAttribute("user", new User());
		model.addAttribute("succMsg", session.getAttribute("succMsg"));
		model.addAttribute("errorMsg", session.getAttribute("errorMsg"));
		session.removeAttribute("succMsg");
		session.removeAttribute("errorMsg");
		
		return "logout";
	}
	
	@PostMapping("/logoutUser")
	public String logoutUser(@Valid @ModelAttribute("user") User user, BindingResult bresult, Model  model, HttpSession session) {
				 
				 if(!studService.checkEmail(user.getEmail())) {
					 bresult.rejectValue("email","","Username is not existed");

				 }
				 else if(!studService.ValidatePassword(user)){
					 bresult.rejectValue("password","","Invalid password");	
				 }
				 else {
					 session.setAttribute("succMsg", "Logout successfully");
					 return "login";
				 }
			
			return "logout";
		
	}


	

}
