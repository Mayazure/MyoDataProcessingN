package ntime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NTimeParser {

	public long getTimestamp(String time){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
		Date date = null;
		try {
			date = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		 
		long timestamp = date.getTime();
		return timestamp;
	}

	public String getDateTime(String ts){
		Timestamp timestamp = new Timestamp(Long.valueOf(ts));
		Date date = new Date(timestamp.getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
		return simpleDateFormat.format(date);
	}
}
