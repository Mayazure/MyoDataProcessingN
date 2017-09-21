package nrangedata;

public class NLineInfo {
	
    private String datetime;
	private String timestamp1;
	private String timestamp2;
	private String range1;
	private String range2;
	private int test;
	
	public NLineInfo(String datetime, String timestamp1, String timestamp2, String range1, String range2, int test) {
		super();
		this.datetime = datetime;
		this.timestamp1 = timestamp1;
		this.timestamp2 = timestamp2;
		this.range1 = range1;
		this.range2 = range2;
		this.test = test;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getTimestamp1() {
		return timestamp1;
	}

	public void setTimestamp1(String timestamp1) {
		this.timestamp1 = timestamp1;
	}

	public String getTimestamp2() {
		return timestamp2;
	}

	public void setTimestamp2(String timestamp2) {
		this.timestamp2 = timestamp2;
	}

	public String getRange1() {
		return range1;
	}

	public void setRange1(String range1) {
		this.range1 = range1;
	}

	public String getRange2() {
		return range2;
	}

	public void setRange2(String range2) {
		this.range2 = range2;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public String toText(){
		
		StringBuilder builder = new StringBuilder();
		builder.append(datetime).append(",");
		builder.append(timestamp1).append(",");
		builder.append(timestamp2).append(",");
		builder.append(range1).append(",");
		builder.append(range2).append(",");
		builder.append(test);
		
		return builder.toString();
	}
}
