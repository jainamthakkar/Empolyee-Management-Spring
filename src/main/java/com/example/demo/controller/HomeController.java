package com.example.demo.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

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

	@GetMapping("/edit/{id}")
	public String editEmp(@PathVariable int id, Model m) {
		Employee emp = empService.getEmpById(id);
		m.addAttribute("emp", emp);
		return "edit"; 
	}

	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {
		Employee newemp = empService.saveEmp(emp);
		if (newemp != null) {
			System.out.println("success");
			session.setAttribute("msg", "Registration Successfully!!");
		} else {
			session.setAttribute("msg", "Something Went Wrong On Server!!");
		}
		return "redirect:/loadEmpSave";
	}

	@PostMapping("/updateEmpDtls")
	public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
		// System.out.println(emp);

		Employee updateEmp = empService.saveEmp(emp);

		if (updateEmp != null) {
			// System.out.println("save success");
			session.setAttribute("msg", "Update sucessfully");
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/";
	}

	@GetMapping("/deleteEmp/{id}")
	public String loadEmpSave(@PathVariable int id, HttpSession session) {
		boolean f = empService.deleteEmp(id);
		if (f) {
			session.setAttribute("msg", "Delete sucessfully");
		} else {
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/";
	}

}