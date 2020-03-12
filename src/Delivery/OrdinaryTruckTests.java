package Delivery;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.*;

import Stock.Item;

/**
 * 
 * @author Matt
 *
 *This test section outlines the testing for the OrdinaryTruck Object
 *
 **/

public class OrdinaryTruckTests {
	
	/* Test 0 : Declaring object for testing 
     */
	Truck truck;
	
	/* Using @Before Ensure that the object declared has no value before starting
	 */
	@Before
	public void setUpTestTruck(){
		truck = null;
	}
	
	/* Test 1 : Constructing an OrdinaryTruck Object
	 */
	@Test
	public void testTruckConstruction() {
		truck = new OrdinaryTruck();
	}
	
	/* Test 2 : Checking a the Truck's name is assigned correctly
	 */
	@Test
	public void testTruckName() {
		truck = new OrdinaryTruck();
		assertEquals("Ordinary", truck.truckName());
	}
	
	/* Test 3 : Testing a trucks ability to keep track of the number of item type
	 */
	@Test
	public void testCurrentQuanity() {
		truck = new OrdinaryTruck();
		truck.addItem(new Item("Test 1", 10, 10, 10, 10), 10);
		truck.addItem(new Item("Test 2", 20, 20, 20, 20));
		truck.addItem(new Item("Test 3", 30, 30, 30, 30), 30);
		
		assertEquals(3, truck.getCurrentQuantity());
	}
	
	/* Test 4 : Testing a truck adding method
	 */
	@Test
	public void testAddingItem() {
		truck = new OrdinaryTruck();
		boolean testCaseFail = true;
		
		truck.addItem(new Item("Test 1", 10, 10, 10, 10));
		truck.addItem(new Item("Test 2", 20, 20, 20, 20));
		truck.addItem(new Item("Test 3", 30, 30, 30, 30));
		
		ArrayList<Item> inv = truck.getCurrentInventory();
		
		if(inv.get(0).toString().equals("Test 1") &&
				inv.get(1).toString().equals("Test 2") &&
				inv.get(2).toString().equals("Test 3")){
			testCaseFail = false;
		}
		assertEquals(false, testCaseFail);
	}
	
	/* Test 5 : Testing a truck adding method (overloaded)
	 */
	@Test
	public void testAddingItemOverload() {
		truck = new OrdinaryTruck();
		boolean testCaseFail = true;
		
		truck.addItem(new Item("Test 1", 10, 10, 10, 10), 10);
		truck.addItem(new Item("Test 2", 20, 20, 20, 20), 20);
		truck.addItem(new Item("Test 3", 30, 30, 30, 30), 30);
		
		ArrayList<Item> inv = truck.getCurrentInventory();
		
		if(inv.get(0).toString().equals("Test 1") &&
				inv.get(1).toString().equals("Test 2") &&
				inv.get(2).toString().equals("Test 3")){
			testCaseFail = false;
		}
		assertEquals(false, testCaseFail);
	}
	
	/* Test 6 : Testing both truck adding methods
	 */
	@Test
	public void testAddingItemBoth() {
		truck = new OrdinaryTruck();
		boolean testCaseFail = true;
		
		truck.addItem(new Item("Test 1", 10, 10, 10, 10), 10);
		truck.addItem(new Item("Test 2", 20, 20, 20, 20));
		truck.addItem(new Item("Test 3", 30, 30, 30, 30), 30);
		
		ArrayList<Item> inv = truck.getCurrentInventory();
		
		if(inv.get(0).toString().equals("Test 1") &&
				inv.get(1).toString().equals("Test 2") &&
				inv.get(2).toString().equals("Test 3")){
			testCaseFail = false;
		}
		assertEquals(false, testCaseFail);
	}
	
	/* Test 7 : Testing a Truck is adding items with quantity correctly and storing it in it's inventory 
	 */
	@Test
	public void testTruckAddingItemsWithAmounts() {
		boolean testCaseFail = true;
		boolean nameOrdered = false;
		
		truck = new OrdinaryTruck();
		truck.addItem(new Item("Test 1", 10, 10, 10, 10), 10);
		truck.addItem(new Item("Test 2", 20, 20, 20, 20), 20);
		truck.addItem(new Item("Test 3", 30, 30, 30, 30), 30);
		
		ArrayList<Item> inv = truck.getCurrentInventory();
		
		if(inv.get(0).toString().equals("Test 1") &&
				inv.get(1).toString().equals("Test 2") &&
				inv.get(2).toString().equals("Test 3")){
			nameOrdered = true;
		}
		if(truck.getItemAmount(0) == 10 &&
				truck.getItemAmount(1) == 20 &&
				truck.getItemAmount(2) == 30 &&
				nameOrdered == true){
			testCaseFail = false;
		}
		
		assertEquals(false, testCaseFail);
	}
	
	/* Test 8 : Testing that items names are retrieved correctly
	 */
	@Test
	public void testTruckNameFetching() {
		boolean testCaseFail = true;
		
		truck = new OrdinaryTruck();
		truck.addItem(new Item("Test 1", 10, 10, 10, 10), 10);
		truck.addItem(new Item("Test 2", 20, 20, 20, 20), 20);
		truck.addItem(new Item("Test 3", 30, 30, 30, 30), 30);
		
		if(truck.getItemName(0).equals("Test 1") &&
				truck.getItemName(1).equals("Test 2") &&
				truck.getItemName(2).equals("Test 3")){
			testCaseFail = false;
		}
		assertEquals(false, testCaseFail);
	}
	
	/* Test 9 : Testing the trucks ability to total up items
	 */
	
	@Test
	public void testTruckLoadTotal() {
		truck = new OrdinaryTruck();
		Random rand = new Random();
		int currRand = 0;
		int quantTotal = 0;
		
		currRand = rand.nextInt(200);
		quantTotal += currRand;
		truck.addItem(new Item("Test 1", 10, 10, 10, 10), currRand);
		
		currRand = rand.nextInt(200);
		quantTotal += currRand;
		truck.addItem(new Item("Test 2", 20, 20, 20, 20), currRand);
		
		currRand = rand.nextInt(200);
		quantTotal += currRand;
		truck.addItem(new Item("Test 3", 30, 30, 30, 30), currRand);
		
		assertEquals(quantTotal, (int) truck.inventorySize());
	}
	
	/* Test 10 : Testing a OrdinaryTruck's item quantity fetching
	 */
	@Test
	public void testTruckQuantityFetching() {
		boolean testCaseFail = true;
		
		truck = new OrdinaryTruck();
		truck.addItem(new Item("Test 1", 10, 10, 10, 10), 10);
		truck.addItem(new Item("Test 2", 20, 20, 20, 20), 20);
		truck.addItem(new Item("Test 3", 30, 30, 30, 30), 30);
		
		if(truck.getItemAmount(0) == 10 &&
				truck.getItemAmount(1) == 20 &&
				truck.getItemAmount(2) == 30){
			testCaseFail = false;
		}
		assertEquals(false, testCaseFail);
	}
	
	//TODO add get temp test
}

