package text_Base_Adventure;

public class PercentCurrentEvent implements Event {

	private double probability;
	private String text;
	private int lifePercent;
	
    public PercentCurrentEvent(double probability, String text, int lifePercent) {
    	this.probability = probability;
    	this.text = text;
    	this.lifePercent = lifePercent;
    }

    public String apply(Player player) {
    	if(probability > Math.random()) {	
	    	player.updateHP((lifePercent*player.getHP())/100);
	    	return text;
    	}
    	return "";
    }
    
    public String getName() {
		String msg = "Percent Current Event : ";
		msg += " Pb = " + probability;
    	msg += " Txt = \"" + text +"\"";
    	msg += " LP = " + lifePercent;
		return msg;
	}
}
