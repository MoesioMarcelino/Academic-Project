package edu.fapce.academic.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class NavController {
	
	@RequestMapping("/")
	public String index() {
		return "redirect:" + "/";
	}
	
	@RequestMapping("student")
	public String student() {
		return "redirect:" + "student";
	}
	
	@RequestMapping("teacher")
	public String teacher() {
		return "redirect:" + "teacher";
	}
		
	@RequestMapping("discipline")
	public String discipline() {
		return "redirect:" + "discipline";
	}
	
	@RequestMapping("grade")
	public String grade() {
		return "redirect:" + "grade";
	}
	
	//enroll student on discipline
	@RequestMapping("studentDiscipline")
	public String studentDiscipline() {
		return "redirect:" + "studentDiscipline";
	}
}
