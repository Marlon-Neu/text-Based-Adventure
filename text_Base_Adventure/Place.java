package text_Base_Adventure;

import java.util.ArrayList;
import java.util.List;

public class Place {
	private String name;
    
    private Road[] roads;
    private List<Event> events = new ArrayList<Event>();

    public Place(String name) {
        this.name = name;
    }
    
    public Place(String name, List<Event> events) {
        this.name = name;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setRoads(Road[] roads) {
        this.roads = roads;
    }

    public Road[] getRoads() {
        return roads;
    }

    public String toString() {
        return name;
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
