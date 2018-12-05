package edu.fapce.academic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fapce.academic.model.DisciplineModel;
import edu.fapce.academic.model.GradeModel;
import edu.fapce.academic.model.StudentModel;
import edu.fapce.academic.repository.DisciplineRepository;
import edu.fapce.academic.repository.GradeRepository;
import edu.fapce.academic.repository.StudentRepository;
import edu.fapce.academic.service.GradeService;

@Controller
public class GradeController {

	@Autowired
	GradeRepository service;

	@Autowired
	DisciplineRepository disciplineService;

	@Autowired
	StudentRepository studentService;
	
	@Autowired
	GradeService gradeService;

	@RequestMapping("grade")
	public void grade(Model model, Model model1) {
		Iterable<DisciplineModel> disciplines = disciplineService.findAll();
		model1.addAttribute("disciplines", disciplines);
		
		Iterable<StudentModel> students = studentService.findAll();
		model1.addAttribute("students", students);
		
		Iterable<GradeModel> grade = service.findAll();
		model.addAttribute("grade", grade);
	}

	@RequestMapping(value = "saveTest1", method = RequestMethod.POST)
	public String saveTest1(@RequestParam("idGrade") Long idGrade, @RequestParam("test1") double test1) {
		GradeModel grade = service.findById(idGrade).get();
		grade.setTest1(test1);
		service.save(grade);
		/*new EmailService().submit(grade.getId_student().getName()
				,grade.getId_student().getEmail()
				,grade.getId_discipline().getName());*/
		return "redirect:" + "grade";
	}

	@RequestMapping(value = "saveTest2", method = RequestMethod.POST)
	public String saveAV2(@RequestParam("idGrade") Long idGrade, @RequestParam("test2") double test2) {
		GradeModel grade = service.findById(idGrade).get();
		if (!(grade.getTest1() == null)) {
			grade.setTest2(test2);
			/*service.save(grade);
			new EmailService().submit(grade.getId_student().getName()
					,grade.getId_student().getEmail()
					,grade.getId_discipline().getName());*/
			return "redirect:" + "grade";
		} else {
			return "Alert('insira a nota do teste 1 primeiro!')";
		}
	}

	@RequestMapping(value = "average", method = RequestMethod.POST)
	public String average(@RequestParam("idGrade") Long idGrade) {
		GradeModel grade = service.findById(idGrade).get();
		grade.setAverage((grade.getTest1() + grade.getTest2()) / 2);
		service.save(grade);
		return "redirect:" + "grade";
	}
	
	@RequestMapping(value = "ListDisciplineGrade", method = RequestMethod.POST)
	public String ListStudentGradeDiscipline(@RequestParam ("idDiscipline")Long idDiscipline,
			Model model) {
		
		Iterable<GradeModel> gradeTeachers = gradeService.findAllByIdDiscipline(idDiscipline);
		model.addAttribute("gradeTeachers", gradeTeachers);
		
		return "redirect:" + "grade?idGrade=" + idDiscipline;
	}
	
	@RequestMapping(value = "ListDisciplineStudent", method = RequestMethod.POST)
	public String ListDisciStudent(@RequestParam("idStudent")Long idStudent, Model model) {
		
		Iterable<GradeModel> gradeStudents = gradeService.findAllByIdStudent(idStudent);
		model.addAttribute("gradeStudents", gradeStudents);
		
		return "redirect:" + "grade";
	}	
	
	
}