package nrangedata;

import nconfig.NConfig;

public class NRangeInfoWithLine {

	private int lineStart;
	private int lineEnd;
	private int angerLevel;
	private int anxiousLevel;
	
	public NRangeInfoWithLine(int lineEnd, int angerLevel, int anxiousLevel) {
		super();
		this.lineStart = lineEnd - NConfig.windowLength + 1;
		this.lineEnd = lineEnd;
		this.angerLevel = angerLevel;
		this.anxiousLevel = anxiousLevel;
	}
	
	public int getLineStart() {
		return lineStart;
	}
	public void setLineStart(int lineStart) {
		this.lineStart = lineStart;
	}
	
	public int getLineEnd() {
		return lineEnd;
	}
	public void setLineEnd(int lineEnd) {
		this.lineEnd = lineEnd;
	}
	
	public int getAngerLevel() {
		return angerLevel;
	}
	public void setAngerLevel(int angerLevel) {
		this.angerLevel = angerLevel;
	}
	
	public int getAnxiousLevel() {
		return anxiousLevel;
	}
	public void setAnxiousLevel(int anxiousLevel) {
		this.anxiousLevel = anxiousLevel;
	}
}