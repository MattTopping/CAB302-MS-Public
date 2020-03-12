package GUI;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import Stock.Item;
import Stock.Stock;
import specExceptions.CSVFormatException;
import specExceptions.ItemDoesNotExistException;
import specExceptions.CSVReadFileException;
import specExceptions.DeliveryException;
import specExceptions.NoItemDuplicatesException;
import CSV_Funcs.*;
import Delivery.*;

/**
 * This class will create a representation of a store. It extends <code> Read </code>
 * which allows the Singleton pattern to be implemented. This is important so that
 * within each program there can only be 1 store. As there will allows be the 1
 * instance, the stores capital and stock will only occur once avoiding duplication.
 * 
 * This class is initialised with an empty inventory and a starting capital of $100000.
 * The starting name is defaulted to "SuperMart"
 * 
 * @author Matthew Topping
 *
 */
public class Store extends Read {
	/*  An object for representing the store itself. You are required to
	 * use the singleton pattern for this class. The store must have at least the
	 * following properties:
     * – Capital.
	 * – Inventory.
	 * – Name 
	 * 
	 * https://www.javaworld.com/article/2073352/core-java/simply-singleton.html << Singleton class */
	private Stock inventory;
	private double capital;
	private String name;
	private Item item;	
	
	
	/**
	 * Constructing the store to have a new stock (which is empty), along with the 
	 * initial capital of $100000 and the default name "Supermart".
	 */
	protected Store() {
		inventory = new Stock();
		capital = 100000;
		name = "SuperMart";
		// Does this need to be specialized?
	}
	
	
	/**
	 * This method is key to the Singleton pattern. This method will initialise the 
	 * object for the first time .
	 */
	private static class StoreHolder{
		private static Store INSTANCE = new Store();
	}
	
	public void clear() {
		StoreHolder.INSTANCE = new Store();
	}
	
	
	/**
	 * This method return the current store object. This allows the Store to have the same state
	 * across multiple files 
	 * 
	 * @return Store Instance
	 */
	public static Store getInstance() {
		return StoreHolder.INSTANCE;
	}
	
	/**
	 * This is a get method to obtain the store's current name. This name can be edited (See addBranchName)
	 *  
	 * @return the store's name
	 */

	public String getName() {
		return name;
	}
	
	
	/**
	 * This method will extend the initialised name to specify the current branch. This 
	 * will leave the name as SuperMart ... (where ... is the name entered in the parameter)
	 * 
	 * @param newName input the name of the branch for this SuperMart Store.
	 */
	public void addBranchName(String newName) {
		name = "SuperMart " + newName;
	}
	
	
	/**
	 * This is a get method to obtain the store's current capital.
	 * 
	 * @return the store's current capital
	 */
	public double getCapital() {
		return capital;
	}
	
	
	/**
	 * This is a set method to increase the capital of the store. This is most likely to be 
	 * used in conjunction with a load function of some description when an item is sold 
	 * from the inventory 
	 * 
	 * @param moneyAmount the amount of money you want to increase the capital by
	 */
	public void addCapital(double moneyAmount) {
		capital += moneyAmount;
	}
	
	
	/**
	 * This is a set method to decrease the capital of the store. This is most likely to be 
	 * used in conjunction with a load function of some description when an item is added 
	 * into the inventory 
	 * 
	 * @param moneyAmount the amount of money you want to decrease the capital by
	 */
	public void decCapital(double moneyAmount) {
		capital -= moneyAmount;
	}
	
	
	/**
	 * This is a get method that will return the current inventory of the store. It will be 
	 * passed as a stock object.
	 * 
	 * @return the current stock 
	 */
	public Stock getCurrInventory() {
		return inventory;
	}
	
	/**
	 * Used at the start of running the program to initialise 
	 * the items within the store's inventory by reading a CSV
	 * file containing all of the items and their properties
	 * 
	 * @author Samuel West - n9709681 
	 * 
	 * @throws CSVFormatException - if the line that is being read 
	 * doesn't conform to the required layout of the program
	 * @throws NoItemDuplicatesException - thrown if the current item 
	 * being instantiated already exists	 
	 * @throws CSVReadFileException - if there is an error with reading
	 * the items from the CSV file
	 *
	 */
	
