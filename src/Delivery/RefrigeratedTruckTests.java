package Delivery;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

//import static org.junit.Assert.*;
import org.junit.*;

import Stock.Item;

/**
 * 
 * @author Matt
 *
 *This test section outlines the testing for the RefrigeratedTruck Object
 *
 **/

public class RefrigeratedTruckTests {
	
	/* Test 0 : Declaring object for testing 
     */
	Truck truck;
	File itemProperties = new File("C:/Users/Matt/git/CAB302-Group-Assignment/Sample files/item_properties.csv");
	Path itemPath = itemProperties.toPath();
	
	/* Using @Before Ensure that the object declared has no value before starting
	 */
	@Before
	public void setUpTestTruck(){
		truck = null;
	}
	
	/* Test 1 : Constructing a RefridgeratedTruck Object
	 */
	@Test
	public void testTruckConstruction() {
		truck = new RefrigeratedTruck();
	}
	
	/* Test 2 : Testing a RefridgeratedTruck's is named correctly 
	 */
	@Test
	public void testName() {
		truck = new RefrigeratedTruck();
		assertEquals("Refrigerated", truck.truckName());
	}
	
	/* Test 3 : Testing a RefridgeratedTruck's pricing formula is written out correctly 
	 */
	@Test
	public void testTruckPriceCalc() {
		truck = new RefrigeratedTruck();
		Random rand = new Random();
		double testTemp = rand.nextDouble()*30-20;
		testTemp = Double.valueOf(String.format("%.2f", testTemp));
		truck.addItem(new Item("test", 10, 10, 10, 10, testTemp));
		
		double pwr = testTemp/5;
		double cost = (900 + (200 * Math.pow(0.7,pwr)) );
		
		cost = Double.valueOf(String.format("%.2f", cost));
		
		assertEquals(cost, truck.getCost(), 0.01);
	}
	
	/* Test 4 : Testing a RefridgeratedTruck's ability to identify how many item's it needs to sort
	 */
	@Test
	public void testTruckItemCount() {
		truck = new RefrigeratedTruck();
		truck.addItem(new Item("Test 1", 10, 10, 10, 10, -1.0));
		truck.addItem(new Item("Test 2", 20, 20, 20, 20, -2.0));
		truck.addItem(new Item("Test 3", 30, 30, 30, 30, -3.0));
		
		assertEquals(3, truck.getCurrentQuantity());
	}
	
	/* Test 5 : Testing a RefridgeratedTruck's is adding items correctly and storing it in it's inventory 
	 */
	@Test
	public void testTruckAddingItems() {
		boolean testCaseFail = true;
		
		truck = new RefrigeratedTruck();
		truck.addItem(new Item("Test 1", 10, 10, 10, 10, -1.0));
		truck.addItem(new Item("Test 2", 20, 20, 20, 20, -2.0));
		truck.addItem(new Item("Test 3", 30, 30, 30, 30, -3.0));
		
		ArrayList<Item> inv = truck.getCurrentInventory();
		
		if(inv.get(0).toString().equals("Test 1") &&
				inv.get(1).toString().equals("Test 2") &&
				inv.get(2).toString().equals("Test 3")){
			testCaseFail = false;
		}
		else {}
		assertEquals(false, testCaseFail);
	}
	
	/* Test 6 : Testing a RefridgeratedTruck's is adding items with quantity correctly and storing it in it's inventory 
	 */
	@Test
	public void testTruckAddingItemsWithAmounts() {
		boolean testCaseFail = true;
		boolean nameOrdered = false;
		
		truck = new RefrigeratedTruck();
		truck.addItem(new Item("Test 1", 10, 10, 10, 10, -1.0), 10);
		truck.addItem(new Item("Test 2", 20, 20, 20, 20, -2.0), 20);
		truck.addItem(new Item("Test 3", 30, 30, 30, 30, -3.0), 30);
		
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
		else {}
		
		assertEquals(false, testCaseFail);
	}
	
	/* Test 7 : Testing a RefridgeratedTruck's item name fetching
	 */
	@Test
	public void testTruckNameFetching() {
		boolean testCaseFail = true;
		
		truck = new RefrigeratedTruck();
		truck.addItem(new Item("Test 1", 10, 10, 10, 10, -1.0), 10);
		truck.addItem(new Item("Test 2", 20, 20, 20, 20, -2.0), 20);
		truck.addItem(new Item("Test 3", 30, 30, 30, 30, -3.0), 30);
		
		if(truck.getItemName(0).equals("Test 1") &&
				truck.getItemName(1).equals("Test 2") &&
				truck.getItemName(2).equals("Test 3")){
			testCaseFail = false;
		}
		assertEquals(false, testCaseFail);
	}
	
	/* Test 8 : Testing a RefridgeratedTruck's item quantity fetching
	 */
	@Test
	public void testTruckQuantityFetching() {
		boolean testCaseFail = true;
		
		truck = new RefrigeratedTruck();
		truck.addItem(new Item("Test 1", 10, 10, 10, 10, -1.0), 10);
		truck.addItem(new Item("Test 2", 20, 20, 20, 20, -2.0), 20);
		truck.addItem(new Item("Test 3", 30, 30, 30, 30, -3.0), 30);
		
		if(truck.getItemAmount(0) == 10 &&
				truck.getItemAmount(1) == 20 &&
				truck.getItemAmount(2) == 30){
			testCaseFail = false;
		}
		assertEquals(false, testCaseFail);
	}
	
	/* Test 9 : Testing a RefridgeratedTruck's item quantity totaling
	 */
	@Test
	public void testTruckQuantityTotaling() {
		truck = new RefrigeratedTruck();
		
		Random rand = new Random();
		int currRand = 0;
		int quantTotal = 0;
		
		currRand = rand.nextInt(200);
		quantTotal += currRand;
		truck.addItem(new Item("Test 1", 10, 10, 10, 10, -1.0), currRand);
		
		currRand = rand.nextInt(200);
		quantTotal += currRand;
		truck.addItem(new Item("Test 2", 20, 20, 20, 20, -2.0), currRand);
		
		currRand = rand.nextInt(200);
		quantTotal += currRand;
		truck.addItem(new Item("Test 3", 30, 30, 30, 30, -3.0), currRand);
		
		assertEquals(quantTotal, (int) truck.inventorySize());
	}
}