package edu.fapce.academic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fapce.academic.model.DisciplineModel;
import edu.fapce.academic.model.StudentModel;
import edu.fapce.academic.model.TeacherModel;
import edu.fapce.academic.repository.DisciplineRepository;
import edu.fapce.academic.repository.StudentRepository;
import edu.fapce.academic.repository.TeacherRepository;
import edu.fapce.academic.service.GradeService;

@Controller
public class StudentDisciplineController {

	@Autowired
	DisciplineRepository service;

	@Autowired
	TeacherRepository TeacherService;

	@Autowired
	StudentRepository StudentService;
	
	
	@Autowired
	GradeService gradeService;

	@RequestMapping("studentDiscipline")
	public void studentDiscipline(Model model, Model model1) {
		Iterable<DisciplineModel> disciplines = service.findAll();
		model.addAttribute("disciplines", disciplines);
		
		Iterable<TeacherModel> teachers = TeacherService.findAll();
		model1.addAttribute("teachers", teachers);
			
		Iterable<StudentModel> students = StudentService.findAll();
		model1.addAttribute("students", students);
	}


	@RequestMapping(value = "enrollStudentDiscipline", method = RequestMethod.POST)
	public String enrollStudentDiscipline(@RequestParam("idStudent") Long idStudent, 
			@RequestParam("idDiscipline") Long idDiscipline) {
		
		StudentModel Student;
		Student = StudentService.findById(idStudent).get();
		
		DisciplineModel discipline;
		discipline = service.findById(idDiscipline).get();
		
		List<StudentModel> Students = discipline.getStudentsOnDiscipline();
		Students.add(Student);
		discipline.setStudentsOnDiscipline(Students);
		service.save(discipline);
		
		gradeService.saveGrade(idStudent, idDiscipline);
		
		return "redirect:" + "studentDiscipline";
	}
	
}