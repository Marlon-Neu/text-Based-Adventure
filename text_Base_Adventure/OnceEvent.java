package text_Base_Adventure;

import java.util.ArrayList;
import java.util.List;

public class OnceEvent implements Event,HasEvents {

	private double probability;
	private Event event;
	public boolean spent = false;
	public String textEventEmpty = "";
	public String textSpent = "";
	
    public OnceEvent(double probability, Event event) {
    	this.probability = probability;
    	this.event = event;
    }
    
    public OnceEvent(Event event) {
    	this.probability = 1;
    	this.event = event;
    }
    
    public OnceEvent(double probability, Event event, String textEventEmpty) {
    	this.probability = probability;
    	this.event = event;
    	this.textEventEmpty = textEventEmpty;
    }
    
    public OnceEvent(Event event, String textEventEmpty) {
    	this.probability = 1;
    	this.event = event;
    	this.textEventEmpty = textEventEmpty;
    }
    
    public OnceEvent(double probability, Event event, String textEventEmpty, String textSpent) {
    	this.probability = probability;
    	this.event = event;
    	this.textEventEmpty = textEventEmpty;
    	this.textSpent = textSpent;
    }
    
    public OnceEvent(Event event, String textEventEmpty, String textSpent) {
    	this.probability = 1;
    	this.event = event;
    	this.textEventEmpty = textEventEmpty;
    	this.textSpent = textSpent;
    }

    public String apply(Player player) {
    	if(probability > Math.random()) {
    		if(!spent)
    		{
    			String msg = event.apply(player);
    			if(!msg.equals("")) {
    				spent = true;
    			}
    			else {
    				msg = textEventEmpty;
    			}
    			return msg;
    		}
    		return textSpent;
    	}
    	return "";
    }
    
    public String getName() {
		String msg = "Once Event : ";
		msg += " Pb = " + probability;
		msg += " Sts = \"" + (spent ? "Spent":"Not Spent") +"\"";
		msg += " Txt EE= \"" + textEventEmpty +"\"";
		msg += " Txt Sp= \"" + textSpent +"\"";
		return msg;
	}
    

	@Override
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
