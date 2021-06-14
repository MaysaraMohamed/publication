package com.scopegroup.repository;


import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;	
import com.scopegroup.dao.Publication;



/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@NoRepositoryBean
public interface PublicationBaseRepository <T extends Publication> extends PagingAndSortingRepository<T, Long> {
	public Optional<Publication> findByISBN(String ISBN);
}