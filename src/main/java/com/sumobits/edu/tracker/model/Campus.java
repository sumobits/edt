package com.sumobits.edu.tracker.model;

import java.io.IOException;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "edt_campus")
public class Campus implements Persistable, ExternalEntity
{
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(Campus.class);
	
	@Id
	@SequenceGenerator(name = "campus_id_seq", sequenceName = "campus_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campus_id_seq")
	@Column(name = "pk", updatable = false)
	@JsonProperty
	private Long id;
	
	@NotNull
	@Size(min=15, max=50)
	@Column(nullable = false, length=50, unique=true)
	@JsonProperty
	private String name;

	@Embedded
	@JsonProperty
	private Address address;
	
	@NotNull
	@Size(min=10, max=20)
	@Column(name="main_phone", length=20)
	@JsonProperty
	private String mainPhone;
	
	@NotNull
	@Size(min=10, max=20)
	@Column(name="office_phone", length=20)
	@JsonProperty
	private String officePhone;
	
	@OneToMany
	@JsonProperty
	private Staff principal;
	
	@ManyToOne
	@JsonProperty
	private Set<Staff> staff;
	
	public Long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public String getMainPhone()
	{
		return mainPhone;
	}

	public void setMainPhone(String mainPhone)
	{
		this.mainPhone = mainPhone;
	}

	public String getOfficePhone()
	{
		return officePhone;
	}

	public void setOfficePhone(String officePhone)
	{
		this.officePhone = officePhone;
	}

	public Staff getPrincipal()
	{
		return principal;
	}

	public void setPrincipal(Staff principal)
	{
		this.principal = principal;
	}

	public Set<Staff> getStaff()
	{
		return staff;
	}

	public void setStaff(Set<Staff> staff)
	{
		this.staff = staff;
	}

	@Override
	public Campus readObject(String input)
	{
		Campus campus = null;

		try
		{
			ObjectMapper objectMapper = new ObjectMapper();
			campus = objectMapper.readValue(input, Campus.class);
		}
		catch (IOException e)
		{
			logger.error("Exception deserializing Staff from '{}': '{}'", input,
			        e.getMessage(), e);
		}

		return campus;
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
		catch (JsonProcessingException e)
		{
			logger.error("Exception serializing Staff '{}' : '{}'", this, e.getMessage(), e);
		}

		return result;
	}
}
