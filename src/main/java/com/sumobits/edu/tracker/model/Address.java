package com.sumobits.edu.tracker.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Embeddable
@JsonRootName("address")
public class Address
{
	@Size(min = 0, max = 80)
	@Column(name = "address_line1", length = 80)
	@JsonProperty("line1")
	private String line1;

	@Size(min = 0, max = 80)
	@Column(name = "address_line2", length = 80)
	@JsonProperty("line2")
	private String line2;

	@Size(min = 0, max = 80)
	@Column(name = "address_line3", length = 80)
	@JsonProperty("line3")
	private String line3;

	@Size(min = 0, max = 80)
	@Column(name = "city", length = 80)
	@JsonProperty("city")
	private String city;

	@Size(min = 0, max = 80)
	@Column(name = "state", length = 40)
	@JsonProperty("state")
	private String state;

	@Size(min = 0, max = 80)
	@Column(name = "postalCode", length = 20)
	@JsonProperty("postalCode")
	private String postalCode;

	@Size(min = 0, max = 80)
	@Column(name = "country", length = 40)
	@JsonProperty("country")
	private String country;

	@Size(min = 0, max = 80)
	@Column(name = "latitude", length = 20)
	@JsonProperty("address_line1")
	private Double latitude;

	@Size(min = 0, max = 80)
	@Column(name = "latitude", length = 80)
	private Double longitude;

	public String getLine1()
	{
		return line1;
	}

	public void setLine1(String line1)
	{
		this.line1 = line1;
	}

	public String getLine2()
	{
		return line2;
	}

	public void setLine2(String line2)
	{
		this.line2 = line2;
	}

	public String getLine3()
	{
		return line3;
	}

	public void setLine3(String line3)
	{
		this.line3 = line3;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public Double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	public Double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

}
