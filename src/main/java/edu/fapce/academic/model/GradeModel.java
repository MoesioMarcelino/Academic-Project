package edu.fapce.academic.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="grade_student_discipline")
public class GradeModel {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	private Double test1 = Double.NaN;
	private Double test2 = Double.NaN;
	private Double average = Double.NaN;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_student", nullable=false)
	private StudentModel id_student;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_discipline", nullable=false)
	private DisciplineModel id_discipline;
}
