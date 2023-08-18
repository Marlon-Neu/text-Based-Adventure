package text_Base_Adventure;

import java.util.List;
import java.util.ArrayList;

public class Road {
	private String description;
    private Place target;
    private List<Event> events = new ArrayList<Event>();

    public Road(String description, Place target) {
        this.description = description;
        this.target = target;
    }
    
    public Road(String description, Place target, List<Event> events) {
        this.description = description;
        this.target = target;
        this.events = events;
    }
    
    public Road(String description, Place target, Event event) {
        this.description = description;
        this.target = target;
        this.events.add(event);
    }

    public String getDescription() {
        return description;
    }

    public Place getTarget() {
        return target;
    }
    
    public void setTarget(Place target) {
    	this.target = target;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }

    public String toString() {
        return description + " -> " + target;
    }
    
    public void setEvents(List<Event> events) {
    	this.events = events;
    }
    
    public void addEvent(Event event) {
    	events.add(event);
    }
    
    public String checkEvents(Player player) {
    	String eventText = "";
    	for(Event event : events) {
    		eventText = event.apply(player);
    		if(eventText != "") {
    			return eventText;
    		}
    	}
    	return eventText;
    }
    
    public List<Event> getEvents(){
    	return events;
    }
}
