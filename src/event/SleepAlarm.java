package event;

public class SleepAlarm extends Event {
	
	public SleepAlarm() {
		super();
	}

	@Override
	public String Trigger() {
		return Event.SLEEP_ALARM;		
	}

}
