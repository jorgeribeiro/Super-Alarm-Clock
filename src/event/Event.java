package event;

import java.util.Date;

public abstract class Event {
	private Date time;
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public abstract void Trigger();
}
