package com.nequissimus.trillian.log;

import java.util.ArrayList;

import com.nequissimus.library.file.TextFileReader;
import com.nequissimus.trillian.log.types.XMLLog;
import com.nequissimus.trillian.log.types.XMLSession;
import com.nequissimus.trillian.log.types.XMLMessage;

// Get data from trillian log format
public class XMLFile {

	private String sFileName;
	private boolean DEBUG;

	public XMLFile(String fileName,boolean debugMode) {

		if (fileName != null)
			sFileName = fileName;
		else
			sFileName = "";
		
		DEBUG = debugMode;

	} // public XMLFile(String fileName)

	public XMLLog getXML() {

		XMLLog xLog = new XMLLog();

		if (sFileName == "") {

			System.out.println("Error reading the list of XML files!");
			System.exit(0);

		} // if

		String currLine = new String();
		
		TextFileReader rFile = new TextFileReader(sFileName, DEBUG);
		
		ArrayList aFileContents = rFile.getEverything();
				
		XMLSession xSession = null;
			
		for (int x = 0 ; x < aFileContents.size() ; x++) {

			currLine = aFileContents.get(x).toString(); // jede Zeile einzeln
			
			if (currLine.startsWith("<session type=\"start\"")) {

				// Search for values
				// See how fucking stupid this is? Hello?
				String tmpTime = currLine.substring(
						currLine.indexOf("time=") + 6, currLine
								.indexOf("time=") + 16);
				int iTime = Integer.parseInt(tmpTime);
				String sFrom = currLine.substring(
						currLine.indexOf("from=") + 6, currLine.indexOf("\"",
								currLine.indexOf("from=") + 7));
				String sTo = currLine.substring(currLine.indexOf("to=") + 4,
						currLine.indexOf("\"", currLine.indexOf("to=") + 5));
				String sMedium = currLine.substring(
						currLine.indexOf("medium=") + 8, currLine.indexOf("\"",
								currLine.indexOf("medium=") + 9));
				xSession = new XMLSession(iTime, sMedium, sFrom, sTo);
				xSession.setFile(sFileName);
				
				//System.out.print(xSession.getFile());
				//System.exit(0);

			} else if ((currLine.startsWith("<message type=")) && (!currLine.startsWith("<message type=\"information_"))) {

				if (xSession != null) {
					
					// Each message into the session
					// And here we go again
					// Luckily, most of it was copy/paste from above
					String tmpTime = currLine.substring(currLine
							.indexOf("time=") + 6,
							currLine.indexOf("time=") + 16);
					int iTime = Integer.parseInt(tmpTime);
					String sType = currLine.substring(
							currLine.indexOf("type=") + 6, currLine.indexOf(
									"\"", currLine.indexOf("type=") + 6));
					String sFrom = currLine.substring(
							currLine.indexOf("from=") + 6, currLine.indexOf(
									"\"", currLine.indexOf("from=") + 6));
					String sTo = currLine.substring(
							currLine.indexOf("to=") + 4, currLine.indexOf("\"",
									currLine.indexOf("to=") + 4));
					String sFromDisplay = currLine.substring(currLine
							.indexOf("from_display=") + 14, currLine.indexOf(
							"\"", currLine.indexOf("from_display=") + 14));
					String sText = currLine.substring(
							currLine.indexOf("text=") + 6, currLine.indexOf(
									"\"", currLine.indexOf("text=") + 6));
					XMLMessage xMessage = new XMLMessage(sType, iTime, sFrom,
							sTo, sFromDisplay, sText);
					xSession.xMessages.add(xMessage);

				}

			} else if (currLine.startsWith("<session type=\"stop")) {

				// Session ends, get stop time and close session
				if (xSession != null) {
					
					String tmpTime = currLine.substring(currLine
							.indexOf("time=") + 6,
							currLine.indexOf("time=") + 16);
					int iTime = Integer.parseInt(tmpTime);
					
					xSession.setStopTime(iTime);
					
					xLog.sessions.add(xSession);
					xSession = null;

				} // if

			} // else

		} // while

		return xLog;

	} // public XMLLog getXML()

}
