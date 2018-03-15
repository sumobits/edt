package com.sumobits.edu.tracker.dao;

public interface GenericDao <T>
{	
	public T save(T instance);
	
	public boolean delete(T instance);
	
	public int deleteAll();
}
