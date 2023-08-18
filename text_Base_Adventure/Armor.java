package text_Base_Adventure;

public class Armor extends Item {
	
	private int protectionValue;
	private String itemType = "Armor";
	
	public Armor(String name, String description, int protectionValue) {
		super(name, description);
		this.protectionValue = protectionValue;
	}
	
	public int getValue() {
		return protectionValue;
	}
	
	public String getType() {
		return itemType;
	}
}
