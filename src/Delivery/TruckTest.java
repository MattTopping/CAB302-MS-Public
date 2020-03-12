package Delivery;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Path;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import Stock.Item;

public class TruckTest {
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
	
	/*Test 1 : Testing the coldest temperature method
	 */
	@Test
	public void testTruckTempSortingAboveZero() {
		Truck truck = new RefrigeratedTruck();
		
		Random rand = new Random();
		double randTemp;
		
		randTemp = rand.nextDouble()*10;
		double currLowest = randTemp;
		
		truck.addItem(new Item("Test 1", 10, 10, 10, 10, randTemp));
		
		randTemp = rand.nextDouble()*10;
		if(randTemp<currLowest) {
			currLowest = randTemp;
		}
		
		truck.addItem(new Item("Test 2", 10, 10, 10, 10, randTemp));
		
		randTemp = rand.nextDouble()*10;
		if(randTemp<currLowest) {
			currLowest = randTemp;
		}
		
		truck.addItem(new Item("Test 3", 10, 10, 10, 10, randTemp));
		
		assertEquals(currLowest, truck.coldestItem(), 0.1);
	}
	
	/*Test 4 : Testing the coldest temperature method
	 */
	@Test
	public void testTruckTempSortingInRange() {
		Truck truck = new RefrigeratedTruck();
		
		Random rand = new Random();
		double randTemp;
		
		randTemp = rand.nextDouble()*30-20;
		double currLowest = randTemp;
		
		truck.addItem(new Item("Test 1", 10, 10, 10, 10, randTemp));
		
		randTemp = rand.nextDouble()*30-20;
		if(randTemp<currLowest) {
			currLowest = randTemp;
		}
		
		truck.addItem(new Item("Test 2", 10, 10, 10, 10, randTemp));
		
		randTemp = rand.nextDouble()*30-20;
		if(randTemp<currLowest) {
			currLowest = randTemp;
		}
		
		truck.addItem(new Item("Test 3", 10, 10, 10, 10, randTemp));
		
		assertEquals(currLowest, truck.coldestItem(), 0.1);
	}
}
