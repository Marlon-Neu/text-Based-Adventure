package text_Base_Adventure;

import java.util.ArrayList;
import java.util.List;

public class LifeCheckEvent implements Event,HasEvents {
	
	private double probability;
	private String text;
	private int lifeCheck;
	
	private Event eventPass;
	private Event eventFail;
	private int reverseFactor = 1;
	
	public LifeCheckEvent(double probability, String text, int lifeCheck, Event eventPass, Event eventFail) {
    	this.probability = probability;
    	this.text = text;
    	this.lifeCheck = lifeCheck;
		this.eventPass = eventPass;
		this.eventFail = eventFail;
    }
	
	public LifeCheckEvent(double probability, String text, int lifeCheck, Event eventPass, Event eventFail,boolean reverse) {
    	this.probability = probability;
    	this.text = text;
    	this.lifeCheck = lifeCheck;
		this.eventPass = eventPass;
		this.eventFail = eventFail;
		if(reverse) {
			reverseFactor = -1;
		}
    }

	public String apply(Player player) {
    	if(probability > Math.random()) {
    		String msg = text;
    		if (msg != "") {
    			msg += "\n";
    		}
    		if(player.getHP()*reverseFactor>=lifeCheck*reverseFactor){
    			msg += eventPass.apply(player);
    		}
    		else {
    			msg += eventFail.apply(player);
    		}
	    	
	    	return msg;
    	}
    	return "";
    }
	
	public String getName() {
		String msg = "Life Check Event : ";
		msg += " Pb = " + probability;
    	msg += " Txt = \"" + text +"\"";
    	msg += " Rvs = \"" + (reverseFactor == 1 ? "false":"true") +"\"";
		return msg;
	}

	@Override
	public List<Event> getEvents() {
		@SuppressWarnings("serial")
		List<Event> events = new ArrayList<Event>(){
	    	{
	    		add(eventPass);
	    		add(eventFail);
	    	}
	    };
		return events;
	}
}
