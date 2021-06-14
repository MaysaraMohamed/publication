package com.scopegroup.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@Entity
public class Author {
	
	//@Column(unique=true)
	private String email; 
	private String firstName; 
	private String lastName; 
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
    
    @JsonIgnore
    @ManyToMany
	@JoinTable(name = "publication_author", joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "publication_id", referencedColumnName = "id"))
    Set<Publication> publications;
	/**
	 * 
	 */
	public Author() {
	}

	

	/**
	 * @param email
	 * @param firstName
	 * @param lastName
	 */
	public Author(String email, String firstName, String lastName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the publicationAuthors
	 */
	public Set<Publication> getPublications() {
		if(publications == null) {
			return new HashSet<Publication>();
		}else {
			return publications;
		}
	}



	/**
	 * @param publications the publications to set
	 */
	public void setPublications(Set<Publication> publications) {
		this.publications = publications;
	}

	

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Author [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", id=" + id
				+ ", publications=" + publications + "]";
	}

}
