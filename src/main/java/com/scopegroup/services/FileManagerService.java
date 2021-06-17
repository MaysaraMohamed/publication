package com.scopegroup.services;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scopegroup.controller.FileManageController;
import com.scopegroup.dao.Author;
import com.scopegroup.dao.Book;
import com.scopegroup.dao.Magazine;
import com.scopegroup.dao.Publication;
import com.scopegroup.repository.AuthorRepository;
import com.scopegroup.repository.PublicationRepository;
import com.scopegroup.utilities.Utilities;

@Service
public class FileManagerService {

	@Autowired
	private PublicationRepository publicationRepository;

	@Autowired
	private AuthorRepository authorRepository;

	private static final Logger LOGGER = Logger.getLogger(FileManageController.class.getName());

	/**
	 * processFile to load file data into DB.
	 * 
	 * @return
	 */
	public HashMap<String, Integer> processFile(String filePath) {

		int authorsCount = 0;
		int booksCount = 0;
		int magazinsCount = 0;
		int publicationWithNoAuthorCount = 0;
		int failedMFieldsCount = 0;

		// create a reader
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {

			// read the file line by line
			String line;
			while ((line = br.readLine()) != null) {

				String[] columns = Utilities.splitLinebyComma(line);

				if (Utilities.missingMandatoryPublicationFields(columns)) {
					failedMFieldsCount++;
					continue;
				}
				Set<Publication> publications = new HashSet<Publication>();

				// Skip header .
				if (!line.contains("email") && !line.contains("firstName")) {
					Author author = new Author(columns[0], columns[1], columns[2]);
					Author dbAuthor = new Author();
					LOGGER.info("Author is :" + author.toString());
					boolean autherIsPresent = authorRepository.findByEmail(author.getEmail()).isPresent();

					// check if author not exist then create
					if (!autherIsPresent) {
						if (Utilities.authorIsPresentInSheet(author)) {
							dbAuthor = authorRepository.save(author);
							LOGGER.info("DB Author is :" + dbAuthor.toString());
							authorsCount++;
						} else {
							// Skip publication without author.
							publicationWithNoAuthorCount++;
							failedMFieldsCount++;
							continue;
						}
					} else {
						dbAuthor = authorRepository.findByEmail(author.getEmail()).get();
					}

					if (!publicationRepository.findByISBN(columns[4]).isPresent()) {

						if (columns[3].equalsIgnoreCase("B")) {
							Publication book = new Book(columns[4], columns[5], columns[6]);
							LOGGER.info("Publication is :" + book.toString());
							publicationRepository.save(book);

							// add publication to the author
							publications = dbAuthor.getPublications();
							publications.add(book);
							dbAuthor.setPublications(publications);

							// update author if exist
							if (autherIsPresent) {
								authorRepository.save(dbAuthor);
							}
							booksCount++;
						} else if (columns[3].equalsIgnoreCase("M")) {
							Publication magazine = new Magazine(columns[4], columns[5],
									Utilities.stringToDate(columns[7]));
							LOGGER.info("Publication is :" + magazine.toString());
							publicationRepository.save(magazine);

							// add publication to the author
							publications = dbAuthor.getPublications();
							publications.add(magazine);
							dbAuthor.setPublications(publications);

							// update author if exist
							if (autherIsPresent) {
								authorRepository.save(dbAuthor);
							}
							magazinsCount++;
						}

					} else {
						// if publication is already exit then get and add to relation with author.
						Publication dbPublication = publicationRepository.findByISBN(columns[4]).get();

						publications = dbAuthor.getPublications();
						publications.add(dbPublication);

						dbAuthor.setPublications(publications);
						if (autherIsPresent) {
							authorRepository.save(dbAuthor);
						}
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("Inserted_Authors", authorsCount);
		hmap.put("Inserted_Books", booksCount);
		hmap.put("Inserted_Magazines", magazinsCount);
		hmap.put("Failed_Publications_Coz_There_Is_No_Author", publicationWithNoAuthorCount);
		hmap.put("Failed_Coz_Of_Missing_Mandatory_Fields", failedMFieldsCount);

		return hmap;
	}

}
