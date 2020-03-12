/**
 * This JUnit file is one of the test classes in the solution 
 * to the CAB302 2017 pair assignment
 * 
 */


package Stock;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * The tests that are run for the item class to ensure that it
 * behaves as it should
 * 
 * @author Samuel West - n9709681  
 *
 */

public class ItemTest {
	Item testItem;
	Item testItemTemp;
	Item testItemTempTwo;
	
	/**
	 * @Before the tests are run...
	 * Instantiating the items with different values
	 * 
	 */
	
	@Before
	public void setup() {
		testItem = new Item("test", 1.0, 5.0, 10, 50);
		testItemTemp = new Item("test2", 2.0, 6.0, 11, 51, -5.0);
		testItemTempTwo = new Item("test2", 2.0, 6.0, 11, 51, -10.0);
	}	
	
	/**
	 * Test 0 : Testing that the name of the item is setting correctly
	 * 	 
	 * Requires: getName
	 * 
	 */
	
	@Test
	public void testName() {
		assertEquals(testItem.getName(), "test");
	}
	
	/**
	 * Test 1 : Testing that when 2 objects are instantiated, their
	 * individual names are being set accordingly
	 * 
	 * Requires: getName
	 * 
	 */
	
	@Test
	public void testTwoItemNames() {
		int testCase;
		
		if(testItem.getName() != testItemTemp.getName()) testCase = 1;
		else testCase = 0;
		
		assertEquals(1, testCase);
	}	
	
	/**
	 * Test 2 : Testing that the manufacturing cost of the item is 
	 * setting correctly
	 * 
	 * Requires: getManCost
	 * 
	 */
	
	@Test
	public void testManufactureCost() {
		assertEquals(1.0, testItem.getManCost(), 0);
	}
	
	/**
	 * Test 3 : Testing that when 2 objects are instantiated, their
	 * individual manufacturing costs are being set accordingly
	 * 
	 * Requires: getManCost
	 * 
	 */
	
	@Test
	public void testTwoItemManufactureCosts() {
		int testCase;
		
		if(testItem.getManCost() != testItemTemp.getManCost()) testCase = 1;
		else testCase = 0;
		
		assertEquals(1, testCase);
	}
	
	/**
	 * Test 4 : Testing that the store cost of the item is setting
	 * correctly
	 * 
	 * Requires: getStoreCost
	 * 
	 */
	
	@Test
	public void testStoreCost() {
		assertEquals(5.0, testItem.getStoreCost(), 0);
	}
	
	/**
	 * Test 5 : Testing that when 2 objects are instantiated, their
	 * individual store costs are being set accordingly
	 * 
	 * Requires: getStoreCost
	 * 
	 */
	
	@Test
	public void testTwoItemStoreCosts() {
		int testCase;
		
		if(testItem.getStoreCost() != testItemTemp.getStoreCost()) testCase = 1;
		else testCase = 0;
		
		assertEquals(1, testCase);
	}
	
	/**
	 * Test 6 : Testing that the reorder point of the item is setting
	 * correctly
	 * 
	 * Requires: getReorderPoint
	 * 
	 */
	
	@Test
	public void testReorderPoint() {
		assertEquals(10, testItem.getReorderPoint());
	}
	
	/**
	 * Test 7 : Testing that when 2 objects are instantiated, their
	 * individual reorder points are being set accordingly
	 * 
	 * Requires: getReorderPoint
	 * 
	 */
	
	@Test
	public void testTwoItemReorderPoints() {
		int testCase;
		
		if(testItem.getReorderPoint() != testItemTemp.getReorderPoint()) testCase = 1;
		else testCase = 0;
		
		assertEquals(1, testCase);
	}
	
	/**
	 * Test 8 : Testing that the reorder amount of the item is setting
	 * correctly
	 * 
	 * Requires: getReorderAmount
	 * 
	 */
	
	@Test
	public void testReorderAmount() {
		assertEquals(testItem.getReorderAmount(), 50);
	}
	
	/**
	 * Test 9 : Testing that when 2 objects are instantiated, their
	 * individual reorder amounts are being set accordingly
	 * 
	 * Requires: getReorderAmount
	 * 
	 */
	
	@Test
	public void testTwoItemReorderAmounts() {
		int testCase;
		
		if(testItem.getReorderAmount() != testItemTemp.getReorderAmount()) testCase = 1;
		else testCase = 0;
		
		assertEquals(1, testCase);
	}
	
	/**
	 * Test 10 : Testing that the temperature of the item is setting
	 * correctly
	 * 
	 * Requires: getTemp
	 * 
	 */
	
	@Test
	public void testItemTemp() {
		
		assertEquals(-5.0, testItemTemp.getTemp(), 0);
	}	
	
	/**
	 * Test 11 : Testing that when 2 objects are instantiated, their
	 * individual temperatures are being set accordingly
	 * 
	 * Requires: getTemp
	 * 
	 */
	
	@Test
	public void testTwoItemTemps() {		
		int testCase;
		
		if(testItemTemp.getTemp() != testItemTempTwo.getTemp()) testCase = 1;
		else testCase = 0;
		
		assertEquals(1, testCase);
	}	
	
	/**
	 * Test 12 : Testing that the temperature of the item is null if there
	 * isn't a temperature set in the constructor
	 * 
	 * Requires: getTemp
	 * 
	 */		
	
	@Test
	public void testItemNoTemp() {
		assertEquals(null, testItem.getTemp());
	}
	
	/**
	 * Test 13 : Ensuring that the toString method that is being overriden
	 * is working as it should (Name is returning correctly)
	 * 
	 * Requires: override of toString
	 * 
	 */	
	
	@Test
	public void testToStringOverride() {
		assertEquals("test", testItem.toString());
	}
	
	/**
	 * Test 14 : Ensuring that the toString method, when calling the toString
	 * method and not matching, does infact work as it should
	 * 
	 * Requires: override of toString
	 * 
	 */	
	
	@Test
	public void testTwoToStringOverride() {
		assertNotSame("test", testItemTemp.toString());
	}
}
