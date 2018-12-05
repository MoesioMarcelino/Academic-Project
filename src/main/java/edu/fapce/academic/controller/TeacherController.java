package edu.fapce.academic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fapce.academic.model.DisciplineModel;
import edu.fapce.academic.model.TeacherModel;
import edu.fapce.academic.repository.DisciplineRepository;
import edu.fapce.academic.repository.TeacherRepository;

@Controller

public class TeacherController{
	
	@Autowired
	TeacherRepository service;
	
	@Autowired
	DisciplineRepository disciplineService;

	@RequestMapping("teacher")
	public void ShowTeachers(Model model) {

		Iterable<TeacherModel> teachers = service.findAll();
		model.addAttribute("teachers", teachers);
	}

	@RequestMapping(value = "saveTeacher", method = RequestMethod.POST)
	public String saveTeacer(@RequestParam("name") String name,
			@RequestParam("cpf") String cpf, @RequestParam("email") String email){

		TeacherModel newTeacher = new TeacherModel();
		newTeacher.setName(name);
		newTeacher.setCpf(cpf);
		newTeacher.setEmail(email);
		service.save(newTeacher);
		return "redirect:" + "teacher";
	}

	@RequestMapping(value = "deleteTeacher", method = RequestMethod.POST)
	public String deleteTeacher(@RequestParam("id") Long id) {
		service.deleteById(id);
		return "redirect:" + "teacher";
	}

	@RequestMapping(value = "editTeacher", method = RequestMethod.POST)
	public String editTeacher(@RequestParam("id") Long id, @RequestParam("name") String name,
			@RequestParam("cpf") String cpf, @RequestParam("email") String email) {
			
		TeacherModel teacher = service.findById(id).get();
		teacher.setName(name);
		teacher.setCpf(cpf);
		teacher.setEmail(email);
		service.save(teacher);
		return "redirect:" + "teacher";
	}
	
	@RequestMapping(value = "DisciplineTeacher", method = RequestMethod.POST)
	public String DisciplineTeacher(@RequestParam("idDiscipline") Long idDiscipline,
			@RequestParam("idTeacher") Long idTeacher) {
		
		DisciplineModel newDiscipline;
		newDiscipline = disciplineService.findById(idDiscipline).get();
		TeacherModel teacher = service.findById(idTeacher).get();
		List<DisciplineModel> disciplines = teacher.getDisciplines();
		disciplines.add(newDiscipline);
		teacher.setDisciplines(disciplines);
		service.save(teacher);
		return "redirect:" + "teacher";
	}	
}