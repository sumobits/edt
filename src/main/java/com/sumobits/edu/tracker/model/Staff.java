package com.sumobits.edu.tracker.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "edt_staff")
public class Staff extends Person implements Persistable, ExternalEntity
{
	private static Logger logger = LoggerFactory.getLogger(Staff.class);

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
	@Column(name = "pk", updatable = false)
	@JsonProperty
	private long id;

	@NotNull
	@Size(min=10, max=80)
	@Column(name="employee_id", updatable=false, length=80)
	@JsonProperty
	private String employeeId;
	
	@NotNull
	@ManyToOne
	@JsonProperty
	private Campus campus;
	
	@Embedded
	@JsonProperty
	private Audit audit;
	
	@Override
	public long getId()
	{
		return id;
	}

	@Override
	public void setId(long id)
	{
		this.id = id;
	}

	public String getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

	public Campus getCampus()
	{
		return campus;
	}

	public void setCampus(Campus campus)
	{
		this.campus = campus;
	}

	public Audit getAudit()
	{
		return audit;
	}

	public void setAudit(Audit audit)
	{
		this.audit = audit;
	}

	@Override
	public Staff readObject(String input)
	{
		Staff staff = null;
		
		try
		{
			ObjectMapper objectMapper = new ObjectMapper();
			staff = objectMapper.readValue(input, Staff.class);
		}
		catch(IOException e)
		{
			logger.error("Exception deserializing Staff from '{}': '{}'", input, e.getMessage(), e);
		}
		
		return staff;
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
			logger.error("Exception serializing Staff '{}' : '{}'", this, e.getMessage(), e);
		}
		
		return result;
	}

	@Override
	public String toString()
	{
		return "Staff [" + writeObject() + "]";
	}
	
}
