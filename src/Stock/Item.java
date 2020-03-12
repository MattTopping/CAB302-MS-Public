package Stock;

 /**
  * The Item class is an object used to hold properties 
  * about an Item that is stored within the stock.
  * 
  * It is important to note that the toString method
  * have been overridden to allow comparisons.
  * More on this below.
  * 
  * @author Matthew Topping
  * 
  */

public class Item {
	
	private String itemName;
	private double manufacturingPrice;
	private double sellPrice;
	private int reorderPoint;
	private int reorderAmount;
	private Double storageTemp;
	
	
	/**
	 * This method constructs the Item object assignment the objects properties 
	 * to it's appropriate private variables within the class
	 * 
	 * @param name add a name to call the item. This will be used as the search key
	 * @param manfacturingPrice add the cost to produce the item
	 * @param sellPrice add the price at which the item will be sold
	 * @param reorderPoint add an amount at which you wish to have this item reordered
	 * @param reorderAmount add the amount of items that will be restocked when a reorder is called
	 */
	
	//call other constructor 
	public Item (String name, double manufacturingPrice, double sellPrice, int reorderPoint, int reorderAmount) {
		itemName = name;
		this.manufacturingPrice = manufacturingPrice;
		this.sellPrice = sellPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.storageTemp = null;
	}
	
	
	/**
	 * This method constructs the Item in the same way as above but also adds an item storage temperature.
	 * This is used if the item is stored in a refrigerated unit.
	 * 
	 * @param storageTemp this is a double that will hold the storage temperature of the object
	 */
	
	public Item (String name, double manufacturingPrice, double sellPrice, int reorderPoint, int reorderAmount, double storageTemp) {
		itemName = name;
		this.manufacturingPrice = manufacturingPrice;
		this.sellPrice = sellPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.storageTemp = storageTemp;
	}
	
	
	/**
	 * Calling this method will return the name that was assigned to an item during construction
	 * 
	 * @return the item's name
	 */
	
	public String getName() {
		return itemName;
	}
	
	
	/**
	 * Calling this method will return the manufacturing cost assigned to an item during construction
	 * 
	 * @return the item's manufacturing cost
	 */
	
	public double getManCost() {
		return manufacturingPrice;
	}
	
	
	/**
	 * Calling this method will return the selling price assigned to an item during construction 
	 * 
	 * @return the item's selling price
	 */
	
	public double getStoreCost() {
		return sellPrice;
	}
	
	
	/**
	 * Calling this method will return the reorder point that was assigned to an item during 
	 * construction as an integer 
	 * 
	 * @return the item's reorder point
	 */
	
	public int getReorderPoint() {
		return reorderPoint;
	}
	
	
	/**
	 * Calling this method will return the amount of item's that will be ordered in when 
	 * the item's quantity drops below the reorder point
	 * 
	 * @return the item's reorder amount 
	 */
	
	public int getReorderAmount() {
		return reorderAmount;
	}
	
	
	/**
	 * Calling this method will return the degree (in C) that the item will need to be stored at
	 * 
	 * @return the item's storage temperature 
	 */
	
	public Double getTemp() {
		return storageTemp;
	}
	
	
	/**
	 * The toString method has been overridden so that the item name will be returned 
	 * will be called. This will be used to compare items (see override equals)
	 */
	
	@Override
	public String toString() {
		return itemName;
	}
}
