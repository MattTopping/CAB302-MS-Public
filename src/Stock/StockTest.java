/**
 * This JUnit file is one of the test classes in the solution 
 * to the CAB302 2017 pair assignment
 * 
 */


package Stock;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import specExceptions.ItemDoesNotExistException;
import specExceptions.NoItemDuplicatesException;

/**
 * The tests that are run for the stock class to ensure that it
 * behaves as it should
 * 
 * @author Samuel West - n9709681  
 *
 */

public class StockTest {
	Stock stock;
	Item item;
	Item itemTemp;	
	
	/**
	 * @Before the tests are run...
	 * Instantiating the stock object and an item
	 * 
	 */
	
	@Before
	public void setup() {
		stock = new Stock();
		item = new Item("test", 1, 5, 10, 50);
		itemTemp = new Item("test2", 2, 6, 11, 51, 41);
		
	}			
	
	/**
	 * Test 0 : Testing that when two items get's initialised, 
	 * they are both different when called
	 * 
	 * Requires: addItem, getItem
	 * 
	 */
	
	@Test
	public void testInit() throws ItemDoesNotExistException, NoItemDuplicatesException {		
		stock.initialise(item);
		assertEquals(item, stock.itemRetriever("test"));
	}
	
	/**
	 * Test 1 : Testing that when an item get's initialised, 
	 * it is actually going into stock and can be retrieved
	 * 
	 * Requires: initialise, itemRetriever
	 * 
	 */
	
	@Test
	public void testTwoInit() throws ItemDoesNotExistException, NoItemDuplicatesException {		
		stock.initialise(item);
		stock.initialise(itemTemp);
		assertNotEquals(item, stock.itemRetriever("test2"));
	}	
	
	/**
	 * Test 2 : Testing adding some quantity to an item is
	 * storing correctly
	 * 
	 * Requires: initialise, add, toString, currentQuantity
	 * 
	 */
	
	@Test
	public void testAddQuantity() throws ItemDoesNotExistException, NoItemDuplicatesException {
		stock.initialise(item);
		stock.add(item.getName(), 5);
		assertEquals(stock.currentQuantity(item.getName()), 5);
	}
	
	/**
	 * Test 3 : Testing adding some quantity to an item doesn't
	 * add the same quantity to another item as well
	 * 
	 * Requires: initialise, add, toString, currentQuantity
	 * 
	 */
	
	@Test
	public void testAddTwoQuantity() throws ItemDoesNotExistException, NoItemDuplicatesException {
		stock.initialise(item);
		stock.initialise(itemTemp);
		stock.add(item.getName(), 5);
		assertEquals(stock.currentQuantity(itemTemp.getName()), 0);
	}
	
	/**
	 * Test 4 : Testing removing some quantity from an 
	 * item is working as it should
	 * 
	 * Requires: initialise, add, remove, toString, currentQuantity
	 * 
	 */
	
	@Test
	public void testRemoveQuantity() throws ItemDoesNotExistException, NoItemDuplicatesException {
		stock.initialise(item);
		stock.add(item.getName(), 10);
		stock.remove(item.getName(), 5);
		assertEquals(stock.currentQuantity(item.getName()), 5);
	}
	
	/**
	 * Test 5 : Testing removing some quantity from an 
	 * item isn't also removing the same from another item
	 * 
	 * Requires: initialise, add, remove, toString, currentQuantity
	 * 
	 */
	
	@Test
	public void testRemoveTwoQuantity() throws ItemDoesNotExistException, NoItemDuplicatesException {
		stock.initialise(item);
		stock.initialise(itemTemp);
		stock.add(item.getName(), 10);
		stock.add(itemTemp.getName(), 10);
		stock.remove(item.getName(), 5);
		assertNotEquals(stock.currentQuantity(itemTemp.getName()), 5);
	}
	
	/**
	 * Test 6 : Testing discontinuing some item from the inventory
	 * actually discontinues it
	 * 
	 * Requires: initialise, discontinue, itemRetriever
	 * 
	 */
	
	@Test(expected = ItemDoesNotExistException.class)
	public void testDiscontinueItem() throws ItemDoesNotExistException, NoItemDuplicatesException {
		stock.initialise(item);
		stock.discontinue(item.getName());		
		stock.itemRetriever(item.getName());
	}
	
	/**
	 * Test 7 : Testing discontinuing some item from the inventory
	 * actually discontinues it and not all of the items
	 * 
	 * Requires: initialise, discontinue, itemRetriever
	 * 
	 */
	
	@Test
	public void testDiscontinueTwoItem() throws ItemDoesNotExistException, NoItemDuplicatesException {
		stock.initialise(item);
		stock.initialise(itemTemp);
		stock.discontinue(item.getName());		
		assertEquals(stock.itemRetriever(itemTemp.getName()), itemTemp);
	}
	
	/**
	 * Test 8 : Testing that when itemFinder is used, it is finding
	 * the correct item
	 * 
	 * Requires: initialise, discontinue, itemRetriever
	 * 
	 */
	
	@Test
	public void testItemFinderTwoItem() throws ItemDoesNotExistException, NoItemDuplicatesException {
		stock.initialise(item);
		stock.initialise(itemTemp);	
		assertNotSame(stock.itemRetriever(item.getName()), itemTemp);
	}
	
	/**
	 * Test 9 : Testing that when a reorder is called on the 
	 * current stock, the items that need to be reordered are added
	 * 
	 * Requires: initialise, add, reorder
	 * 
	 */
	
	@Test
	public void testReorder() throws ItemDoesNotExistException, NoItemDuplicatesException {
		stock.initialise(item);
		stock.initialise(itemTemp);			
		ArrayList<Item> testReorder = new ArrayList<Item>();
		testReorder.add(item);
		testReorder.add(itemTemp);
		assertEquals(stock.reorder(), testReorder);
	}
	
	/**
	 * Test 10 : Testing that when a reorder is called on the 
	 * current stock, and something doesn't need to be reordered, 
	 * it isn't added to the list
	 * 
	 * Requires: initialise, add, reorder
	 * 
	 */
	
	@Test
	public void testWrongReorder() throws ItemDoesNotExistException, NoItemDuplicatesException {
		stock.initialise(item);
		stock.initialise(itemTemp);		
		stock.add(item.getName(), 11);
		ArrayList<Item> testReorder = new ArrayList<Item>();		
		testReorder.add(itemTemp);
		assertEquals(stock.reorder(), testReorder);
	}
}
