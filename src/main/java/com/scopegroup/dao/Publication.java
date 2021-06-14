package com.scopegroup.dao;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;


/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@Inheritance
@Entity
@DiscriminatorColumn(name="dType", discriminatorType=DiscriminatorType.STRING)
abstract public class Publication {
	
	@Column(unique=true)
	private String ISBN; 
	private String title;
	private char pType; 
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	
    @ManyToMany(mappedBy = "publications")
    private Set<Author> authors;
	
	/**
	 * 
	 */
	public Publication() {
	}


	/**
	 * @param iSBN
	 * @param title
	 * @param id
	 * @param authors
	 */
	public Publication(String iSBN, String title, char pType ) {
		super();
		this.ISBN = iSBN;
		this.title = title;
		this.pType = pType;
	}



	/**
	 * @return the publications
	 */
	public Set<Author> getAuthors() {
		return authors;
	}



	/**
	 * @param publications the publications to set
	 */
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
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
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}


	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	

	/**
	 * @return the pType
	 */
	public char getpType() {
		return pType;
	}


	/**
	 * @param pType the pType to set
	 */
	public void setpType(char pType) {
		this.pType = pType;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Publication [ISBN=" + ISBN + ", title=" + title + ", pType=" + pType + ", id=" + id + ", authors="
				+ authors + "]";
	}
	
}
