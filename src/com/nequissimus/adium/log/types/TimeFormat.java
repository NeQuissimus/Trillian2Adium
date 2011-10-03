package com.nequissimus.adium.log.types;

import java.text.SimpleDateFormat;
import java.util.Date;


// Since Adium uses two different time formats, this came in handy...
// This way the correct format can easily be accessed
public class TimeFormat {
	
	private long lTimeStamp;
	
	public TimeFormat(long timestamp) {
		
		lTimeStamp = timestamp;
		
	}
	
	public void setTimeStamp(long timestamp) {lTimeStamp = timestamp;}
	
	public String getAdiumTime(boolean withColon,boolean withTimeStamp) {
		
		String timeZone = "0200";
		SimpleDateFormat dFormatter = null;
		
		if (withColon) {
			timeZone = "02:00";
			dFormatter = new SimpleDateFormat("yyyy-MM-dd:kk:mm:ss");
		} else {
			dFormatter = new SimpleDateFormat("yyyy-MM-dd:kk.mm.ss");
		}
			
		String sTimeStamp = Long.toString(lTimeStamp);
		Date dChatTime = new Date();
			dChatTime.setTime(lTimeStamp*100);
		String sFormattedTime = "";
		
		try {
			
			sFormattedTime = dFormatter.format(new Date(lTimeStamp*1000));
			sFormattedTime = sFormattedTime.replaceFirst(":", "T") + "+" + timeZone;
			
			if (withTimeStamp)
				sFormattedTime = sTimeStamp + " (" + sFormattedTime + ")";
			
		} catch (Exception pe) {
			
			System.out.println("doof");
			
		}
		return sFormattedTime;
		
	}

}
