package com.sumobits.edu.tracker.api;

import java.util.Set;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sumobits.edu.tracker.model.Student;

@WebService
@RestController("/students")
public class StudentWebService
{
	private static Logger logger = LoggerFactory.getLogger(StudentWebService.class);

	@Autowired
	private com.sumobits.edu.tracker.service.StudentService studentService = null;
	
	@GetMapping(value="/list", produces="application/json")
	public Response getStudents(HttpServletRequest request)
	{
		logger.info("Received request for '{}' from client '{}'", request.getRequestURL(), request.getRemoteHost());
		Set<Student> students = studentService.findAll();
		logger.info("Retrieved '{}' students from persistence.", students.size());
		return Response.status(Response.Status.OK).entity(students.toString()).build();
	}
	
	@GetMapping(value="/view", produces="application/json")
	public Response findStudent(@QueryParam("student_id") String studentId, HttpServletRequest request)
	{
		logger.info("Received request for '{}' from client '{}'", request.getRequestURL(), request.getRemoteHost());
		Student student = studentService.findByStudentId(studentId);
		logger.info("Retrieved '{}' student from persistence.", student);
		return Response.status(Response.Status.FOUND).entity(student.writeObject()).build();		
	}
	
	@PostMapping(value="/create", consumes= "application/json", produces="application/json")
	public Response createStudent(@RequestParam("student") Student student, HttpServletRequest request, HttpServletResponse response)
	{
		logger.info("Received request for '{}' from client '{}'", request.getRequestURL(), request.getRemoteHost());
		student = studentService.save(student);
		logger.info("Updated '{}' student from persistence.", student);
		return Response.status(Response.Status.ACCEPTED).entity(student.writeObject()).build();
	}

	@PutMapping(value="/save", consumes="application/json", produces="application/json")
	public Response saveStudent(@RequestParam(name="student") Student student, HttpServletRequest request)
	{
		logger.info("Received request for '{}' from client '{}'", request.getRequestURL(), request.getRemoteHost());
		student = studentService.save(student);
		logger.info("Updated '{}' student from persistence.", student);
		return Response.status(Response.Status.ACCEPTED).entity(student.writeObject()).build();
	}
	
	@DeleteMapping(value="/delete", produces="text/plain")
	public Response deleteStudent(@QueryParam("student_id")String studentId, HttpServletRequest request)
	{
		logger.info("Received request for '{}' from client '{}'", request.getRequestURL(), request.getRemoteHost());
		Student student = studentService.findByStudentId(studentId);
		
		if(null == student)
		{
			studentService.delete(student);
			logger.info("Deleted '{}' student from persistence.", student);
		}

		return Response.status(Response.Status.ACCEPTED).entity(student.writeObject()).build();
	}
}