	public void initItems(Path file) throws CSVFormatException, NoItemDuplicatesException, CSVReadFileException{
		//Load the CSV files into individual strings
		ArrayList<String> initItems;
		try {
			initItems = (ArrayList<String>) Files.readAllLines(file, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new CSVReadFileException("The items did not load in successfully.");
		}
		
		for(int itemVar = 0; itemVar < initItems.size(); itemVar++){
			try{
				String vars[] = initItems.get(itemVar).split(",");		
				//If the item doesn't had a temp
				if(vars.length == 5){				
					item = new Item(vars[0], convDouble(vars[1]), convDouble(vars[2]),
						convInt(vars[3]), convInt(vars[4]));	
				} else{				
					item = new Item(vars[0], convDouble(vars[1]), convDouble(vars[2]),
						convInt(vars[3]), convInt(vars[4]), convDouble(vars[5]));	
				}
				//Initialise item at a quantity of 0				
				inventory.initialise(item);				
			} catch (NumberFormatException n){
				throw new CSVFormatException("Invalid format provided!");
			}
		}
	}
	
	/**
	 * Loads in a sales log after the week, and with that
	 * updates the inventory accordingly (removing all of the 
	 * items that were sold) and updates the capital accordingly
	 * (adds the store costs of every item sold)
	 * 
	 * @author Samuel West - n9709681
	 * 
	 * @throws CSVReadFileException - if there is an error with reading
	 * the items from the CSV file
	 * @throws ItemDoesNotExistException - thrown when trying to retrieve
	 * an item that doesn't exist
	 * @throws CSVFormatException - if the line that is being read 
	 * doesn't conform to the required layout of the program
	 * @throws DeliveryException - throws if the item being sold doesn't 
	 * have enough quantity to meet the sold items.
	 * 
	 */
	
	public void loadSalesLog(Path file) throws CSVReadFileException, ItemDoesNotExistException, CSVFormatException, DeliveryException {				
		ArrayList<String> salesLogs;		
		
		try {
			salesLogs = (ArrayList<String>) Files.readAllLines(file, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new CSVReadFileException("The items did not load in successfully.");
		}		
		
		System.out.println("Item quantity before: " + inventory.currentQuantity("rice") + " for " + inventory.itemRetriever("rice"));
		System.out.println("Item name: Rice");
		System.out.println("Item quantity: 100");
		inventory.remove("rice", 100);
		System.out.println("Item quantity after: " + inventory.currentQuantity("rice"));
		System.out.println("=======================================================");	
		
		double cost = 0.0;
		for(int item = 0; item < salesLogs.size(); item++){
			try{
				String itemVars[] = salesLogs.get(item).split(",");
				Item currItem = inventory.itemRetriever(itemVars[0]);				
				int quantity = convInt(itemVars[1]);						
				cost += currItem.getStoreCost() * quantity;
				//Removes the items that were sold from the inventory
				
				if(quantity > inventory.currentQuantity(currItem.getName())){
					throw new DeliveryException("The item " + currItem.getName() +  " is empty and "
							+ "can't be removed any more!");
				} else {
					inventory.remove(currItem.getName(), quantity);				
				}
			} catch (NumberFormatException n){
				throw new CSVFormatException("Invalid format provided!");
			}	
		}
		
		//Add the capital earned from the items sold
		addCapital(cost);
	}
	
	/**
	 * Loads in the manifest ordered for the store, and with that
	 * updates the inventory accordingly (adding all of the items
	 * that were ordered) and updates the capital accordingly
	 * (decreases the capital by the cost of the order)
	 * 
	 * @author Samuel West - n9709681
	 * 
	 * @throws CSVFormatException - if the line that is being read 
	 * doesn't conform to the required layout of the program
	 * @throws ItemDoesNotExistException - thrown when trying to retrieve
	 * an item that doesn't exist
	 * @throws CSVReadFileException - if there is an error with reading
	 * the items from the CSV file
	 * @throws DeliveryException - throws in the case that the manifest
	 * can't be afforded by the store
	 * 
	 */
	
	public void loadManifest(Path file) throws CSVFormatException, ItemDoesNotExistException, CSVReadFileException, DeliveryException {				
		ArrayList<String> manifest;	
		ArrayList<String> itemName = new ArrayList<String>();
		ArrayList<Integer> itemQuantity = new ArrayList<Integer>();
		
		try {
			manifest = (ArrayList<String>) Files.readAllLines(file, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new CSVReadFileException("The items did not load in successfully.");
		}			
				
		double cost = 0.00;		
		Truck truck = null;		
						
		for(int line = 0; line < manifest.size(); line++){
			try{
				if(manifest.get(line).startsWith(">")){
					if(truck !=null) {
						cost += truck.getCost();						
					}
					if(manifest.get(line).equals(">Refrigerated")){										
						truck = new RefrigeratedTruck();
					} else{										
						truck = new OrdinaryTruck();
					}
				} else {
					String itemVars[] = manifest.get(line).split(",");
													
					Item item = inventory.itemRetriever(itemVars[0].toString());				
					int quantity = convInt(itemVars[1]);
					
					//Add item to truck for cost calc and update inventory
					truck.addItem(item, quantity);
					itemName.add(item.getName());
					itemQuantity.add(quantity);					
					cost += (item.getManCost() * quantity);	
				}
				
				if(line == manifest.size() - 1) {
					cost += truck.getCost();
					System.out.println(truck.getCost());
				}
			} catch (NumberFormatException n){
				throw new CSVFormatException("Invalid format provided!");
			}
		}
		
		if(cost > getCapital()){
			throw new DeliveryException("The manifest is too expensive to order.");
		} else {
			for(int item = 0; item < itemName.size(); item++){			
				inventory.add(itemName.get(item), itemQuantity.get(item));				
			}
			//Remove the cost of the order from capital
			decCapital(cost);
		}		
	}	
}
