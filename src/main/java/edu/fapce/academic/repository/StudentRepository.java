package edu.fapce.academic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fapce.academic.model.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {
	
}
