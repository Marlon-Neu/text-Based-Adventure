package text_Base_Adventure;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class Inventory {
	private HashMap<String,Item> items = new HashMap<String,Item>();
	private HashMap<String,Integer> itemNumber = new HashMap<String,Integer>();
	private final int capacity;
	private int numOfItems = 0;
	private Armor strongestArmor = null;
	
	public Inventory(int capacity) {
		this.capacity = capacity;
	}
	
	public String addItem(String name,String description) {
		String msg = "Inventar voll";
		if(numOfItems < capacity) {
			Item item = new Item(name,description);	
			if(!items.containsKey(name)) {	
				items.put(name,item);
				itemNumber.put(name, 1);
			}
			else {
				itemNumber.replace(name, itemNumber.get(name)+1);
			}
			numOfItems++;
			updateInventory(item);
			msg = name + " zum Inventar hinzugefügt.";
		}
		
		return msg;
	}
	
	public String addItem(Item item) {
		String msg = "Inventar voll";
		String itemName = item.getName();
		if(numOfItems < capacity) {
			if(!items.containsValue(item)) {
				items.put(item.getName(),item);
				itemNumber.put(itemName, 1);
			}
			else {
				itemNumber.replace(itemName, itemNumber.get(itemName)+1);
			}
			numOfItems++;
			updateInventory(item);
			msg = itemName + " zum Inventar hinzugefügt.";
		}
		
		return msg;
	}
	
	public String removeItem(String name) {
		String msg = name +" ist nicht im Inventar";
		
		if(items.containsKey(name)) {
			Item item = items.get(name);
			itemNumber.replace(name, itemNumber.get(name)-1);
			if(itemNumber.get(name) == 0) {
				items.remove(name);
				itemNumber.remove(name);
			}
			numOfItems--;
			updateInventory(item);
			msg = name +" entfernt";
		}
		
		return msg;
	}
	
	private void updateInventory(Item item) {
		switch(item.getType()){
		case "Armor":
			updateStrongestArmor();
			break;
		default:
		}
	}
	
	
	public boolean hasItem(String name) {
		return items.containsKey(name);
	};
	
	public boolean hasItem(Item item) {
		return items.containsValue(item);
	};
	
	public String getDescription(String name){
		String msg = name + "gibt es nicht";
		if(items.containsKey(name)) {
			msg = items.get(name).getString();
		}
		
		return msg;
	}
	
	public String getInventoryStatus() {
		return "Inventory : "+ numOfItems + "/" + capacity;
	}
	
	public boolean hasItemType(String itemType) {
		Iterator<Entry<String,Item>> itemIterator = items.entrySet().iterator();
		while(itemIterator.hasNext()) {
			Entry<String,Item> itemMap = (Entry<String,Item>)itemIterator.next();
			if(itemMap.getValue().getType().equals(itemType)) {
				return true;
			}
		}
		return false;
	}
	
	public Armor getStrongestArmor() {
		Iterator<Entry<String,Item>> itemIterator = items.entrySet().iterator();
		while(itemIterator.hasNext()) {
			Entry<String,Item> itemMap = (Entry<String,Item>)itemIterator.next();
			if(itemMap.getValue() instanceof Armor) {
				Armor armorToCheck = (Armor) itemMap.getValue();
				if(strongestArmor == null) {
					strongestArmor = armorToCheck;
				}
				else {
					if(strongestArmor.getValue() <= armorToCheck.getValue()) {
						strongestArmor = armorToCheck;
					}
				}
			}
		}
		return strongestArmor;
	}
	
	public int getStrongestArmorValue() {
		if(strongestArmor != null) {
			return strongestArmor.getValue();
		}
		return 0;
	}
	
	private void updateStrongestArmor() {
		strongestArmor = getStrongestArmor();
	}
	
	public String getStrongestArmorName() {
		if(strongestArmor != null) {
			return strongestArmor.getName(); 
		}
		return "";
	}
	
	public boolean hasArmor() {
		if(strongestArmor != null) {
			return true;
		}
		return false;
	}
	
	public List<Item> listItems(){
		List<Item> oldItemList = new ArrayList<Item>(items.values());
		List<Item> newItemList = new ArrayList<Item>(items.values());
		for (Item item : oldItemList) {
			String itemName = item.getName();
			int numOfItem = itemNumber.get(itemName);
			if(numOfItem > 1) {
				int itemIndex = newItemList.indexOf(item);
				for(int i = numOfItem;i>1;i--) {
					newItemList.add(itemIndex,item);
				}
			}
		}
		return newItemList;
	}
}
