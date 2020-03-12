/**
 * This file is one of the classes in the solution to the 
 * CAB302 2017 pair assignment
 * 
 */


package Delivery;


import java.util.ArrayList;
import Stock.Item;

/**
 * The Ordinary Truck class, like Refrigerated, implements  
 * truck with all of the relevant functionality for it's own trucks
 * 
 * @author Samuel West - n9709681
 * 
 */

public class OrdinaryTruck extends Truck {		
	private String truckName;
	private ArrayList<Item> inventory;	
	private ArrayList<String> manItemNames;
	private ArrayList<Integer> manItemAmounts;	
	
	/**
	 * This is the constructor of the class which just creates
	 * the inventory of the truck	 
	 *
	 */
	
	public OrdinaryTruck() {
		truckName = "Ordinary";
		inventory = new ArrayList<Item>();
		manItemNames = new ArrayList<String>();
		manItemAmounts = new ArrayList<Integer>();
	}
	
	/**
	 * Returns the name of the truck (for Manifest loading)
	 * 
	 * @return the name of the truck
	 *
	 */
	
	@Override
	public String truckName() {		
		return truckName;
	}		
	
	
	/**
	 * Overrides the Truck getQuantity method which returns how 
	 * many items the truck is storing
	 * 
	 * @return the trucks inventory size
	 *
	 */


	@Override
	public int getCurrentQuantity() {
		return inventory.size();
	}
	
	/**
	 * Adds an item to the truck's inventory (doesn't worry
	 * about quantity or name)
	 *
	 */
	
	@Override
	public void addItem(Item item) {
		inventory.add(item);				
	}
	
	/**
	 * Adds an item to the truck's inventory and also loads in 
	 * the amount / name for the Manifest order info.
	 *
	 */

	@Override
	public void addItem(Item item, int quantity) {
		inventory.add(item);		
		manItemNames.add(item.getName());
		manItemAmounts.add(quantity);
	}
	
	/**
	 * Returns the list of items that are contained within the 
	 * truck's inventory
	 *
	 * @return an ArrayList of the items in the inventory
	 *
	 */

	@Override
	public ArrayList<Item> getCurrentInventory() {		
		return inventory;
	}
	
	/**
	 * Returns the name of an item that given the index of 
	 * that specific item name (mainly used for adding to 
	 * the Manifest)
	 *
	 * @param index for the ArrayList
	 * @return an ArrayList of the item names in the inventory
	 *
	 */
	
	@Override
	public String getItemName(int index) { 
		return manItemNames.get(index);
	}
	
	/**
	 * Returns the amount of an item at a given index provided 
	 * as a parameter (mainly used for adding to the Manifest
	 * also)
	 *
	 * @param index for the ArrayList
	 * @return an ArrayList of the item quantities / amounts in 
	 * the inventory
	 *
	 */
	
	@Override
	public Integer getItemAmount(int index) {		
		return manItemAmounts.get(index);
	}
	
	/**
	 * This method returns the cost of the truck based off of it's contents.
	 * As an ordinary truck's price is based of quantity, it will get this valid 
	 * before calculating price.
	 * 
	 * @return the cost of the truck
	 */
	
	@Override
	public double getCost() {
		return 750.0 + (0.25 * inventorySize());
	}
	
	/**
	 * Returns the size of the inventory based on the current 
	 * quantities being stored for each item in the cargo	 
	 *	 
	 * @return the total quantity of all items in the cargo
	 *
	 */

	@Override
	public double inventorySize() {
		double sum = 0;
		
		for(int item = 0; item < manItemAmounts.size(); item++){
			sum += manItemAmounts.get(item);
		}
		
		return sum;
	}
}
