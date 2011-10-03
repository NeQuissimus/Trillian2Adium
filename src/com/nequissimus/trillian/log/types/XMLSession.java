package com.nequissimus.trillian.log.types;

import java.util.ArrayList;

// Session object that holds all the messages and some essential info
public class XMLSession {

	private int iTimeStamp;
	private int iStopTimeStamp;
	private String sMedium;
	private String sFrom;
	private String sTo;
	public ArrayList xMessages;
	private String sFromFile;

	public XMLSession(int time, String medium, String from, String to) {

		iTimeStamp = time;
		sMedium = medium;
		sFrom = from;
		sTo = to;
		xMessages = new ArrayList();

	}

	public int getTime() {
		return iTimeStamp;
	}

	public String getFrom() {
		return sFrom;
	}

	public String getMedium() {
		return sMedium;
	}

	public String getTo() {
		return sTo;
	}

	public ArrayList getMessages() {
		return xMessages;
	}
	
	public int getStopTime() {
		return iStopTimeStamp;
	}
	
	public String getFile(){
		return sFromFile;
	}
	
	public void setStopTime(int timestamp){
		iStopTimeStamp = timestamp;		
	}
	public void setFile(String file) {
		sFromFile = file.substring(0, file.length()-4);
	}

}
