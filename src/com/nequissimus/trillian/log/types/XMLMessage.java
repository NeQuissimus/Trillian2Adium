package com.nequissimus.trillian.log.types;

// Save all the data for each message in a message object
public class XMLMessage {
	
	private String sType;
	private int iTimeStamp;
	private String sFrom;
	private String sTo;
	private String sFromDisplay;
	private String sText;
	
	public XMLMessage(String type,int time,String from,String to,String fromDisplay,String text){
		
		sType = type;
		iTimeStamp = time;
		sFrom = from;
		sTo = to;
		sFromDisplay = fromDisplay;
		sText = text;
		
	}
	
	public int getTime() {
		return iTimeStamp;
	}
	public String getFrom() {
		return sFrom;
	}
	public String getFromDisplay() {
		return sFromDisplay;
	}
	public String getText() {
		return sText;
	}
	public String getTo() {
		return sTo;
	}
	public String getType() {
		return sType;
	}

}
