package com.scopegroup.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

@Component
public class Utilities {
	
	/**
	 * check if input file has supported extension or no. 
	 * @param fileName
	 * @param extension
	 * @return
	 */
	public static boolean extensionSupported(String fileName, String extension) {
		String ext = fileName.substring(fileName.lastIndexOf(".")+1); 
		if(extension.equalsIgnoreCase(ext)) {
			return true; 
		}else {
			return false; 
		}
	}
	
	/**
	 * split input line into array of string and handling comma between double coutes. 
	 * @param line
	 * @return String[] 
	 */
	public static String[] splitLinebyComma(String line){
		
    	// remove quota from first and end of line. 
    	line = line.replaceAll("^\"|\"$", "");
    	// replace 2 double quotas by one double quote. 
    	line = line.replaceAll("\"\"", "\"");
    	
        // convert line into columns
    	//,           // Split on comma
    	//(?=         // Followed by
    	//  (?:      // Start a non-capture group
    	//     [^"]*  // 0 or more non-quote characters
    	//     "      // 1 quote
    	//     [^"]*  // 0 or more non-quote characters
    	//     "      // 1 quote
    	//   )*       // 0 or more repetition of non-capture group (multiple of 2 quotes will be even)
    	//   [^"]*    // Finally 0 or more non-quotes
    	//   $        // Till the end  (This is necessary, else every comma will satisfy the condition)
    	//)
        String[] columns = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        return columns; 
	}

	
	/**
	 * Convert string date to date format MM/dd/yyyyy
	 * @param stringDate
	 * @return Date
	 */
	public static Date stringToDate(String stringDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		try {
			date = formatter.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 
	 * @param columns
	 * @return boolean to define if input columns miss mandatory fields or no. 
	 */
	public static boolean missingMandatoryFields(String[] columns) {
		int max=2;
		int count=0; 
		for (String string : columns) {
			System.out.println("String is :"+string);
			if(string.length()<=0) {
				count++; 
			}
			if(columns.length<=7 && count > 0) {
				count++; 
			}
		}
		if(count>=max || columns.length < 7) {
			System.out.println("String is : true ");
			return true; 
		}else {
			System.out.println("String is : false ");
			return false;
		}
	}
}
