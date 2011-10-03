package com.nequissimus.test.filetest;

import java.io.File;
import java.io.FilenameFilter;

import com.nequissimus.adium.log.AdiumReader;
import com.nequissimus.trillian.log.XMLFile;
import com.nequissimus.trillian.log.types.XMLLog;

// Run class, get arguments, start everything
// Cry if no arguments
public class RunClass {

	private static boolean DEBUG = false;

	public static void main(String[] args) {

		File fDir = new File(".");
		FilenameFilter fFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		};
		String[] sFiles = fDir.list(fFilter);

		for (String sFileName : sFiles) {

			// System.out.println(sFileName);

			XMLFile trillianReader = new XMLFile(sFileName, DEBUG);
			XMLLog trillianLog = trillianReader.getXML();

			AdiumReader adiumReader = new AdiumReader(trillianLog, DEBUG);
			adiumReader.createLogs();

		}

	}

}
