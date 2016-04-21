package event;

public class RedAlert extends Event {
	
	public RedAlert() {
		super();
	}

	@Override
	public String Trigger() {
		return Event.RED_ALERT;
	}

}
