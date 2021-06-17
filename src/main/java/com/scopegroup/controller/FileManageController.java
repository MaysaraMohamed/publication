package com.scopegroup.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scopegroup.common.Response;
import com.scopegroup.services.FileManagerService;
import com.scopegroup.utilities.Utilities;

/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */
@RestController
public class FileManageController {

	@Autowired
	private FileManagerService fileManagerService;

	@Value("${upload.path}")
	private String DIR_TO_UPLOAD;

	@Value("${supported.extension}")
	private String EXT;

	private static final Logger LOGGER = Logger.getLogger(FileManageController.class.getName());

	/**
	 * upload input file and call processFile method to insert into DB. 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	
	@PostMapping("/upload")
	public ResponseEntity<Response> uploadToDirectory(@RequestParam("file") MultipartFile file) throws IOException {

		// Create directory if not exist. 
		File directory = new File(DIR_TO_UPLOAD);
		if (! directory.exists()){
		  directory.mkdir();
		}
		
		// Save file to on server. 
		Response response = new Response();
		byte[] bytes = file.getBytes();
		String fileName = file.getOriginalFilename();
		Path path = Paths.get(DIR_TO_UPLOAD + fileName);
		Files.write(path, bytes);

		// check if file extension is supported or no. 
		if (Utilities.extensionSupported(fileName, EXT)) {
			// if file extension supported then call processFile method to process file and insert into db. 
			HashMap<String, Integer> processedMap = fileManagerService.processFile(DIR_TO_UPLOAD + fileName);
			if (processedMap != null) {
				response.setResult(processedMap);
				response.setStatus(200);
				response.setMessage("Input file processed successfully");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				LOGGER.warning("File content not supported in file : " + path);
				response.setStatus(406);
				response.setMessage("File content not supported");
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
			}
		} else {
			LOGGER.warning("Extension not supported for file : " + path);
			response.setMessage("Extension not supported");
			response.setStatus(406);
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
		}
	}

}
