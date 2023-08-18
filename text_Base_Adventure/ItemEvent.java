package text_Base_Adventure;

public class ItemEvent implements Event {
	
	protected double probability;
	protected String text;
	protected Item item;
	protected String type = "Basic";  
	
    public ItemEvent(double probability, String text, Item item) {
    	this.probability = probability;
    	this.text = text;
    	this.item = item;
    }

    public String apply(Player player) {
    	if(probability > Math.random()) {
    		String msg = text;
	    	msg += "\n" + item.getString();
	    	return msg;
    	}
    	return "";
    }

    public String getName() {
		String msg = "Item " + type + " Event : ";
		msg += " Pb = " + probability;
    	msg += " Txt = \"" + text +"\"";
    	msg += " Itm = \"" + item.getName() +"\"";
		return msg;
	}
}
