package text_Base_Adventure;

import java.util.ArrayList;
import java.util.List;

public class CounterEvent implements Event,HasEvents {

	private double probability;
	private String text;
	private int ctr;
	private Event eventToTrigger;
	private Event eventWhileCounting = null;
	
    public CounterEvent(double probability, String text,int ctr, Event eventToTrigger) {
    	this.probability = probability;
    	this.text = text;
    	this.ctr = ctr;
    	this.eventToTrigger = eventToTrigger;
    }
    
    public CounterEvent(double probability, String text,int ctr, Event eventToTrigger,Event eventWhileCounting) {
    	this.probability = probability;
    	this.text = text;
    	this.ctr = ctr;
    	this.eventToTrigger = eventToTrigger;
    	this.eventWhileCounting = eventWhileCounting;
    }
    
    public int getCtr() {
    	return ctr;
    }
    
    public Event getEventToTrigger() {
    	return eventToTrigger;
    }

    public String apply(Player player) {
    	if(probability > Math.random()) {	
	    	if(ctr > 0){
	    		ctr--;
	    		if(eventWhileCounting != null) {
	    			return eventWhileCounting.apply(player);
	    		}
	    		return text;
	    	}
	    	else{
	    		return eventToTrigger.apply(player);
	    	}
    	}
    	return "";
    }

    public String getName() {
		String msg = "Counter Event : ";
		msg += " Pb = " + probability;
    	msg += " Txt = \"" + text +"\"";
    	msg += " Ctr = " + ctr;
		return msg;
	}

	@Override
	public List<Event> getEvents() {
		@SuppressWarnings("serial")
		List<Event> events = new ArrayList<Event>(){
	    	{
	    		add(eventToTrigger);
	    		add(eventWhileCounting);
	    	}
	    };
		return events;
	}
}
