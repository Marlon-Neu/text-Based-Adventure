package text_Base_Adventure;

import java.util.ArrayList;
import java.util.List;

public class TemporaryEvent implements Event, Updatable, HasEvents {
	
	private ReversibleEvent event;
	private boolean start = false;
	private int ctr = 1;
	
	public TemporaryEvent(ReversibleEvent event) {
		this.event = event;
	}
	
	public TemporaryEvent(ReversibleEvent event,int ctr) {
		this.event = event;
		this.ctr = ctr;
	}
	
	@Override
	public void update() {
		if(start) {
			if(--ctr == 0) {
				start = false;
				event.undo();
			}
		}
	}

	@Override
	public String apply(Player player) {
		start = true;
		return event.apply(player);
	}

	@Override
	public String getName() {
		String msg = "Temporary Event";
		msg += " Ctr = " + ctr;
		return msg;
	}
	
	public List<Event> getEvents() {
		@SuppressWarnings("serial")
		List<Event> events = new ArrayList<Event>(){
	    	{
	    		add(event);
	    	}
	    };
		return events;
	}
}
