package ntime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NTimeParserOld {

	public long getTimestamp(String time){
		Timestamp timestamp = Timestamp.valueOf(time);
		long parsedTime = timestamp.getTime();
		return parsedTime;
	}

	public String getDate(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pDate = simpleDateFormat.format(date);
		return pDate;
	}
	
	public String getDate(long ts){
		Timestamp timestamp = new Timestamp(ts);
		Date date = new Date(timestamp.getTime());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pDate = simpleDateFormat.format(date);
		return pDate;
	}
	
	public String getTime(long ts){
		Timestamp timestamp = new Timestamp(ts);
		Date date = new Date(timestamp.getTime());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
		String pTime = simpleDateFormat.format(date);
		return pTime;
	}
}
