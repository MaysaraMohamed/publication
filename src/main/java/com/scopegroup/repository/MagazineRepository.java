package com.scopegroup.repository;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.scopegroup.dao.Magazine;



/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@RepositoryRestResource(collectionResourceRel = "magazine", path = "magazine")
public interface MagazineRepository extends PublicationBaseRepository<Magazine> {
}