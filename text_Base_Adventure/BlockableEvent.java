package text_Base_Adventure;

public class BlockableEvent implements Event {
		
	private double probability;
	private String text;
	private int lifeDelta;
	
    public BlockableEvent(double probability, String text, int lifeDelta) {
    	this.probability = probability;
    	this.text = text;
    	this.lifeDelta = lifeDelta;
    }

    public String apply(Player player) {
    	if(probability > Math.random()) {
    		String msg = text;
    		int lifeDelta = this.lifeDelta;
    		if(lifeDelta < 0) {
    			if(player.getArmor() !=0 ) {
    				lifeDelta += player.getArmor();
    				if(lifeDelta > 0) {
    					lifeDelta = 0;
    				}
    				msg+= "\nSchaden um "+player.getArmor()+" blockiert";
    			}
    		}
	    	player.updateHP(lifeDelta);
	    	return msg;
    	}
    	return "";
    }
    
    public String getName() {
		String msg = "Blockable Event : ";
		msg += " Pb = " + probability;
    	msg += " Txt = \"" + text +"\"";
    	msg += " LD = " + lifeDelta;
		return msg;
	}
    
}
