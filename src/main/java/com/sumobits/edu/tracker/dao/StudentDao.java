package com.sumobits.edu.tracker.dao;

import java.util.Set;

import com.sumobits.edu.tracker.model.Campus;
import com.sumobits.edu.tracker.model.Student;

public interface StudentDao extends GenericDao<Student>
{

	public Student findById(long id);
	
	public Set<Student> findAll();
	
	public Set<Student> findByCampus(Campus campus);
	
	public Student findByStudentId(String studentId);
	
}
