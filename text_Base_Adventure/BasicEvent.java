package text_Base_Adventure;

public class BasicEvent implements Event {

	private double probability;
	private String text;
	
    public BasicEvent(double probability, String text) {
    	this.probability = probability;
    	this.text = text;
    }

    public String apply(Player player) {
    	if(probability > Math.random()) {
    		return text;
    	}
    	return "";
    }

	@Override
	public String getName() {
		String msg = "Basic Event : ";
		msg += " Pb = " + probability;
    	msg += " Txt = \"" + text +"\"";
		return msg;
	}
}
