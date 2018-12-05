package edu.fapce.academic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fapce.academic.model.GradeModel;

@Repository
public interface GradeRepository extends JpaRepository<GradeModel, Long>{

}
