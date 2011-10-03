package com.nequissimus.library.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Read an entire file into an ArrayList
public class TextFileReader {
	
	private static String sFileName;
	private boolean DEBUG;
	
	public TextFileReader(String fileName, boolean debugMode) {
		
		sFileName = fileName;
		DEBUG = debugMode;
		
	}

	public ArrayList getEverything() {

		//StringBuffer sbContents = new StringBuffer();
		ArrayList aContents = new ArrayList();

		try {
			
			File fTextFile = new File(sFileName);
			BufferedReader brContents = new BufferedReader(new FileReader(
					fTextFile));

			try {

				String line = null;

				while ((line = brContents.readLine()) != null) {

					aContents.add(line);
					
					//sbContents.append(line);
					//sbContents.append(System.getProperty("line.separator"));

				} // try

			} finally {

				brContents.close();

			} // finally

		} catch (IOException ex) {

			if (DEBUG)
				System.out.println("TextFileReader: " + ex.getMessage());

		} // catch

		return aContents;

	} // static public String getEverything(File fTextFile)

} // public class TextFileReader
