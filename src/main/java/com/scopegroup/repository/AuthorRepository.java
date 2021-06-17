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

//  PagingAndSortingRepository extends the CrudRepository and adds findAll
// 	methods that enable you to sort the result and to retrieve it in a paginated way

@RepositoryRestResource(collectionResourceRel = "author", path = "author")
public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {
	public Optional<Author> findByEmail(String email); 
}