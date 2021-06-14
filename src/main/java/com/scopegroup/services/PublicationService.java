package com.scopegroup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.scopegroup.dao.Publication;
import com.scopegroup.repository.PublicationRepository;

/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-13
 */
@Service
public class PublicationService {

	@Autowired
	private PublicationRepository publicationRepository;

	public Publication getPublicationByISBN(@PathVariable String ISBN) {
		return publicationRepository.findByISBN(ISBN).get();
	}

	public List<Publication> getPublicationByAuthorMail(@PathVariable String mail) {
		return publicationRepository.findByAuthors_email(mail);
	}

	public List<Publication> getAllPublications() {
		return publicationRepository.findAll();
	}
}
