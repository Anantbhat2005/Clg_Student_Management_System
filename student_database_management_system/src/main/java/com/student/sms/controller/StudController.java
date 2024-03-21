package com.student.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.student.sms.entity.StudDet;
import com.student.sms.service.StudService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudController 
{
	
	@Autowired
	private StudService studService;

	
	
	@GetMapping("/")
	public String home()
	{
		return "home";
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
	public String index(Model m)
	{
		List<StudDet> list=studService.getAllStud();
		m.addAttribute("studList",list);
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
	

}
