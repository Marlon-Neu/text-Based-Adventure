package text_Base_Adventure;

public class ItemRemoveEvent extends ItemEvent {
	
	protected static String type = "Remove";  

	public ItemRemoveEvent(double probability, String text, Item item) {
		super(probability, text, item);
	}

	public String apply(Player player) {
    	if(probability > Math.random()) {
    		String msg = text;
	    	msg += "\n" + item.getString();
	    	msg += "\n" + player.removeItem(item);
	    	msg += "\n" + player.getInventoryStatus();
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
