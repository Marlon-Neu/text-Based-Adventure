package text_Base_Adventure;

public class ChangeTargetEvent implements ReversibleEvent {

	private double probability;
	private String text;
	private int lifeDelta;
	private Road road;
	private Place target;
	private Place oldTarget;
	private String newDescription = null;
	
	public ChangeTargetEvent(double probability, String text, int lifeDelta,Road road, Place target) {
    	this.probability = probability;
    	this.text = text;
    	this.lifeDelta = lifeDelta;
    	this.road = road;
    	this.oldTarget = road.getTarget();
    	this.target = target;
    }
	
    public ChangeTargetEvent(double probability, String text, int lifeDelta,Road road, Place target, String newDescription) {
    	this.probability = probability;
    	this.text = text;
    	this.lifeDelta = lifeDelta;
    	this.road = road;
    	this.oldTarget = road.getTarget();
    	this.target = target;
    	this.newDescription = newDescription;
    }
    
    public String apply(Player player) {
    	if(probability > Math.random()) {	
	    	player.updateHP(lifeDelta);
	    	road.setTarget(target);
	    	if(newDescription != null) {
	    		road.setDescription(newDescription);
	    	}
	    	return text;
    	}
    	return "";
    }
    
    public String getName() {
		String msg = "Change Target Event : ";
		msg += " Pb = " + probability;
    	msg += " Txt = \"" + text +"\"";
    	msg += " LD = " + lifeDelta;
    	msg += " Rd = \"" + road.getDescription() +"\"";
    	msg += " Tgt = \"" + target.getName() +"\"";
    	msg += " NwD = \"" + newDescription +"\"";
    	
		return msg;
	}

	@Override
	public void undo() {
		road.setTarget(oldTarget);
	}
}
