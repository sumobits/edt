package com.sumobits.edu.tracker.model;

import java.io.Serializable;

public interface Persistable extends Serializable
{
	public long getId();
	
	public void setId(long id);
}
