package com.sumobits.edu.tracker.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sumobits.edu.tracker.model.Campus;
import com.sumobits.edu.tracker.model.Student;

@Repository("studentDao")
public class StudentDaoImpl extends GenericDaoImpl<Student> implements StudentDao
{
	private static Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);
	
	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public int deleteAll()
	{
		Query deleteAll = entityManager.createQuery("DELETE FROM Student");
		int count = deleteAll.executeUpdate();
		
		logger.info("Successfully deleted '{}' students", count);
		return count;
	}

	@Override
	public Student findById(long id)
	{
		Query findQuery = entityManager.createQuery("FROM Student where pk=:id");
		findQuery.setParameter("id", id);
		Student student = null;

		try
		{
			student = (Student) findQuery.getSingleResult();
		}
		catch (NoResultException e)
		{
		}

		return student;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Student> findAll()
	{
		Query findQuery = entityManager.createQuery("FROM Student");
		Set<Student> students = new HashSet<Student>();
		
		try
		{
			students.addAll(findQuery.getResultList());
		}
		catch (Exception e)
		{
			logger.error("Failed to read students from database: '{}'", e.getMessage(), e);
		}

		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Student> findByCampus(Campus campus)
	{
		Query findQuery = entityManager.createQuery("FROM Student where campus_fk=:cpk");
		findQuery.setParameter("cpk", campus.getId());
		Set<Student> students = new HashSet<Student>();
		
		try
		{
			students.addAll(findQuery.getResultList());
		}
		catch (NoResultException e)
		{
		}

		return students;
	}

	@Override
	public Student findByStudentId(String studentId)
	{
		Query findQuery = entityManager.createQuery("FROM Student where studeId=:studentId");
		findQuery.setParameter("studentId", studentId);
		Student student = null;
		
		try
		{
			student = (Student) findQuery.getSingleResult();
		}
		catch (NoResultException e)
		{
		}

		return student;
	}

}
