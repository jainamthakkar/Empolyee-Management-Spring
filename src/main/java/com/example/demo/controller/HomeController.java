package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController{
	
	@Autowired
	private EmpService empService;
	
	@GetMapping("/") 
	public String index(Model m) {
		List<Employee> list = empService.getAllEmp();
		m.addAttribute("empList", list);
		return "index";
	}
	
	@GetMapping("/loadEmpSave") 
	public String loadEmpSave() {
		return "emp_save";
	}
	
	@GetMapping("/edit") 
	public String editEmp() {
		return "edit";
	}
	
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {
		/* System.out.println(emp); */
		Employee newemp = empService.saveEmp(emp);
		if(newemp != null) {
			System.out.println("success");
			session.setAttribute("msg", "Registration Successfully!!");
		}else {
			session.setAttribute("msg", "Something Went Wrong On Server!!");
		}
		return "redirect:/loadEmpSave";
	}
}