package com.sumobits.edu.tracker.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumobits.edu.tracker.dao.StudentDao;
import com.sumobits.edu.tracker.model.Campus;
import com.sumobits.edu.tracker.model.Student;

@Service
public class StudentServiceImpl implements StudentService
{
	private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private StudentDao studentDao;

	@Override
	public Student save(Student student)
	{
		logger.debug("Saving {}'", student);
		studentDao.save(student);
		logger.debug("Successfully persisted {}", student);
		return null;
	}

	@Override
	public boolean delete(Student student)
	{
		logger.debug("Deleting {}.", student);
		studentDao.delete(student);
		logger.debug("Successfully deleted {}", student);
		return true;
	}

	@Override
	public int deleteAll()
	{
		logger.debug("Deleting all students ....");
		int count = studentDao.deleteAll();
		logger.debug("Successfully deleted '{}' students.", count);
		return count;
	}

	@Override
	public Student findById(long id)
	{
		logger.debug("Fetching Student By ID: '{}' ....", id);
		Student student = studentDao.findById(id);
		logger.debug(null == student ? "Student with id '{}' does not exist." : "Found student with id '{}'", id);
		return student;
	}

	@Override
	public Set<Student> findAll()
	{
		logger.debug("Fetching all students ....");
		Set<Student> students = studentDao.findAll();
		logger.debug("Retrieved '{}' students", students.size());
		return students;
	}

	@Override
	public Set<Student> findByCampus(Campus campus)
	{
		logger.debug("Fetching all students for campus '{}' ....", campus.getName());
		Set<Student> students = studentDao.findAll();
		logger.debug("Retrieved '{}' students for campus '{}'", students.size(), campus.getName());
		return students;
	}

	@Override
	public Student findByStudentId(String studentId)
	{
		logger.debug("Fetching Student By Student ID: '{}' ....", studentId);
		Student student = studentDao.findByStudentId(studentId);
		logger.debug(null == student ? "Student with student id '{}' does not exist." : "Found student with student id '{}'", studentId);
		return student;
	}	

}
