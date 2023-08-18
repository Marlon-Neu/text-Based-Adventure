package text_Base_Adventure;

public class Item {
	protected String name;
	protected String description;
	private String itemType = "Basic";
	
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getString() {
		return name + " : " + description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return itemType;
	}
}
