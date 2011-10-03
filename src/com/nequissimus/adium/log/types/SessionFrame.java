package com.nequissimus.adium.log.types;


// Session data for beginning and ending an Adium file
public class SessionFrame {

	private String sReceiver;
	private String sTime;
	private String sProtocol;
	private String sStopTime;
	
	public SessionFrame(String receiver,String time,String protocol,String stopTime){
		
		sReceiver = receiver;
		sTime = time;
			// Why the fuck is the time format different inside the log?
			sTime = sTime.substring(sTime.indexOf("(")+1, sTime.length()-3) + ":00";
		sStopTime = stopTime;
			sStopTime = sStopTime.substring(sStopTime.indexOf("(")+1, sStopTime.length()-3) + ":00";
		sProtocol = protocol;
		
	}
	
	public String getHeader() {

		return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
				//+ System.getProperty("line.separator")
				+ "<chat account=\""+sReceiver+"\" "
				+ "xmlns=\"http://purl.org/net/ulf/ns/0.4-02\" "
				+ "service=\""+sProtocol+"\">"
				+ "<event time=\""+sTime+"\" sender=\""+sReceiver+"\" "
				+ "type=\"windowOpened\"></event>";

	}
	
	public String getFooter() {
		
		return "<event time=\""+ sStopTime +"\" sender=\""+ sReceiver +"\" "
		+"type=\"windowClosed\"></event></chat>";
		
	}

}
