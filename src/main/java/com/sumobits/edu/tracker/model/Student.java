package com.sumobits.edu.tracker.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "edt_student")
public class Student extends Person implements Persistable, ExternalEntity
{
	private static Logger logger = LoggerFactory.getLogger(Student.class);
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
	@Column(name = "pk", updatable = false)
	@JsonProperty
	private Long id;

	@NotNull
	@Size(min = 10, max = 25)
	@JsonProperty
	private String studentId;
	
	@ManyToMany
	@JoinTable(name = "edt_student_campus", joinColumns= @JoinColumn(referencedColumnName="pk"))
	@NotNull
	@JsonProperty
	private Campus campus;

	public Long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public Campus getCampus()
	{
		return campus;
	}

	public void setCampus(Campus campus)
	{
		this.campus = campus;
	}
	@Override
	public Student readObject(String input)
	{
		Student student = null;
		
		try
		{
			ObjectMapper objectMapper = new ObjectMapper();
			student = objectMapper.readValue(input, Student.class);
		}
		catch(IOException e) 
		{
			logger.error("Exceotion deserializing Student from '{}': '{}'", input, e.getMessage(), e);
		}
		
		return student; 
	}

	@Override
	public String writeObject()
	{
		String result = null;
		try
		{
			ObjectMapper objectMapper = new ObjectMapper();
			result = objectMapper.writeValueAsString(this);
		}
		catch(JsonProcessingException e)
		{
			logger.error("Failed to marshall student to JSON: '{}'", e.getMessage(), e);
		}
		
		return result;
	}
}
