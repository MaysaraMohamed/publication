package com.scopegroup.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@Entity
@DiscriminatorValue("B")
public class Book extends Publication{
	private String description; 
	
	/**
	 * 
	 */
	public Book() {
	}

	
	/**
	 * @param iSBN
	 * @param title
	 * @param id
	 * @param authors
	 */
	public Book( String iSBN, String title, String description) {
		super( iSBN, title, 'B');
		this.description = description;
		
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
