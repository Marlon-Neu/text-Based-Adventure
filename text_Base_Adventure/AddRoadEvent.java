package text_Base_Adventure;

import java.util.Arrays;

public class AddRoadEvent implements Event {
	private double probability;
	private String text;
	private int lifeDelta = 0;
	private Road road;
	private Place origin;
	
	
	public AddRoadEvent(double probability, String text, Place origin,Road road) {
    	this.probability = probability;
    	this.text = text;
    	this.road = road;
    	this.origin = origin;
    }
    
	
	public AddRoadEvent(double probability, String text, int lifeDelta,Place origin,Road road) {
    	this.probability = probability;
    	this.text = text;
    	this.lifeDelta = lifeDelta;
    	this.road = road;
    	this.origin = origin;
    }
	
	
    public String apply(Player player) {
    	if(probability > Math.random()) {	
	    	player.updateHP(lifeDelta);
	    	Road[] oldRoads = origin.getRoads();
	    	Road[] newRoads = Arrays.copyOf(oldRoads, oldRoads.length+1);
	    	newRoads[oldRoads.length] = road;
	    	origin.setRoads(newRoads);
	    	return text;
    	}
    	return "";
    }
    
    public String getName() {
    	String msg = "Add Road Event :";
    	msg += " Pb = " + probability;
    	msg += " Txt = \"" + text +"\"";
    	msg += " Rd = \"" + road.getDescription() +"\"";
    	msg += " Org = \"" + origin.getName() +"\"";
    	return msg;
    }
}
