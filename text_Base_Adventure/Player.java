package text_Base_Adventure;

public class Player implements Updatable{
	private Place place = new Place("noch nicht im Spiel");
	private final String name;
	private final int hpMax = 240;
	private int hp = hpMax;
	protected Inventory inventory = new Inventory(10);
	protected String text = "";
	
	public Player(String _name) {
		this.name = _name;
	}

    public Place where() {
        return place;
    }

    public String goTo(Place next) {
    	this.place = next;
    	String msg = place.checkEvents(this);
        return msg;
    }
    
    public String takeRoad(Road road) {
    	String msg = road.checkEvents(this);
    	String msg2 = goTo(road.getTarget());
    	if(msg.equals("")) {
    		return msg2;
    	}
    	if(msg2.equals("")) {
    		return msg;
    	}
    	return msg + "\n" + msg2;
    }

    public String toString() {
    	String msg = name +" [";
    	for(int i = 1; i <=10 ; i++) {
    		if(hp >= (hpMax*i)/10) {
    			msg += "#";
    		}
    		else {
    			msg += " ";
    		}
    	}
    	msg += "] " + hp + "/" + hpMax + " , Position: " + place;
        return msg;
    }
    
    public String getName() {
    	return name;
    }
    
    public int getHP() {
    	return hp;
    }
    
    public boolean isAlive() {
    	if(hp <= 0) {
    		return false;
    	}
    	return true;
    }
    
    public void updateHP(int delta) {
    	if (hp+delta < hpMax ) {
    		hp += delta;
    	}
    	else {
    		hp = hpMax;
    	}
    }
    
    public String getItem(Item item) {
    	return inventory.addItem(item);
    }
    
    public String getItem(String name, String description) {
    	return inventory.addItem(name, description);
    }
    
    public String removeItem(String name) {
    	return inventory.removeItem(name);
    }
    
	public String removeItem(Item item) {
		return inventory.removeItem(item.getName());
	}
    
    public String describeItem(String name) {
    	return inventory.getDescription(name);
    }
    
    public String getInventoryStatus() {
    	return inventory.getInventoryStatus();
    }
    
    public boolean hasItem(String item) {
    	return inventory.hasItem(item);
    }
    
    public boolean hasItem(Item item) {
    	return inventory.hasItem(item);
    }
    
    public int getArmor() {
    	return inventory.getStrongestArmorValue();
    }
    
    public boolean makeChoice(int number, Road[] roads) {
    	text = "";
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
    
    public String getText() {
    	return text;
    }
    
    public void update() {
    	
    }
}
