package com.scopegroup.repository;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.scopegroup.dao.Book;



/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@RepositoryRestResource(collectionResourceRel = "book", path = "book")
public interface BookRepository extends PublicationBaseRepository<Book> {
}