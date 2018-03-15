package com.sumobits.edu.tracker.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person
{
	@NotNull
	@Size(min = 2, max = 30)
	@Column(name = "first_name", nullable = false, length = 30)
	private String firstName;

	@Size(min = 0, max = 30)
	@Column(name = "middle_name", nullable = true, length = 30)
	private String middleName;

	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Embedded
	private Address homeAddress;

	@NotNull
	@Size(min = 10, max = 25)
	@Column(name = "main_phone", nullable = false, length = 25)
	private String mainPhone;

	@Size(min = 10, max = 25)
	@Column(name = "mobile_phone", nullable = true, length = 25)
	private String mobilePhone;

	@Size(min = 10, max = 25)
	@Column(name = "work_phone", nullable = true, length = 25)
	private String workPhone;

	@NotNull
	@Size(min = 10, max = 25)
	@Column(name = "last_name", nullable = false, length = 25)
	@Email
	private String email;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Address getHomeAddress()
	{
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress)
	{
		this.homeAddress = homeAddress;
	}

	public String getMainPhone()
	{
		return mainPhone;
	}

	public void setMainPhone(String mainPhone)
	{
		this.mainPhone = mainPhone;
	}

	public String getMobilePhone()
	{
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}

	public String getWorkPhone()
	{
		return workPhone;
	}

	public void setWorkPhone(String workPhone)
	{
		this.workPhone = workPhone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
}
