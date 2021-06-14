package com.scopegroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.scopegroup.dao.Publication;
import com.scopegroup.services.PublicationService;

/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-12
 */
@RestController
public class PublicationController {

	@Autowired
	private PublicationService publicationService;

	
	@GetMapping("/publication/isbn/{ISBN}")
	public Publication getPublicationByISBN(@PathVariable String ISBN) {
		return publicationService.getPublicationByISBN(ISBN);
	}

	@GetMapping("/publication/mail/{mail}")
	public List<Publication> getPublicationByAuthorMail(@PathVariable String mail) {
		return publicationService.getPublicationByAuthorMail(mail);
	}

	@GetMapping("/publication")
	public List<Publication> getAllPublications() {
		return publicationService.getAllPublications();
	}

}
