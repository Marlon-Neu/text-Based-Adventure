package text_Base_Adventure;

import java.util.ArrayList;
import java.util.List;

public class ItemCheckEvent extends ItemEvent implements HasEvents {
	
	private Event eventPass;
	private Event eventFail;
	protected static String type = "Check";  
	
	public ItemCheckEvent(double probability, String text, Item item,Event eventPass,Event eventFail) {
		super(probability, text, item);
		this.eventPass = eventPass;
		this.eventFail = eventFail;
	}

	public String apply(Player player) {
    	if(probability > Math.random()) {
    		String msg = text;
    		if (msg != "") {
    			msg += "\n";
    		}
    		if(player.hasItem(item)){
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
			String msg = "Item " + type + " Event : ";
			msg += " Pb = " + probability;
	    	msg += " Txt = \"" + text +"\"";
	    	msg += " Itm = \"" + item.getName() +"\"";
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
