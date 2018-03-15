package com.sumobits.edu.tracker.model;

import java.io.Serializable;


/**
 * Base interfacce for all domain objects exposed over web clients 
 * excluding entinties serialized for 
 * @author ticook
 *
 */
public interface ExternalEntity extends Serializable
{

	/**
	 * Converts API formatted JSON string into concrete
	 * ExternalEntity instance.
	 * @param input
	 * @return
	 */
	public ExternalEntity readObject(String input);
	
	/**
	 * Converts ExternalEntity instance into API contracted 
	 * JSON representation.
	 * @return
	 */
	public String writeObject();
}
