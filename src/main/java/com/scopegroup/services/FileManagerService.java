package com.scopegroup.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	// private static final Logger LOGGER =
	// Logger.getLogger(FileManageController.class.getName());

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

				if (Utilities.missingMandatoryFields(columns)) {
					failedMFieldsCount++;
					continue;
				}
				Set<Publication> publications = new HashSet<Publication>();

				// Skip header .
				if (!columns[0].contains("email") && !columns[0].contains("firstName")) {
					Author author = new Author(columns[0], columns[1], columns[2]);
					Author dbAuthor = new Author();
					System.out.println("Author is :" + author.toString());

					// check if author not exist then create
					if (!authorRepository.findByEmail(author.getEmail()).isPresent()) {
						if (columns[0].length() > 0) {
							dbAuthor = authorRepository.save(author);
							System.out.println("DB Author is :" + dbAuthor.toString());
							authorsCount++;
						}
					} else {
						dbAuthor = authorRepository.findByEmail(author.getEmail()).get();
					}

					if (!publicationRepository.findByISBN(columns[4]).isPresent()) {

						// Skip publication without author.
						if (columns[0].length() < 1) {
							publicationWithNoAuthorCount++;
							continue;
						}

						if (columns[3].equalsIgnoreCase("B")) {
							Publication book = new Book(columns[4], columns[5], columns[6]);
							System.out.println("Publication is :" + book.toString());
							publicationRepository.save(book);

							// add publication to the author
							publications = dbAuthor.getPublications();
							publications.add(book);
							dbAuthor.setPublications(publications);

							// update author if exist
							if (authorRepository.findByEmail(author.getEmail()).isPresent()) {
								authorRepository.save(dbAuthor);
							}
							booksCount++;
						} else if (columns[3].equalsIgnoreCase("M")) {
							Publication magazine = new Magazine(columns[4], columns[5],
									Utilities.stringToDate(columns[7]));
							System.out.println("Publication is :" + magazine.toString());
							publicationRepository.save(magazine);

							// add publication to the author
							publications = dbAuthor.getPublications();
							publications.add(magazine);
							dbAuthor.setPublications(publications);

							// update author if exist
							if (authorRepository.findByEmail(author.getEmail()).isPresent()) {
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
						if (authorRepository.findByEmail(author.getEmail()).isPresent()) {
							authorRepository.save(dbAuthor);
						}
					}
				}
			}

		} catch (IOException ex) {
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
