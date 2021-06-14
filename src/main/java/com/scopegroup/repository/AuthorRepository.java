package com.scopegroup.repository;


import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.scopegroup.dao.Author;



/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@RepositoryRestResource(collectionResourceRel = "author", path = "author")
public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {
	public Optional<Author> findByEmail(String email); 
}