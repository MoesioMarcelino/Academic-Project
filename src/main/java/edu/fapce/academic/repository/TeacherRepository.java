package edu.fapce.academic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fapce.academic.model.TeacherModel;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel , Long>{

}
