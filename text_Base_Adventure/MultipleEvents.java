package text_Base_Adventure;

import java.util.List;
import java.util.ArrayList;

public class MultipleEvents implements Event,HasEvents {
	
	private List<Event> events = new ArrayList<Event>();
	
    public MultipleEvents() {
    }
    
    public MultipleEvents(List<Event> events) {
    	this.events = events;
    }
	
	public String apply(Player player) {
		String msg = "";
		boolean firstNEEvent = true;
		if(!events.isEmpty()){
			for(Event event:events) {
				String toAdd = event.apply(player);
				if (!toAdd.equals("")) {
	    			if(!firstNEEvent) {
	    				msg += "\n";
	    			}
	    			firstNEEvent = false;
	    		}
				msg += toAdd;
			}
		}
		return msg;
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
	
	public void removeEvent(Event event) {
		events.remove(event);
	}
	
	public String getName() {
		String msg = "Multiple Events";
		return msg;
	}

	@Override
	public List<Event> getEvents() {
		return events;
	}
}
