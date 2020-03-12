/**
 * This JUnit file is one of the test classes in the solution 
 * to the CAB302 2017 pair assignment
 * 
 */


package GUI;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * The tests that are run for the store class to ensure that it
 * behaves as it should
 * 
 * @author Samuel West - n9709681  
 *
 */

public class StoreTest {	
	Store location1;
	Store location2;
		
	@Before
	public void setup() throws NoSuchFieldException, IllegalAccessException {	
		location1 = new Store();
		location2 = new Store();
        location1.clear();
        location2.clear();
	}
	
	/**
	 * Test 0 : Testing that the name of the store is setting correctly
	 * 
	 * Requires: getName
	 *
	 */
	
	@Test
	public void testStoreName() {		
		assertEquals(location1.getName(), "SuperMart");
	}
	
	/**
	 * Test 1 : Testing that when two stores are declared, they both have 
	 * the same name due to the singleton pattern 
	 * 
	 * Requires: getName
	 * 
	 */
	
	@Test
	public void testTwoStoreName() {
		@SuppressWarnings("unused")
		Store location1 = Store.getInstance();
		Store location2 = Store.getInstance();
		assertEquals(location2.getName(), "SuperMart");
	}
	
	/**
	 * Test 2 : Testing that the capital of the store is setting correctly
	 * 
	 * Requires: getCapital
	 * 
	 */
		
	@Test
	public void testStoreCapital() {
		Store location1 = Store.getInstance();
		assertEquals(location1.getCapital(), 100000, 0);
	}
	
	/**
	 * Test 3 : Testing that when two stores are declared, they both have 
	 * the same capital due to the singleton pattern 
	 * 
	 * Requires: getCapital
	 * 
	 */
	
	@Test
	public void testTwoStoreCapital() {
		@SuppressWarnings("unused")
		Store location1 = Store.getInstance();
		Store location2 = Store.getInstance();
		assertEquals(location2.getCapital(), 100000, 0);
	}
	
	/**
	 * Test 4 : Testing that after the store has been created, capital 
	 * can be added
	 * 
	 * Requires: addCapital, getCapital
	 * 
	 */

	@Test
	public void testAddCapital() {
		Store location1 = Store.getInstance();
		location1.addCapital(50);
		assertEquals(location1.getCapital(), 100050, 0);
	}
	
	/**
	 * Test 5 : Testing that when two stores are declared and capital
	 * is added to one of them, it changes for the other due to the 
	 * singleton pattern
	 * 
	 * Requires: addCapital, getCapital
	 * 
	 */
	
	@Test
	public void testTwoAddCapital() {
		Store location1 = Store.getInstance();
		Store location2 = Store.getInstance();
		location1.addCapital(50);
		assertEquals(location2.getCapital(), 100050, 0);
	}
	
	/**
	 * Test 6 : Testing that after the store has been created, capital 
	 * can be decreased
	 * 
	 * Requires: decCapital, getCapital
	 * 
	 */
	
	@Test
	public void testDecreaseCapital() {
		Store location1 = Store.getInstance();
		location1.decCapital(50);
		assertEquals(location1.getCapital(), 99950, 0);
	}
	
	/**
	 * Test 7 : Testing that when two stores are declared and capital
	 * is decreased from one of them, it changes for the other due to 
	 * the singleton pattern
	 * 
	 * Requires: decCapital, getCapital
	 * 
	 */
	
	@Test
	public void testTwoDecreaseCapital() {
		Store location1 = Store.getInstance();
		Store location2 = Store.getInstance();
		location1.decCapital(50);
		assertEquals(location2.getCapital(), 99950, 0);
	}	
	
	/**
	 * Test 8 : Testing adding a branch to the store
	 * 
	 * Requires: addBranchName, getName
	 * 
	 */
	
	@Test
	public void testAddBranchName() {
		Store location1 = Store.getInstance();
		location1.addBranchName("Central");		
		assertEquals(location1.getName(), "SuperMart Central");
	}
}
