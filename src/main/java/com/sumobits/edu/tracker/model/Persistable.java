package com.sumobits.edu.tracker.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Persistable extends Serializable
{
	public Long getId();
	
	public void setId(long id);
}
