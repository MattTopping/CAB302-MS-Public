package Stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import specExceptions.ItemDoesNotExistException;
import specExceptions.NoItemDuplicatesException;

/**
 * 
 * Stock extends <code>HashMap<T></code>. This is used so that stock can use the collection type and 
 * method contained within HashMap. This will allow the storage of Items and their associated quantities.
 * 
 * Stock stores Item's and there associated quantities. This allows a reorder method to developed to see
 * what needs to be sent to manifest to be reordered
 * 
 * @author Matthew Topping
 *
 */

//No Serialization not required
@SuppressWarnings("serial")

public class Stock extends HashMap<Item, Integer> {

	/**
	 * This method is used to enter an item into the stock collection for the first time. 
	 * Once this is done the other methods will be able to be used on the item to manipulate it's
	 * associated qualities
	 * 
	 * @param item the Item that is stored in the inventory 
	 * @param quantity the amount of the item that is currently in the inventory 
	 * 
	 * @throws NoItemDuplicatesException to avoid having quantity stores in multiple locations
	 * an item will not be able to be added twice in stock. This exception is thrown if this is attempted
	 */
	
	public void initialise(Item item) throws NoItemDuplicatesException {
		if(containsKey(item) != true) {
			put(item, 0);
		} else {
			throw new NoItemDuplicatesException("This item has already been entered");
		}
	}
	
	
	/**
	 * This method is used to increase the quantity of an item held in the stock.
	 * 
	 * @param itemName the String name of the item that's quantity wants to be increased 
	 * @param quantity the amount that the quantity is to be increased by 
	 * 
	 * @throws ItemDoesNotExistException this is thrown if the item searched for does not exist in the stock 
	 */
	
	public void add(String itemName, int quantity) throws ItemDoesNotExistException{
		replace(itemRetriever(itemName), currentQuantity(itemName) + quantity);
	}	
	
	
	/**
	 * This method is used to decrease the quantity of an item within the stock 
	 * 
	 * @param itemName the String name of the item that's quantity wants to be decreased
	 * @param quantity the amount that the quantity is to be decreased by 
	 * 
	 * @throws ItemDoesNotExistException this is thrown if the item searched for does not exist in the stock
	 */
	
	public void remove(String itemName, int quantity) throws ItemDoesNotExistException {
		replace(itemRetriever(itemName), currentQuantity(itemName) - quantity);
	}
	
	/**
	 * This method allows a search for an item to see it's current quantity within the stock 
	 * 
	 * @param item the String name of the item that is having it's quantity retrieved
	 * 
	 * @return the current quantity stored of the item searched for 
	 * 
	 * @throws ItemDoesNotExistException this is thrown if the item searched for does not exist in the stock
	 */
	
	public int currentQuantity(String item) throws ItemDoesNotExistException {
		return get(itemRetriever(item)).intValue();
	}
	
	
	/**
	 * This method will remove an item from the stock. This will also remove it's associated
	 * quantity
	 * 
	 * @param item the String name of the item that wants to removed from the stock
	 * 
	 * @throws ItemDoesNotExistException this is thrown if the item searched for does not exist in the stock
	 */
	
	public void discontinue(String item) throws ItemDoesNotExistException {
		remove(itemRetriever(item));
	}
	
	
	/**
	 * This method retrieves the Item object from a string search. This will then
	 * allow  the internal information of an item to be used as it will be return.
	 * 
	 * @param itemName the String name of the item that is being fetched
	 * @return Item returns the item that was search for by ut's string name
	 * 
	 * @throws ItemDoesNotExistException this is thrown if the item searched for does not exist in the stock
	 */
	
	public Item itemRetriever(String itemName) throws ItemDoesNotExistException {
		
		// to get the Item objects, keys must be called. This obtains keys rather than their associated values 
		Set<Item> currItems = new HashSet<Item>();
		currItems = keySet();			
		ArrayList<Item> currItemList = new ArrayList<Item>();
		currItemList.addAll(currItems);						
				
		for(int i = 0; i< currItems.size(); i++) {
			Item item = currItemList.get(i);			
			if(itemName.equals(item.toString())) {				
				return item;
			}						
		}		
		throw new ItemDoesNotExistException(itemName + " was not found");		
				
	}
	
	/**
	 * This method searches the current inventory and works out whether there are 
	 * any quantities that are below the reorder point for that associated item. If 
	 * there are item's that fit this requirement, then they are added to a list which
	 * is returned. This can then be used by other classes such as Manifest to produce 
	 * a work order
	 * 
	 * @return ArrayList<Item> returns a list of items that need reordering 
	 * @throws ItemDoesNotExistException this is thrown if the item searched for does not exist in the stock. Note
	 * that this is unlikely in this method however as it can be thrown by other methods used where user input 
	 * can occur it is required 
	 */
	public ArrayList<Item> reorder() throws ItemDoesNotExistException  {
		ArrayList<Item> itemsToReorder = new ArrayList<Item>();
		
		Set<Item> currItems = new HashSet<Item>();
		currItems = keySet();
		
		for(Item item: currItems) {
			int reorderPoint = item.getReorderPoint();
			int quantity = currentQuantity(item.toString());
			
			if(quantity < reorderPoint) {
				itemsToReorder.add(item);
			}
		}
		return itemsToReorder;
	}
	
}
