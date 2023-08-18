package text_Base_Adventure;

import java.util.ArrayList;
import java.util.List;

public class CounterObserverEvent implements Event,HasEvents {
	
	private CounterEvent ctrEvent;
	private String text;
	private Event eventToTrigger;
	
	public CounterObserverEvent(String text, CounterEvent ctrEvent){
		this.text = text;
		this.ctrEvent = ctrEvent;
		this.eventToTrigger = ctrEvent.getEventToTrigger();
	}
	
	public CounterObserverEvent(String text, CounterEvent ctrEvent,Event eventToTrigger){
		this.text = text;
		this.ctrEvent = ctrEvent;
		this.eventToTrigger = eventToTrigger;
	}
	
	public String apply(Player player) {
		int ctr = ctrEvent.getCtr();
		if(ctr > 0) {
			if(!text.equals("")) {
				return String.format(text, ctr);
			}
		}
		else {
			return eventToTrigger.apply(player);
		}
		
		return "";
	}
	
	public String getName() {
		String msg = "Counter Observer Event : ";
    	msg += " Txt = \"" + text +"\"";
		return msg;
	}

	@Override
	public List<Event> getEvents() {
		@SuppressWarnings("serial")
		List<Event> events = new ArrayList<Event>(){
	    	{
	    		add(eventToTrigger);
	    	}
	    };
		return events;
	}

}
