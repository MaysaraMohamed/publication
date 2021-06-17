package com.scopegroup.services;

import java.util.ArrayList;
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

	public List<Publication> getPublicationByISBN(@PathVariable String ISBN) {
		List<Publication> publications = new ArrayList<Publication>();
		if(publicationRepository.findByISBN(ISBN).isPresent()){
			publications.add(publicationRepository.findByISBN(ISBN).get());
			return publications; 
		}else {
			return publications; 
		}
	}

	public List<Publication> getPublicationByAuthorMail(@PathVariable String mail) {
		return publicationRepository.findByAuthors_email(mail);
	}

	public List<Publication> getAllPublications() {
		return publicationRepository.findAll();
	}
}
