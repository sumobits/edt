package com.sumobits.edu.tracker.service;

import java.util.Set;

import com.sumobits.edu.tracker.model.Campus;
import com.sumobits.edu.tracker.model.Student;

public interface StudentService
{
	public Student save(Student student);
	
	public boolean delete(Student student);
	
	public int deleteAll();
	
	public Student findById(long id);
	
	public Set<Student> findAll();
	
	public Set<Student> findByCampus(Campus campus);
	
	public Student findByStudentId(String studentId);
	
	

}
