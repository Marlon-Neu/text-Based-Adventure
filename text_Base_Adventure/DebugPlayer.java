package text_Base_Adventure;

import java.util.List;
import java.util.ArrayList;

public class DebugPlayer extends Player {
	
	private World world;
	private int debugNumber = -999;
	private Place prevPlace;
	private Place debug;
	private Place debugTele = new Place("Debug Tele");
	private List<Event> events = new ArrayList<Event>();
	private List<Place> places;
	
	public DebugPlayer(String _name, World world) {
		super(_name);
		this.world = world;


		debug = new Place("Debug");
		Road debugRoads[] = {
			new Road("get Events",debug, new DebugEvent("eventList")),
			new Road("list all",debug, new DebugEvent("allList")),
			new Road("get all items",debug, new DebugEvent("allItems")),
			new Road("get armor value",debug, new DebugEvent("getArmor")),
			new Road("list Player Items",debug, new DebugEvent("listItems")),
			new Road("teleport To",debugTele),
			new Road("go Back",prevPlace)
		};
		debug.setRoads(debugRoads);
	}
	
	public boolean makeChoice(int number, Road[] roads) {
    	text = "";
    	if (number == debugNumber) {
    		if(this.where() != debug) {
    			int index = debug.getRoads().length - 1;
    			debug.getRoads()[index].setTarget(this.where());
    		}
    		this.goTo(debug);
    		updateDebug();
    		
    		return true;
    	}
    	if (number > 0 && number <= roads.length) {
            int index = number - 1;

            Road chosenRoad = roads[index];
            text += "Ihre Wahl: " + chosenRoad + "\n";
            
            String eventMsg = this.takeRoad(chosenRoad); 
            if(eventMsg != "") {
            	text += eventMsg;
            }
            return true;
        }
    	return false;
    }
	
	private void updateDebug(){
		places = world.getPlaces();
		if(!events.isEmpty()) {
			events.clear();
		}
		for(Place place : places) {
			Road[] roads = place.getRoads();
			if(roads != null) {
				for(Road road: place.getRoads()) {
					events.addAll(road.getEvents());
				}
			}
			events.addAll(place.getEvents());
		}
		updateTele();
	}
	
	private String getAll(){
		String msg = "";
		for(Place place : places) {
			msg += "\n" + place.getName() + " : ";
			msg += "\n Events : ";
			msg += getEventNames(place.getEvents(),1);
			Road[] roads = place.getRoads();
			if(roads != null) {
				msg += "\n Roads : ";
				for(Road road: place.getRoads()) {
					msg += "\n\t" + road.getDescription() + " : ";
					msg += "\n\t Events : ";
					msg += getEventNames(road.getEvents(),2);
				}
			}
			msg += "\n";
		}
		return msg;
	}
	
	public String getEventNames() {
		String msg ="";
		msg += getEventNames(events,0);
		return msg;
	}
	
	public String getEventNames(List<Event> events,int num) {
		String msg ="";
		for(Event event:events) {
			msg += "\n";
			for(int i=0;i<num;i++) {
				msg +="\t";
			}
			msg += event.getName();
			if(event instanceof HasEvents) {
				msg +=getEventNames(((HasEvents) event).getEvents(),num+1);
			}
		}
		return msg;
	}
	
	public String getAllItems() {
		for(Item item:world.getItems()) {
			this.getItem(item);
		}
	    
	    return "Gotten all Items";
	}
	
	public String debug(String choice) {
		
		String msg = "";
		switch(choice) {
		case "eventList":
			msg = getEventNames();
			break;
		case "allList":
			msg = getAll();
			break;
		case "allItems":
			msg = getAllItems();
			break;
		case "listItems":
			msg = listAllItems();
			break;
		case "getArmor":
			msg = "Armor = "+ this.getArmor();
			break;
		default:
		}
		return msg;
	}
	
	private void updateTele() {
		Road[] roadsTele = new Road[places.size()];
		int index = 0;
		for(Place place : places) {
			roadsTele[index++] = new Road(place.getName(),place);
		}
		debugTele.setRoads(roadsTele);
	}
	
	public String listAllItems() {
		String msg = "";
		for(Item item: inventory.listItems() ) {
			msg += "\""+item.getName()+"\",  ";
		}
		msg += inventory.getInventoryStatus();
	    
	    return msg;
	}

}
