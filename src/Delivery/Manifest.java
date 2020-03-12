/**
 * This file is one of the classes in the solution to the 
 * CAB302 2017 pair assignment
 * 
 */


package Delivery;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import GUI.Store;
import Stock.*;
import specExceptions.*;

/**
 * The Manifest class loads in what items need to be reordered,
 * places them all in their respective truck ans exports them
 * to a CSV file.
 * 
 * @author Samuel West - n9709681
 * 
 */

public class Manifest {
	private ArrayList<Truck> trucks;
	private ArrayList<Item> cargo;	
	private ArrayList<Item> ordCargo;
	private ArrayList<Double> temps;
	private PrintWriter pw;
	private Store store;
	private Stock stock;	
	
	/**
	 * This is the constructor of the class which initialised 
	 * the trucks Arraylist for the order, creates a stock and
	 * orders the cargo list to have the lowest temp first	
	 * 		 
	 * @throws ItemDoesNotExistException if when the stock does
	 * a recall there is an item that doesn't exist	 
	 * @throws DeliveryException 
	 *
	 */
	
	public Manifest() throws ItemDoesNotExistException, DeliveryException   {
		try{
			trucks = new ArrayList<Truck>();		
			//Gets a reference to the store
			store = Store.getInstance();
			//Gets the inventory of the store
			stock = store.getCurrInventory();
			//Retrieves items that need reordering
			cargo = stock.reorder();
			ordCargo = new ArrayList<Item>();
			temps = new ArrayList<Double>();
			cargoSort();	
		} catch (IndexOutOfBoundsException e){
			throw new DeliveryException("There is no cargo to reorder!");
		}
	}
	
	public void itemTempsOrdered(){				
		for(Item item: cargo){
			if(item.getTemp() != null){
				temps.add(item.getTemp());
			}
		}
				
	    Collections.sort(temps);		    
	    	    
	    for(int temp = 0; temp < temps.size(); temp++){		
	    	for(int item = 0; item < cargo.size(); item++){
				if(cargo.get(item).getTemp() == temps.get(temp)){
					ordCargo.add(cargo.get(item));				
				} 
			}
	    }			    
	}
	
	/**
	 * Cycles through the cargo and checks what item is the coldest. If 
	 * it is the coldest, make it the first item in the truck, else keep
	 * going until it finds a warmer item. Any item without a temp is 
	 * unordered at the end. (Used to fill trucks in most efficient manner)
	 * 
	 * @throws DeliveryException 
	 *
	 */
	
	public void cargoSort() throws DeliveryException{
		itemTempsOrdered();		
		for(Item item: cargo){
	    	if(item.getTemp() == null){
	    		ordCargo.add(item);
	    	}
	    }					
		
		truckOrder();
	}
	
	/**
	 * Check for the Manifest loading that looks to see whether  
	 * another item can fit in the cargo (with it's current quantity)	
	 *  
	 * @param currCapacity - the current capacity of the truck 
	 * @param quantity - the quantity of the item being added into the truck
	 * @param maxCapacity - the maximum capacity of the truck
	 *
	 * @return true if the item can fit in the cargo and false if it 
	 * can't fit
	 *
	 */
		
	public boolean checkNextAdd(int currCapacity, int quantity, int maxCapacity) {
		if((currCapacity + quantity) > maxCapacity){
			return false;
		} else {
			return true;
		}
	}	
	
	/**
	 * Loads the truck order with respect to the current cargo 
	 * required to be loaded. Does the check here whether the truck
	 * is full or not so every truck should be filled to the brim. 
	 * 
	 * @throws DeliveryException - thrown in the case that the manifest 
	 * is too expensive for the store to order. 
	 *
	 */
	
	public void truckOrder() throws DeliveryException {	
		Truck truck = null;	
		int index = 0;
		int maxCap = 0;
		int currCap = 0; 
		int quantity = 0;
		int overflow = 0;	
		
		do{								
			Item currItem = ordCargo.get(index);			
			quantity = currItem.getReorderAmount();
			//If the cargo is still in temp items
			if(index < temps.size()) {					
				maxCap = 800;
				//If there isn't currently a truck instantiated
				if(currCap == 0){			
					if(!trucks.isEmpty()){	
//						cost += truck.getCost();
					}
					truck = new RefrigeratedTruck();
				}
			} else {				
				maxCap = 1000;				
				if(currCap == 0){	
					if(!trucks.isEmpty()){	
//						cost += truck.getCost();
					}
					truck = new OrdinaryTruck();
				}
			}
			
			//If the truck can fit the current item			
			if(checkNextAdd(currCap, quantity, maxCap)){
				//If there is overflow from the item (all didn't fit in one truck)
				if(overflow > 0){ 			
					truck.addItem(currItem, overflow);						
					currCap += overflow;
					overflow = 0; 
				} else if(overflow == 0) {					
					truck.addItem(currItem, quantity); 						
					currCap += quantity;
				} 
				
				if(index == 23){					
					trucks.add(truck);
				}
				
				//Increment the item
				index++; 
			} else {				
				//Set the overflow
				overflow = (currCap + quantity) - maxCap;				
				int itemPartialQuantity = quantity-overflow;				
				truck.addItem(currItem, itemPartialQuantity);
//				cost += currItem.getManCost() * itemPartialQuantity;
				//Add the truck to the trucks list
				trucks.add(truck);	
				currCap = 0;				
			}						
		} while(index < ordCargo.size());	
		
//		if(cost > store.getCapital()){
//			throw new DeliveryException("The Manifest is too expensive to order.");
//		}
	}
	
	/**
	 * Calculates the cost of all of the trucks 
	 * 
	 * @return the cost of the manifest
	 *
	 */
	
	public double manifestCost(){
		double cost = 0.0;
		
		for(int truck = 0; truck < trucks.size(); truck++){
			Truck currTruck = trucks.get(truck);
			cost += currTruck.getCost();
		}
		
		return cost;
	}
	
	/**
	 * Writes and creates the Manifest CSV file by cycling 
	 * through all of the trucks that are previously called and 
	 * loaded in methods above. 
	 * 
	 * @throws FileNotFoundException - when there is an error in 
	 * generating the file for the manifest
	 * @throws DeliveryException - to write a message when a 
	 * FileNotFoundException occurs
	 *
	 */
	
	public void writeManifest() throws FileNotFoundException, DeliveryException  {		
		try{
			pw = new PrintWriter(new File("manifest.csv"));
			String file = "";
			
			for(int truck = 0; truck < trucks.size(); truck++){	
				Truck currTruck = trucks.get(truck);			
				file += '>' + currTruck.truckName() + '\n'; 
				for(int item = 0; item < currTruck.getCurrentQuantity(); item++){
					String itemName = currTruck.getItemName(item);
					int itemQuantity = currTruck.getItemAmount(item);
					file += itemName + ',' + itemQuantity + '\n'; 					
				}				
			}	
			
			pw.write(file);
			pw.close();		
		} catch (FileNotFoundException f){
			throw new DeliveryException("The manifest was not generated successfully");
		} 
		
	}	
} 