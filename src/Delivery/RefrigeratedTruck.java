/**
 * This file is one of the classes in the solution to the 
 * CAB302 2017 pair assignment
 * 
 */


package Delivery;


import java.util.ArrayList;
import Stock.Item;

/**
 * The Refrigerated Truck class that extends truck which deals
 * with all of the relevant functionality to the specific truck
 * 
 * @author Samuel West - n9709681
 * 
 */

public class RefrigeratedTruck extends Truck{
	private String truckName;
	private ArrayList<Item> inventory;
	private ArrayList<String> manItemNames;
	private ArrayList<Integer> manItemAmounts;
	
	/**
	 * 
	 * This is the constructor of the class which sets the temp 
	 * of the truck through the parameter given
	 *
	 */
	
	public RefrigeratedTruck(){
		truckName = "Refrigerated";
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
	 * This method returns the cost of the truck. As the refrigerated truck's temperature
	 * effect it's price, the method calculates what the lowest current requirement is
	 * and bases the price off that
	 * 
	 * @return the overall cost this truck
	 */
	
	@Override
	public double getCost() {
		double temp = coldestItem();
		return (900.0 + (200.0 * (Math.pow(0.7, (temp/5.0)))));
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
