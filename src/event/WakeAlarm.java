package event;

public class WakeAlarm extends Event {
	
	public WakeAlarm() {
		super();
	}
	
	@Override
	public String Trigger() {
		return Event.WAKE_ALARM;
	}

}
