package edu.fapce.academic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fapce.academic.model.DisciplineModel;
import edu.fapce.academic.model.TeacherModel;
import edu.fapce.academic.repository.DisciplineRepository;
import edu.fapce.academic.repository.StudentRepository;
import edu.fapce.academic.repository.TeacherRepository;
import edu.fapce.academic.service.GradeService;

@Controller
public class DisciplineController {

	@Autowired
	DisciplineRepository service;

	@Autowired
	TeacherRepository TeacherService;

	@Autowired
	StudentRepository StudentService;
	
	@Autowired
	GradeService gradeService;

	@RequestMapping("discipline")
	public void showDisciplines(Model model, Model model1) {
		Iterable<DisciplineModel> disciplines = service.findAll();
		model.addAttribute("disciplines", disciplines);
		
		Iterable<TeacherModel> teachers = TeacherService.findAll();
		model1.addAttribute("teachers", teachers);
	}

	@RequestMapping(value = "saveDiscipline", method = RequestMethod.POST)
	public String SaveDiscipline(@RequestParam("name") String name,
			@RequestParam("idTeacher") Long idTeacher) {
		
		DisciplineModel newDiscipline = new DisciplineModel();
		newDiscipline.setName(name);
		
		TeacherModel teacher;
		teacher = TeacherService.findById(idTeacher).get();
		newDiscipline.setTeacher(teacher);
		service.save(newDiscipline);
		
		return "redirect:" + "discipline";
	}

	@RequestMapping(value = "deleteDiscipline", method = RequestMethod.POST)
	public String DeleteDiscipline(@RequestParam("id") Long id) {
		service.deleteById(id);
		return "redirect:" + "discipline";
	}
	
	@RequestMapping(value = "editDiscipline", method = RequestMethod.POST)
	public String editDiscipline(@RequestParam("id") Long id, @RequestParam("name") String name) {
		DisciplineModel discipline = service.findById(id).get();
		discipline.setName(name);
		service.save(discipline);
		return "redirect:" + "discipline";
	}	
}