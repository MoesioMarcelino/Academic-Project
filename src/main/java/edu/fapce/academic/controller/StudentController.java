package edu.fapce.academic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fapce.academic.model.StudentModel;
import edu.fapce.academic.repository.StudentRepository;

@Controller
public class StudentController{

	@Autowired
	private StudentRepository service;

	@RequestMapping("student")
	public void students(Model model) {

		Iterable<StudentModel> students = service.findAll();
		model.addAttribute("students", students);		
	}

	@RequestMapping(value = "saveStudent", method = RequestMethod.POST)
	public String saveStudent(@RequestParam("name") String nome, @RequestParam("cpf") String cpf, @RequestParam("email") String email) {

		StudentModel newStudent = new StudentModel();
		newStudent.setName(nome);
		newStudent.setCpf(cpf);
		newStudent.setEmail(email);
		service.save(newStudent);

		return "redirect:" + "student";

	}

	@RequestMapping(value = "deleteStudent", method = RequestMethod.POST)
	public String deleteStudent(@RequestParam("id") Long id) {
		service.deleteById(id);
		return "redirect:" + "student";
	}

	@RequestMapping(value = "editStudent", method = RequestMethod.POST)
	public String editStudent(@RequestParam("id") Long id, @RequestParam("name") String name,
			@RequestParam("cpf") String cpf, @RequestParam("email") String email) {
		
		StudentModel student = service.findById(id).get();
		student.setName(name);
		student.setCpf(cpf);
		student.setEmail(email);
		service.save(student);

		return "redirect:" + "student";
	}

}
