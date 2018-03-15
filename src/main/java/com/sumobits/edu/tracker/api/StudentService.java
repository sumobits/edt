package com.sumobits.edu.tracker.api;

import java.util.Set;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sumobits.edu.tracker.model.Student;

@WebService
@RestController("/students")
public class StudentService
{
	private static Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private com.sumobits.edu.tracker.service.StudentService studentService = null;
	
	@GetMapping(value="/list", produces="application/json")
	public Response getStudents(HttpServletRequest request)
	{
		logger.debug("Received request for '{}' from client '{}'", request.getRequestURL(), request.getRemoteHost());
		Set<Student> students = studentService.findAll();
		logger.debug("Retrieved '{}' students from persistence.", students.size());
		return Response.status(Response.Status.OK).entity(students).build();
	}
	
	@GetMapping(value="/view", produces="application/json")
	public Response findStudent(@QueryParam("student_id") String studentId, HttpServletRequest request)
	{
		logger.debug("Received request for '{}' from client '{}'", request.getRequestURL(), request.getRemoteHost());
		Student student = studentService.findByStudentId(studentId);
		logger.debug("Retrieved '{}' student from persistence.", student);
		return Response.status(Response.Status.FOUND).entity(student).build();		
	}

	@PutMapping(value="/save", consumes="application/json", produces="application/json")
	public Response saveStudent(@RequestParam(name="Student") Student student, HttpServletRequest request)
	{
		logger.debug("Received request for '{}' from client '{}'", request.getRequestURL(), request.getRemoteHost());
		student = studentService.save(student);
		logger.debug("Updated '{}' student from persistence.", student);
		return Response.status(Response.Status.ACCEPTED).entity(student).build();
	}
	
	@DeleteMapping(value="/delete", produces="text/plain")
	public Response deleteStudent(@QueryParam("student_id")String studentId, HttpServletRequest request)
	{
		logger.debug("Received request for '{}' from client '{}'", request.getRequestURL(), request.getRemoteHost());
		Student student = studentService.findByStudentId(studentId);
		
		if(null == student)
		{
			studentService.delete(student);
			logger.debug("Deleted '{}' student from persistence.", student);
		}

		return Response.status(Response.Status.ACCEPTED).entity(student).build();
	}
}
