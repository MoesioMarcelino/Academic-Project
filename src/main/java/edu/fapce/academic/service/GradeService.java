package edu.fapce.academic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fapce.academic.model.DisciplineModel;
import edu.fapce.academic.model.GradeModel;
import edu.fapce.academic.model.StudentModel;
import edu.fapce.academic.repository.DisciplineRepository;
import edu.fapce.academic.repository.GradeRepository;
import edu.fapce.academic.repository.StudentRepository;

@Service
public class GradeService {

	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	DisciplineRepository disciplineService;

	@Autowired
	StudentRepository studentService;
	
	
	public String saveGrade(Long idStudent, Long idDiscipline) {
		StudentModel student = studentService.findById(idStudent).get();
		DisciplineModel discipline = disciplineService.findById(idDiscipline).get();
		GradeModel newGrade = new GradeModel();
		newGrade.setId_student(student);
		newGrade.setId_discipline(discipline);
		newGrade.setTest1(0.0);
		newGrade.setTest2(0.0);
		newGrade.setAverage(0.0);
		gradeRepository.save(newGrade);
		return "studentDiscipline";
	}
	
	public List<GradeModel> findAllByIdDiscipline(Long id_Discipline){
		
		List<GradeModel> list = new ArrayList<>();
		List<GradeModel> listDiscipline = gradeRepository.findAll();
		
		for (GradeModel gradeModel : listDiscipline) {
			if (gradeModel.getId_discipline().getId() == id_Discipline) {
				list.add(gradeModel);
			}
		}
		
		return list;
	}
	
public List<GradeModel> findAllByIdStudent(Long id_Student){
		
		List<GradeModel> list = new ArrayList<>();
		List<GradeModel> listStudent = gradeRepository.findAll();
		
		for (GradeModel gradeModel : listStudent) {
			if (gradeModel.getId_student().getId() == id_Student) {
				list.add(gradeModel);
			}
		}
		
		return list;
	}
}

