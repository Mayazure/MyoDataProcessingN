package nrangedata;

import ntime.NTimeParser;

public class NRangeParser {
	
	private NTimeParser timeParser = new NTimeParser();
	
	//New Analysis Parse
	public NRangeInfo parseRange(String line){
		
		line = line.trim();
		
		if(line.endsWith("x")){
			line = line.substring(0,line.length()-1);
		}
		
		int len = line.length();
		
		//Get EMG data level must be one of {0,1}.
		int level = 0;
		if(line.charAt(len-1)=='>'){
			level = 1;
		}
		else{
			level = 0;
		}
		
		//Get time and convert to timestamp
		int s1 = line.indexOf(">");
		String dateTime = line.substring(0, s1);
		String timestamp = String.valueOf(timeParser.getTimestamp(dateTime));
		
		//Get emotion range data
		line = line.substring(s1+1,len-1);
		String[] datas = line.split("\\$..=");
		
		NRangeInfo nRangeInfo = new NRangeInfo(timestamp, datas[1], datas[2], level);
		return nRangeInfo;
	}
	
	public NLineInfo parseOldRange(String line){
				
		int test = line.contains("TEST")?1:0;
		
		int start = line.indexOf(">");
		String datetime = line.substring(0,start);
		
		line = line.substring(start+1).trim();
		String[] data = line.split("\\$..=");
		
		return new NLineInfo(datetime, data[1],data[2],data[3],data[4],test);
	}
}
