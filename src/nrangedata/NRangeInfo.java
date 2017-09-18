package nrangedata;

public class NRangeInfo {

	private String timestamp;
	private String range1;
	private String range2;
	private int level;
	
	public NRangeInfo(String timestamp, String range1, String range2, int level) {
		super();
		this.timestamp = timestamp;
		this.range1 = range1;
		this.range2 = range2;
		this.level = level;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
}