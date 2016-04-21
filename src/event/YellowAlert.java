package event;

public class YellowAlert extends Event {
	
	public YellowAlert() {
		super();
	}

	@Override
	public String Trigger() {
		return Event.YELLOW_ALERT;
	}

}
