package controller.label;

public class NSecondInfo {

	public static final int ACTION_NONE = 0;
	public static final int ACTION_LEFT_STEERING = 1;
	public static final int ACTION_RIGHT_STEERING = 2;
	public static final int ACTION_OTHERS = 3;
	
	private int action;
	private int minute;
	private int second;
	private int millisecond;
	
	public NSecondInfo(){
		this(0,0,0,0);
	}
	
	public NSecondInfo(int action, int minute, int second, int millisecond) {
		this.action = action;
		this.minute = minute;
		this.second = second;
		this.millisecond = millisecond;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getMillisecond() {
		return millisecond;
	}

	public void setMillisecond(int millisecond) {
		this.millisecond = millisecond;
	}
	
	
}
