package com.scopegroup.dao;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@Entity
@DiscriminatorValue("M")
public class Magazine extends Publication {
	private Date issueDate;

	/**
	 * 
	 */
	public Magazine() {
	}

	/**
	 * @param pType
	 * @param iSBN
	 * @param title
	 * @param id
	 * @param authors
	 */
	public Magazine(String iSBN, String title, Date issueDate) {
		super(iSBN, title,'M');
		this.issueDate = issueDate;
	}

	/**
	 * @return the issueDate
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate
	 *            the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

}
