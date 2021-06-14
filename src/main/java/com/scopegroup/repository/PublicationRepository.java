package com.scopegroup.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.scopegroup.dao.Publication;



/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@RepositoryRestResource(collectionResourceRel = "publication", path = "publication")
public interface PublicationRepository extends PublicationBaseRepository<Publication> {
	
	public Optional<Publication> findByISBN(String ISBN); 
	
	// to get publications by author's mail
	public List<Publication> findByAuthors_email(String email); 
	
	public List<Publication> findAll(); 
}