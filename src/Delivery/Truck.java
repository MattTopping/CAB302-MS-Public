/**
 * This file is one of the classes in the solution to the 
 * CAB302 2017 pair assignment
 * 
 */


package Delivery;


import java.util.ArrayList;
import Stock.Item;

/**
 * The Truck class sets out most of the methods that are further 
 * specified in the other Truck classes that extend this one. 
 * 
 * @author Samuel West - n9709681 
 * 
 */

public abstract class Truck {	
	
	/**
	 * A list of abstract classes that are to be further
	 * implemented in the specific truck classes.
	 *
	 */
	
	abstract public String truckName();	
	abstract public int getCurrentQuantity();	
	abstract public void addItem(Item item);
	abstract public void addItem(Item item, int quantity);
	abstract public ArrayList<Item> getCurrentInventory();
	abstract public String getItemName(int index);
	abstract public Integer getItemAmount(int index);
	abstract public double inventorySize();
	abstract public double getCost();
	
	/**
	 * Returns the coldest item of the truck based
	 * on the current inventory of the truck
	 * 	 
	 * @return the temp if the coldest item
	 * 
	 */
	
	public double coldestItem(){
		ArrayList<Item> cargo = getCurrentInventory();
		double coldestTemp = 10.0;
				
		for(Item currItem: cargo){
			Double currTemp = currItem.getTemp();
			//If the temp is the coldest so far and isn't null
			if(currTemp != null) {
				if(currTemp < coldestTemp) {
					coldestTemp = currTemp;
				}
			}
		}
		
		return coldestTemp;
	}
}
