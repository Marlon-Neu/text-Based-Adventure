package text_Base_Adventure;

public class TransferEvent implements Event {

	private double probability;
	private String text;
	private Place destination;
	
    public TransferEvent(double probability, String text,Place destination) {
    	this.probability = probability;
    	this.text = text;
    	this.destination = destination;
    }

    public String apply(Player player) {
    	if(probability > Math.random()) {
    		return text + "\n" + player.goTo(destination);
    	}
    	return "";
    }
    
    public String getName() {
		String msg = "Transfer Event : ";
		msg += " Pb = " + probability;
    	msg += " Txt = \"" + text +"\"";
    	msg += " Dst = \"" + destination.getName() +"\"";
		return msg;
	}
}
