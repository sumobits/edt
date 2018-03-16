package com.sumobits.edu.tracker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Embeddable
@JsonRootName(value="audit")
public class Audit implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name="created_dt", nullable=false, updatable=false)
	@JsonProperty
	private DateTime created;
	
	@Column(name="updated_dt", nullable=false, updatable=false)
	@JsonProperty
	private DateTime updated;

	public DateTime getCreated()
	{
		return created;
	}

	public void setCreated(DateTime created)
	{
		this.created = created;
	}

	public DateTime getUpdated()
	{
		return updated;
	}

	public void setUpdated(DateTime updated)
	{
		this.updated = updated;
	}
	
}
