package com.nequissimus.adium.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.nequissimus.adium.log.types.MessageFormat;
import com.nequissimus.adium.log.types.SessionFrame;
import com.nequissimus.adium.log.types.TimeFormat;
import com.nequissimus.library.file.FileFolderOperations;
import com.nequissimus.trillian.log.types.XMLLog;
import com.nequissimus.trillian.log.types.XMLMessage;
import com.nequissimus.trillian.log.types.XMLSession;

// Main class
// Turn log sessions into adium files
public class AdiumReader {

	private XMLLog xLog;
	private boolean DEBUG;

	public AdiumReader(XMLLog trillianLog, boolean debugMode) {

		xLog = trillianLog;
		DEBUG = debugMode;

	}

	public void createLogs() {

		TimeFormat adiumTime = new TimeFormat(0);

		for (int i = 0; i < xLog.sessions.size(); i++) {

			XMLSession xSession = (XMLSession) xLog.sessions.get(i);

			String sMainFolder = xSession.getFile();
			
			
			if (xSession.xMessages.size() > 0) {
				//	System.out.println(xSession.xMessages.size());

				adiumTime.setTimeStamp(xSession.getTime());
				String sFormattedTime = adiumTime.getAdiumTime(false, false);

				sFormattedTime = xSession.getTo() + " (" + sFormattedTime + ")";

				adiumTime.setTimeStamp(xSession.getStopTime());
				String sFormattedStopTime = adiumTime.getAdiumTime(true, false);

				// Create a folder for each session
				FileFolderOperations ffo = new FileFolderOperations(DEBUG);

				try {

					ffo.createDirectory(sMainFolder + File.separator + sFormattedTime + ".chatlog");

				} catch (Exception e) {

					if (DEBUG) {
					
						System.out.println("Could not create folders");
						System.exit(0);
					
					}

				}
				String sPath = ffo.getCurrentPath() + sMainFolder + File.separator + sFormattedTime
						+ ".chatlog" + File.separator;

				// Create file for each session

				File fLog = new File(sPath + sFormattedTime + ".xml");

				try {

					if (!fLog.exists())
						fLog.createNewFile();

				} catch (IOException e) {

					if (DEBUG)
						System.out.println("AdiumReader: " + e.getMessage());

				}

				try {
					//FileWriter fWrite = new FileWriter(fLog.getAbsoluteFile());
					//File fWrite = new File(fLog.getAbsoluteFile());
					BufferedWriter bwLog = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fLog.getAbsoluteFile()),"UTF-8"));
					SessionFrame sfLog = new SessionFrame(xSession.getTo(),
							sFormattedTime, xSession.getMedium(),
							sFormattedStopTime);
					bwLog.write(sfLog.getHeader());

					//

					// Write each message into the file
					for (int j = 0; j < xSession.xMessages.size(); j++) {

						// Adium-format please?
						XMLMessage xMessage = (XMLMessage) xSession.xMessages
								.get(j);
						adiumTime.setTimeStamp(xMessage.getTime());
						String sTime = adiumTime.getAdiumTime(true, false);
						String sFrom = xMessage.getFrom();
						String sFromDisplay = xMessage.getFromDisplay();
						String sType = xMessage.getType();
						String sText = xMessage.getText();

						MessageFormat adiumFormatter = new MessageFormat(sTime,
								sFrom, sFromDisplay, sText);

						String adiumMessage = adiumFormatter.toString();

						// No bullshit like warnings and stuff
						// Private messages into the log file
						if (sType.endsWith("privateMessage"))
							bwLog.write(adiumMessage); 

					}

					// all done, close file
					bwLog.write(sfLog.getFooter());
					bwLog.close();

				} catch (IOException e) {

					if (DEBUG)
						System.out.println("AdiumReader: " + e.getMessage());

				}
				System.out.println("Done! Logs are at " + sPath);
			}
		}

	}

}
