package edu.fapce.academic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fapce.academic.model.DisciplineModel;

@Repository
public interface DisciplineRepository extends JpaRepository<DisciplineModel, Long>{

}
