package com.nequissimus.adium.log.types;


// Turn message data into an Adium message
public class MessageFormat {

	private String sTime;
	private String sFrom;
	private String sFromDisplay;
	private String sText;

	public MessageFormat(String time, String from, String fromDisplay,
			String text) {

		sTime = time;
		sFrom = from;
		sFromDisplay = fromDisplay;
		sText = replaceHTMLEntities(text);

	}
	
	private String replaceHTMLEntities(String tmp){
		
		tmp = EncodingUtil.decodeURIComponent(tmp.replaceAll("<", "")).replaceAll("<-","-").replaceAll("&", "");
	    
		if (tmp.endsWith("<"))
			tmp = tmp.substring(0, tmp.length()-2) + "(";
		
		return tmp;
		
	}

	public String toString() {

		// This is what an Adium message looks like
		return "<message time=\""
				+ sTime
				+ "\" sender=\""
				+ sFrom
				+ "\" alias=\""
				+ sFromDisplay
				+ "\"><div><span style=\"font-family: Helvetica; font-size: 12pt;\">"
				+ sText + "</span></div></message>";

	}

}